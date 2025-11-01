name := "http.server"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "3.7.4-RC3"
libraryDependencies ++= {
  Seq(
    "com.typesafe" % "config" % "1.4.3",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
    "ch.qos.logback" % "logback-classic" % "1.5.20"
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
