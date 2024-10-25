name := "http.server"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "3.6.1"
libraryDependencies ++= {
  Seq(
    "com.typesafe" % "config" % "1.4.3",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
    "ch.qos.logback" % "logback-classic" % "1.5.11"
  )
}
scalacOptions ++= Seq(
  "-Wall"
)
