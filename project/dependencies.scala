import sbt._

object Dependencies {
  val akkaVersion = "2.4.1"
  val sprayVersion = "1.3.3"
  val sprayJsonVersion = "1.3.2"

  val angularVersion = "2.0.0-beta.8"
  val systemJsVersion = "0.19.20"
  val es6PromiseVersion = "3.1.2"
  val es6ShimVersion = "0.35.0"
  val reflectMetadataVersion = "0.1.3"
  val rxjsVersion = "5.0.0-beta.2"
  val zonejsVersion = "0.5.15"

  val akkaActor = "com.typesafe.akka" %% "akka-actor" % akkaVersion
  val sprayCan = "io.spray" %% "spray-can" % sprayVersion
  val sprayRouting = "io.spray" %% "spray-routing" % sprayVersion
  val sprayJson = "io.spray" %% "spray-json" % sprayJsonVersion

  val angular = "org.webjars.npm" % "angular2" % angularVersion
  val systemJs = "org.webjars.npm" % "systemjs" % systemJsVersion
  val es6Promise= "org.webjars.npm" % "es6-promise" % es6PromiseVersion
  val es6Shim = "org.webjars.npm" % "es6-shim" % es6ShimVersion
  val reflectMetadata = "org.webjars.npm" % "reflect-metadata" % reflectMetadataVersion
  val rxjs = "org.webjars.npm" % "rxjs" % rxjsVersion
  val zonejs = "org.webjars.npm" % "zone.js" % zonejsVersion

  val clientDependencies = Seq(
    angular,
    systemJs,
    es6Promise,
    es6Shim,
    reflectMetadata,
    rxjs,
    zonejs
  )

  val serverDependencies = Seq(
    akkaActor,
    sprayCan,
    sprayRouting,
    sprayJson
  )

  val dependencies = clientDependencies ++ serverDependencies
}
