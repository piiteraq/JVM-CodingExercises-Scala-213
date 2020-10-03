name := "CodingExercises"

version := "1.0"

scalaVersion := "2.13.3"

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8"
libraryDependencies += "com.chuusai" %% "shapeless" % "2.3.3"
