name := "http.server"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "3.5.0-RC2"
libraryDependencies ++= {
  Seq(
    "com.typesafe" % "config" % "1.4.3",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
    "ch.qos.logback" % "logback-classic" % "1.5.6"
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
