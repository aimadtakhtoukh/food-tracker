name := "open-food-facts-consumer"

version := "0.1"

scalaVersion := "2.13.6"

val AkkaVersion = "2.6.14"
val AlpakkaVersion = "3.0.1"
val AkkaHttpVersion = "10.2.4"
libraryDependencies ++= Seq(
  "com.lightbend.akka" %% "akka-stream-alpakka-csv" % AlpakkaVersion,
  "com.lightbend.akka" %% "akka-stream-alpakka-file" % AlpakkaVersion,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
  "com.lightbend.akka" %% "akka-stream-alpakka-mongodb" % AlpakkaVersion,
  "org.json4s" %% "json4s-native" % "4.0.1"
)