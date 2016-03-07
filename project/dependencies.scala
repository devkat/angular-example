import sbt._

object Dependencies {
  val akkaVersion = "2.4.1"
  val sprayVersion = "1.3.3"
  val sprayJsonVersion = "1.3.2"

  val akkaActor = "com.typesafe.akka" %% "akka-actor" % akkaVersion
  val sprayCan = "io.spray" %% "spray-can" % sprayVersion
  val sprayRouting = "io.spray" %% "spray-routing" % sprayVersion
  val sprayJson = "io.spray" %% "spray-json" % sprayJsonVersion

  val dependencies = Seq(
    akkaActor,
    sprayCan,
    sprayRouting,
    sprayJson
  )
}
