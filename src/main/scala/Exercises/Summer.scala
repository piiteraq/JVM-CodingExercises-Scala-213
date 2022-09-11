package Exercises

import ChecksumAccumulator.calculate

//object Summer {
//  def main(args: Array[String]) = {
//    for (arg <- args)
//      println(arg + ": " + calculate(arg))
//  }
//}

object Summer extends App {
  for (season <- List("fall", "winter", "spring", "summer"))
    println(season + ": " + calculate(season))

  // Command line args still available ..
  for (arg <- args)
    println(arg)
}