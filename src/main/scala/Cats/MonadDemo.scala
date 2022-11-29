package Cats

import cats._
import cats.Monad
import scala.annotation.tailrec

object MonadDemo extends App {

  implicit def optionMonad(implicit app: Applicative[Option]) =
    new Monad[Option] {
      // Define flatMap using Option's flatten method
      override def flatMap[A, B](fa: Option[A])(f: A => Option[B]) =
        app.map(fa)(f).flatten

      // Reuse this definition from Applicative.
      override def pure[A](a: A): Option[A] = app.pure(a)

      @annotation.tailrec
      def tailRecM[A, B](init: A)(fn: A => Option[Either[A, B]]): Option[B] =
        fn(init) match {
          case None => None
          case Some(Right(b)) => Some(b)
          case Some(Left(a)) => tailRecM(a)(fn)
        }
    }

  val res = Monad[List].ifM(List(true, false, true))(ifTrue = List(1, 2), ifFalse = List(3, 4))
  println(res)
}
