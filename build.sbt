name := "Scala 2.13 Coding Exercises"

version := "1.0"

scalaVersion := "2.13.3"

resolvers ++= Resolver.sonatypeOssRepos("releases")
resolvers ++= Resolver.sonatypeOssRepos("snapshots")

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8"
libraryDependencies += "com.chuusai" %% "shapeless" % "2.3.3"
