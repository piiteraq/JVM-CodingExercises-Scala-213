package Cats

import cats.Applicative
import cats.implicits._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object ApplicativeDemo extends App {

  val x: Future[Option[Int]] = Future.successful(Some(5))
  val y: Future[Option[Char]] = Future.successful(Some('f'))
  val composed: Future[Option[Int]] = Applicative[Future].compose[Option].map2(x, y)(_ + _)
  println(composed)

  def traverseOption[A, B](as: List[A])(f: A => Option[B]): Option[List[B]] =
    as.foldRight(Some(List.empty[B]): Option[List[B]]) { (a: A, acc: Option[List[B]]) =>
      val optB: Option[B] = f(a)
      // optB and acc are independent effects so we can use Applicative to compose
      Applicative[Option].map2(optB, acc)(_ :: _)
    }
  val ta = traverseOption(List(1, 2, 3))(i => Some(i): Option[Int])
  println(ta)

}

