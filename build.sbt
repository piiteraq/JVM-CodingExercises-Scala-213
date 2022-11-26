name := "Scala 2.13 Coding Exercises"

version := "1.0"

scalaVersion := "2.13.3"

resolvers ++= Resolver.sonatypeOssRepos("releases")
resolvers ++= Resolver.sonatypeOssRepos("snapshots")

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.14"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.14" % "test"
libraryDependencies += "org.scalatest" %% "scalatest-propspec" % "3.2.14" % "test"
libraryDependencies += "org.scalatest" %% "scalatest-featurespec" % "3.2.14" % "test"


libraryDependencies += "com.chuusai" %% "shapeless" % "2.3.3"
libraryDependencies += "org.typelevel" %% "cats-core" % "2.8.0"
libraryDependencies += "org.typelevel" %% "cats-effect" % "3.4.1"
