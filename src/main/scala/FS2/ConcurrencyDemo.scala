package FS2

import cats.effect.{IO, IOApp}
import fs2.Stream

object ConcurrencyDemo extends IOApp.Simple {
  val program: IO[Unit] = {
    val res = Stream(1,2,3)
      .merge(Stream.eval(IO { Thread.sleep(200); 4 }))
      .compile.toVector
    res.map(println)
  }

  val run = program
}
