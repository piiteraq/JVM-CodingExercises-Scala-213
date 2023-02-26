name := "Scala 2.13 Coding Exercises"
version := "1.0"
scalaVersion := "2.13.3"

resolvers ++= Resolver.sonatypeOssRepos("releases")
resolvers ++= Resolver.sonatypeOssRepos("snapshots")

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.14"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.14" % "test"

libraryDependencies += "com.chuusai" %% "shapeless" % "2.3.3"
libraryDependencies += "org.typelevel" %% "cats-core" % "2.8.0"
libraryDependencies += "org.typelevel" %% "cats-effect" % "3.4.1"

// available for 2.12, 2.13, 3.2
libraryDependencies += "co.fs2" %% "fs2-core" % "3.6.1"
// optional I/O library
libraryDependencies += "co.fs2" %% "fs2-io" % "3.6.1"
// optional reactive streams interop
libraryDependencies += "co.fs2" %% "fs2-reactive-streams" % "3.6.1"
// optional scodec interop
libraryDependencies += "co.fs2" %% "fs2-scodec" % "3.6.1"