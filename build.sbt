name := """TweetAnalytics"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.4"

crossScalaVersions := Seq("2.11.12", "2.12.4")

libraryDependencies += guice

// Test Database
libraryDependencies += "com.h2database" % "h2" % "1.4.196"

// Testing libraries for dealing with CompletionStage...
libraryDependencies += "org.assertj" % "assertj-core" % "3.6.2" % Test
libraryDependencies += "org.awaitility" % "awaitility" % "2.0.0" % Test

// Mockito for tests
libraryDependencies += "org.mockito" % "mockito-core" % "2.10.0" % "test"

libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.5.4" % Test

// WSClient for making HTTP Restful requests
libraryDependencies ++= Seq(
  ws
)

// Caching
libraryDependencies ++= Seq(
  ehcache
)


// Make verbose tests
testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))


