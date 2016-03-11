import sbt._

object Dependencies {
  val akkaVersion = "2.4.1"
  val sprayVersion = "1.3.3"
  val sprayJsonVersion = "1.3.2"
  val slickVersion = "3.1.1"
  val slf4jVersion = "1.6.4"
  val h2Version = "1.3.170"

  val angularVersion = "2.0.0-beta.8"
  val systemJsVersion = "0.19.20"
  val es6PromiseVersion = "3.1.2"
  val es6ShimVersion = "0.35.0"
  val reflectMetadataVersion = "0.1.3"
  val rxjsVersion = "5.0.0-beta.2"
  val zonejsVersion = "0.5.15"
  val pageJsVersion = "1.6.4"
  val polymerVersion = "1.2.4"
  val polymerIronElementsVersion = "1.0.4"
  val polymerPaperElementsVersion = "1.0.6"
  val polymerTsVersion = "0.1.20"
  val webComponentsVersion = "0.7.20"

  val akkaActor = "com.typesafe.akka" %% "akka-actor" % akkaVersion
  val sprayCan = "io.spray" %% "spray-can" % sprayVersion
  val sprayRouting = "io.spray" %% "spray-routing" % sprayVersion
  val sprayJson = "io.spray" %% "spray-json" % sprayJsonVersion
  val slick = "com.typesafe.slick" %% "slick" % slickVersion
  val slickCodegen = "com.typesafe.slick" %% "slick-codegen" % slickVersion % "compile"
  val slf4j = "org.slf4j" % "slf4j-nop" % slf4jVersion
  val h2 = "com.h2database" % "h2" % h2Version

  val angular = "org.webjars.npm" % "angular2" % angularVersion
  val systemJs = "org.webjars.npm" % "systemjs" % systemJsVersion
  val es6Promise= "org.webjars.npm" % "es6-promise" % es6PromiseVersion
  val es6Shim = "org.webjars.npm" % "es6-shim" % es6ShimVersion
  val reflectMetadata = "org.webjars.npm" % "reflect-metadata" % reflectMetadataVersion
  val rxjs = "org.webjars.npm" % "rxjs" % rxjsVersion
  val zonejs = "org.webjars.npm" % "zone.js" % zonejsVersion

  val pageJs = "org.webjars.bower" % "page" % pageJsVersion
  val polymer = "org.webjars.bower" % "github-com-Polymer-polymer" % polymerVersion
  val polymerIronElements = "org.webjars.bower" % "github-com-PolymerElements-iron-elements" %
    polymerIronElementsVersion excludeAll(ExclusionRule(name = "github-com-polymer-polymer"))
  val polymerPaperElements = "org.webjars.bower" % "github-com-PolymerElements-paper-elements" % polymerPaperElementsVersion
  val polymerTs = "org.webjars.bower" % "polymer-ts" % polymerTsVersion exclude("org.webjars.bower", "polymer")
  val webComponents = "org.webjars.bower" % "webcomponentsjs" % webComponentsVersion

  val angularDependencies = Seq(
    angular,
    systemJs,
    es6Promise,
    es6Shim,
    reflectMetadata,
    rxjs,
    zonejs
  )

  val polymerDependencies = Seq(
    pageJs,
    polymer,
    polymerIronElements,
    polymerPaperElements,
    polymerTs
    //webComponents
  ).map(_.excludeAll(
    ExclusionRule(organization = "org.webjars.bower", name = "github-com-polymerelements-iron-a11y-keys-behavior"),
    ExclusionRule(organization = "org.webjars.bower", name = "github-com-polymerelements-iron-dropdown"),
    ExclusionRule(organization = "org.webjars.bower", name = "github-com-polymerelements-iron-flex-layout"),
    ExclusionRule(organization = "org.webjars.bower", name = "github-com-polymerelements-iron-icons"),
    //ExclusionRule(organization = "org.webjars.bower", name = "github-com-polymerelements-iron-iconset-svg"),
    ExclusionRule(organization = "org.webjars.bower", name = "github-com-polymerelements-iron-overlay-behavior"),
    ExclusionRule(organization = "org.webjars.bower", name = "github-com-polymerelements-iron-selector"),
    ExclusionRule(organization = "org.webjars.bower", name = "github-com-polymerelements-paper-icon-button"),
    ExclusionRule(organization = "org.webjars.bower", name = "github-com-polymerelements-paper-listbox"),
    ExclusionRule(organization = "org.webjars.bower", name = "github-com-polymerelements-paper-material"),
    //ExclusionRule(organization = "org.webjars.bower", name = "github-com-polymerelements-paper-ripple"),
    ExclusionRule(organization = "org.webjars.bower", name = "github-com-polymerelements-paper-styles")
  ))

  val clientDependencies = angularDependencies ++ polymerDependencies

  val serverDependencies = Seq(
    akkaActor,
    sprayCan,
    sprayRouting,
    sprayJson,
    slick,
    slickCodegen,
    slf4j,
    h2
  )

  val dependencies = clientDependencies ++ serverDependencies
}
