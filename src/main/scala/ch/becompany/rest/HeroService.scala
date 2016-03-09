package ch.becompany.rest

import akka.actor.{ActorContext, Actor}
import ch.becompany.Boot
import ch.becompany.dao.Tables.HeroRow
import ch.becompany.db.Heroes
import ch.becompany.model.{HeroValidator, Hero}
import ch.becompany.json.HeroJsonProtocol
import ch.becompany.validation.Validator
import ch.becompany.validation.Validation.ValidationResult
import shapeless._
import spray.http.StatusCodes
import spray.httpx.unmarshalling.FromRequestUnmarshaller
import spray.httpx.marshalling._
import spray.routing._
import spray.json._

import scala.concurrent.ExecutionContext

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class HeroServiceActor extends Actor with HeroService {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(heroesRoute)
}

case class ValidateRejection(result: ValidationResult) extends Rejection

// this trait defines our service behavior independently from the service actor
trait HeroService extends HttpService {
  val context: ActorContext

  import HeroJsonProtocol._
  import spray.httpx.SprayJsonSupport._
  import context.dispatcher

  val versions = Map(
    "angular-example" -> "1.0.0-SNAPSHOT",
    "angular2" -> "2.0.0-beta.8",
    "es6-promise" -> "3.1.2",
    "es6-shim" -> "0.35.0",
    "reflect-metadata" -> "0.1.3",
    "rxjs" -> "5.0.0-beta.2",
    "systemjs" -> "0.19.20"
  )

  def validateEntity1[T:Validator, U <: HList](t: T): Directive0 =
    new Directive0 {
      def happly(inner: HNil ⇒ Route): Route = {
        val validator = implicitly[Validator[T]]
        val result = validator.validate(t)
        if (result.isEmpty) inner(HNil)
          else reject(ValidateRejection(result))
      }
    }
/*
  def validateEntity[T:Validator](t: T): Directive1[T] =
    entity(um) flatMap {
      case t =>
        val validator = implicitly[Validator[T]]
        val result = validator.validate(t)
        if (result.isEmpty) t
        else reject(ValidateRejection(result))
    }
*/

  def validateEntity[T:Validator](d: Directive1[T]): Directive1[T] =
    d flatMap {
      case t =>
        val validator = implicitly[Validator[T]]
        val result = validator.validate(t)
        if (result.isEmpty) provide(t)
        else reject(ValidateRejection(result))
    }

  implicit val heroValidator = HeroValidator
  val validateHero = validateEntity(entity(as[Hero]))

  implicit val validateRejectionHandler = RejectionHandler {
    case List(ValidateRejection(result)) => {
      complete((StatusCodes.BadRequest, result.toJson))
    }
  }

  lazy val heroesRoute =
    pathPrefix("api") {
      path("heroes") {
        get {
          complete(Heroes.list())
        } ~
        post {
          entity(as[Hero]) { hero =>
            complete(Heroes.add(hero) map (id => hero.copy(id = Some(id))))
          }
        }
      } ~
      path("heroes" / LongNumber) { id =>
        rejectEmptyResponse {
          get {
            complete(Heroes.find(id))
          }
        } ~
        put {
          validateHero { hero =>
              complete {
                Heroes.update(id, hero)
                hero
              }
          }
        }
      }
    } ~
    pathPrefix("node_modules" / Segment) { moduleName =>
      get {
        val version = versions(moduleName)
        getFromResourceDirectory(s"META-INF/resources/webjars/$moduleName/$version")
      }
    } ~
    path(PathEnd) {
      getFromResource("index.html")
    } ~
    pathPrefix("") {
      getFromResourceDirectory("")
    }

}