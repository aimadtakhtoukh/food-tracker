name := "open-food-facts-consumer"

version := "0.1"

scalaVersion := "3.0.0"

crossScalaVersions ++= Seq("2.13.6", "3.0.0")

val AkkaVersion = "2.6.14"
val AlpakkaVersion = "3.0.1"
val AkkaHttpVersion = "10.2.4"

libraryDependencies ++= Seq(
  ("com.lightbend.akka" %% "akka-stream-alpakka-csv" % AlpakkaVersion).cross(CrossVersion.for3Use2_13),
  ("com.lightbend.akka" %% "akka-stream-alpakka-file" % AlpakkaVersion).cross(CrossVersion.for3Use2_13),
  ("com.typesafe.akka" %% "akka-stream" % AkkaVersion).cross(CrossVersion.for3Use2_13),
  ("com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion).cross(CrossVersion.for3Use2_13),
  ("com.typesafe.akka" %% "akka-stream" % AkkaVersion).cross(CrossVersion.for3Use2_13),
  ("com.typesafe.akka" %% "akka-http" % AkkaHttpVersion).cross(CrossVersion.for3Use2_13),
  ("com.lightbend.akka" %% "akka-stream-alpakka-mongodb" % AlpakkaVersion).cross(CrossVersion.for3Use2_13),
  "org.json4s" %% "json4s-native" % "4.0.3"
)

scalacOptions ++= {
  Seq(
    "-feature",
    "-language:implicitConversions",
    // disabled during the migration
    // "-Xfatal-warnings"
  ) ++
    (CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((3, _)) => Seq(
        "-unchecked",
        "-source:3.0-migration"
      )
      case _ => Seq(
        "-deprecation",
        "-Xfatal-warnings",
        "-Wunused:imports,privates,locals",
        "-Wvalue-discard"
      )
    })
}