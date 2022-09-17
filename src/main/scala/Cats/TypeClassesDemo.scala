package Cats

// Demo of Type Classes, using Monoid as concrete example.

import cats.Monoid

final case class Pair[A, B](first: A, second: B)

object Pair {
  implicit def tuple2Instance[A, B](implicit A: Monoid[A], B: Monoid[B]): Monoid[Pair[A, B]] =
    new Monoid[Pair[A, B]] {
      def empty: Pair[A, B] = Pair(A.empty, B.empty)

      def combine(x: Pair[A, B], y: Pair[A, B]): Pair[A, B] =
        Pair(A.combine(x.first, y.first), B.combine(x.second, y.second))
    }
}

object TypeClassesDemo extends App {

  def combineAll[A: Monoid](list: List[A]): A =
    list.foldRight(Monoid[A].empty)(Monoid[A].combine)

  val intLst = List(1, 2, 3)
  println(combineAll(intLst))

  val strLst = List("we ", "are ", "here")
  println(combineAll(strLst))

  println(combineAll(List(Pair(1, "hello"), Pair(2, " "), Pair(3, "world"))))

  // Since combine op is associative, we can use divide-and-conquer strategy
  val list = List(1, 2, 3, 4, 5)
  val (left, right) = list.splitAt(2)
  val sumLeft = combineAll(left)
  val sumRight = combineAll(right)
  println(Monoid[Int].combine(sumLeft, sumRight))
}
