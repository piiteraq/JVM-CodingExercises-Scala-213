package FS2

import fs2.Stream
import cats.effect.{Deferred, IO}
import cats.effect.unsafe.implicits.global
import scala.concurrent.duration._
import cats.effect.{IO, IOApp}

object InterruptDemo extends App {

  val program =
    Stream.eval(Deferred[IO, Unit]).flatMap { switch =>

      val switcher =
        Stream.eval(switch.complete(())).delayBy(5.seconds)

      val program =
        Stream.repeatEval(IO(println(java.time.LocalTime.now))).metered(1.second)

      program
        .interruptWhen(switch.get.attempt)
        .concurrently(switcher)
    }

  // Shortened version
  val program1 =
    Stream.
      repeatEval(IO(println(java.time.LocalTime.now))).
      metered(1.second).
      interruptAfter(5.seconds)

  println(s"Running program: ${program}")
  program.compile.drain.unsafeRunSync()
  println(s"Running program1: ${program1}")
  program1.compile.drain.unsafeRunSync()
}

