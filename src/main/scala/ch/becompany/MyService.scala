package ch.becompany

import akka.actor.Actor
import spray.routing._
import spray.http._
import MediaTypes._

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class MyServiceActor extends Actor with MyService {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(myRoute)
}

// this trait defines our service behavior independently from the service actor
trait MyService extends HttpService {

  val versions = Map(
    "angular-example" -> "1.0.0-SNAPSHOT",
    "angular2" -> "2.0.0-beta.8",
    "es6-promise" -> "3.1.2",
    "es6-shim" -> "0.35.0",
    "reflect-metadata" -> "0.1.3",
    "rxjs" -> "5.0.0-beta.2",
    "systemjs" -> "0.19.20"
  )

  val myRoute =
    pathPrefix("node_modules" / Segment) { moduleName =>
      get {
        val version = versions(moduleName)
        getFromResourceDirectory(s"META-INF/resources/webjars/$moduleName/$version")
      }
    } ~
    pathPrefix("") {
      getFromResourceDirectory("")
    }

}