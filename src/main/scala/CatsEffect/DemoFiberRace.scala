package CatsEffect

import cats.effect.{IO, IOApp}
import cats.syntax.all._

object DemoFiberRace extends IOApp.Simple {
  def factorial(n: Long): Long =
    if (n == 0) 1 else n * factorial(n - 1)

  override def run: IO[Unit] =
    for {
      res <- IO.race(IO(factorial(20)), IO(factorial(20)))
      _ <- res.fold(
        a => IO.println(s"Left hand side won: $a"),
        b => IO.println(s"Right hand side won: $b")
      )
    } yield ()
}