package CatsEffect

import cats.effect.{IO, IOApp}
import cats.syntax.all._
import scala.concurrent.duration._

object DemoFiberCancel extends IOApp.Simple {
  override def run: IO[Unit] =
    for {
      fiber <- IO.println("hello!").foreverM.start
      _ <- IO.sleep(5.seconds)
      _ <- fiber.cancel
      _ <- IO.println("All done ..")
    } yield ()
}
