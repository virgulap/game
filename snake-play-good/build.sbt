import play.Project._

name := """snake-play-good"""

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.2.0", 
  "org.webjars" % "bootstrap" % "2.3.1",
  "org.scala-lang" % "scala-swing" % "2.10+"
)



playScalaSettings
