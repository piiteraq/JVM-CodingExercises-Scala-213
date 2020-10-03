package Exercises

import scala.language.postfixOps

/**
  * Created by petec on 8/13/16.
  * From Odersky, pp.483-486
  */
object NQueen {

  def isSafe(queen: (Int, Int), queens: List[(Int, Int)]) =
    queens forall (q => !inCheck(queen, q))

  def inCheck(q1: (Int, Int), q2: (Int, Int)) =
    q1._1 == q2._1 || // on same row
      q1._2 == q2._2 || // on same column
      (q1._1 - q2._1).abs == (q1._2 - q2._2).abs // on same diagonal

  def queens(n: Int): List[List[(Int, Int)]] = {
    def placeQueens(k: Int): List[List[(Int, Int)]] =
      if (k == 0)
        List(List())
      else
        for {
          queens <- placeQueens(k - 1)
          column <- 1 to n
          queen = (k, column)
          if isSafe(queen, queens)
        } yield queen :: queens
    placeQueens(n)
  }

  def show(queens: List[Int]) = {
    val lines =
      for (col <- queens.reverse)
        yield Vector.fill(queens.length)("* ").updated(col, "X ").mkString
    "\n" + (lines mkString "\n")
  }

  def main(args: Array[String]) = {
    for (x <- queens(5) zipWithIndex) {
      println(s"Sol ${x._2}: ${x._1 reverse}")
    }
//    val foo = for {
//      x <- queens(5)
//      (r, c) <- x
//    } yield c
  }

}
