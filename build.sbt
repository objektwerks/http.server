name := "http.server"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "3.6.4"
libraryDependencies ++= {
  Seq(
    "com.typesafe" % "config" % "1.4.3",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
    "ch.qos.logback" % "logback-classic" % "1.5.17"
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
