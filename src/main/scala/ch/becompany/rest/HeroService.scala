package ch.becompany.rest

import akka.actor.{ActorContext, Actor}
import ch.becompany.Boot
import ch.becompany.dao.Tables.HeroRow
import ch.becompany.db.Heroes
import ch.becompany.model.Hero
import ch.becompany.json.HeroJsonProtocol
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
          entity(as[Hero]) { hero =>
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