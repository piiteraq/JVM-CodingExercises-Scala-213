/**
  * Created by petec on 8/7/16.
  */

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object FutureEx {

  // Ex1
  def nextFtr(i: Int = 0): Future[Int] = Future {
    def rand(x: Int) = util.Random.nextInt(x)
    Thread.sleep(rand(5000)) // Avoid in production code.
    if (rand(3) > 0) (i+1) else throw new Exception
  }

  // Ex2
  def cityTemp(name: String): Double = {
    val url = "http://api.openweathermap.org/data/2.5/weather"
    val cityUrl = s"$url?q=$name&appid=3835f64c2f77cc8d0b6ebb4b2a88e714"
    val json = io.Source.fromURL(cityUrl).mkString.trim
    val pattern = """.*"temp":([\d.]+).*""".r
    val pattern(temp) = json
    temp.toDouble
  }

  def main(args: Array[String]) = {

//    val cityTemps = Future sequence Seq(
//      Future(cityTemp("Fresno")), Future(cityTemp("Tempe"))
//    )
//
//    println("Sleeping 20 ..")
//    Thread.sleep(20000)
//    println(s"Woke up. cityTemps: $cityTemps")
//
//    cityTemps onFailure {
//      case _ => println("Failed")
//    }
//
//    cityTemps onSuccess {
//      case Seq(x,y) if x > y => println(s"Fresno is warmer: $x K")
//      case Seq(x,y) if y >x => println(s"Tempe is warmer: $y K")
//      case Seq(x,_) => println(s"Temperature is the same: $x K")
//    }

    import scala.concurrent._
    import ExecutionContext.Implicits.global
    import scala.concurrent.duration._
    import scala.util.{Failure, Success}

    val MaxTime = Duration(10, SECONDS)

    val firstOccurrence: Future[Int] = Future {
      val source = scala.io.Source.fromFile("/Users/petec/notebooks/myText.txt")
      val res = source.toSeq.indexOfSlice("myKeyWord")
      res
    }

    firstOccurrence onComplete {
      case Success(pos) => println(s"Position: $pos")
      case Failure(f) => println(s"Search failed: ${f.getMessage}")
    }

    Thread.sleep(5000) // Prevent program from exiting before Future is completed
  }
}
