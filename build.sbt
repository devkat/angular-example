import Dependencies._

resolvers ++= Seq(
  "spray repo" at "http://repo.spray.io",
  sbtResolver.value
)

lazy val angularExample = (
  Project("angular-example", file("."))
    settings(
    organization := "ch.becompany",
    name := "angular-example",
    version := "1.0.0-SNAPSHOT",
    scalaVersion := "2.11.7",
    crossScalaVersions := Seq("2.11.7"),
    scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8"),
    libraryDependencies ++= dependencies,
    Revolver.settings,
    typingsFile := Some(baseDirectory.value / "typings" / "browser.d.ts")
  )).enablePlugins(SbtWeb)

