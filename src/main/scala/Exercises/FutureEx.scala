package Exercises

/**
  * Created by petec on 8/7/16.
  */

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._ //{Await, Future}
import scala.concurrent.duration._
import util.{Failure, Success}
import util.Random
import scala.language.postfixOps

object FutureBlock extends App {

    def sleep(time: Long) = { Thread.sleep(time) }
    implicit val basetime = System.currentTimeMillis()

    val f = Future {
      Thread.sleep(500)
      1+1
    }

    val result = Await.result(f, 1 second)
    println(result)

    sleep(1000) // Prevent program from exiting before Future is completed
}

object FutureOnComplete extends App {

  def sleep(time: Long) = { Thread.sleep(time) }

  println("Starting calculation")
  val f = Future {
    sleep(Random.nextInt(500))
    42
  }

  println("Before onComplete")
  f.onComplete {
    case Success(value) => println(s"Got the callback, meaning = $value")
    case Failure(e) => e.printStackTrace
  }

  // Do rest of work ..
  println("A ..."); sleep(100)
  println("B ..."); sleep(100)
  println("C ..."); sleep(100)
  println("D ..."); sleep(100)
  println("E ..."); sleep(100)
  println("F ..."); sleep(100)

  sleep(1000)
}

object FutureOnSuccessAndFailure extends App {

  def sleep(time: Long) = { Thread.sleep(time) }

  val f = Future {
    sleep(Random.nextInt(500))
    if (Random.nextInt(500) > 250) throw new Exception("Yikes!") else 42
  }

  f onComplete {
    case Success(result) => println(s"Success: $result")
    case Failure(t) => println("An error has occurred: " + t.getMessage)
  }

  f onComplete {
    case Success(_) =>
    case Failure(t) => println(s"Exception: ${t.getMessage}")
  }

  // Do rest of work ..
  println("A ..."); sleep(100)
  println("B ..."); sleep(100)
  println("C ..."); sleep(100)
  println("D ..."); sleep(100)
  println("E ..."); sleep(100)
  println("F ..."); sleep(100)


  sleep(2000)
}

object FutureT extends App {

  def sleep(time: Long) = { Thread.sleep(time) }
  implicit val basetime = System.currentTimeMillis()

  def longRunningComputation(i: Int): Future[Int] = Future {
    sleep(100)
    i + 1
  }

  longRunningComputation(11).onComplete {
    case Success(result) => println(s"result = $result")
    case Failure(e) => e.printStackTrace
  }

  // Do rest of work ..
  println("A ..."); sleep(100)
  println("B ..."); sleep(100)
  println("C ..."); sleep(100)
  println("D ..."); sleep(100)
  println("E ..."); sleep(100)
  println("F ..."); sleep(100)

  sleep(1000) // Prevent JVM from shutting down
}

object MultiParallelTasks extends App {

  def sleep(time: Long) = { Thread.sleep(time) }
  implicit val basetime = System.currentTimeMillis()

  println("Starting futures ..")
  val result1 = Future { sleep(Random.nextInt(500)); 1 }
  val result2 = Future { sleep(Random.nextInt(500)); 2 }
  val result3 = Future { sleep(Random.nextInt(500)); 3 }

  println("Before for-comprehension")
  val result: Future[Int] = for {
    r1 <- result1
    r2 <- result2
    r3 <- result3
  } yield r1+r2+r3

  println("Before onSuccess")
  result onComplete  {
    case Success(value) => println(s"total = $value")
    case Failure(t) => println(s"Failed: ${t}")
  }

  println("Before sleep at the end")
  sleep(1000)
}

object PromiseExample extends App {
  import scala.concurrent.{ Future, Promise }
  import scala.concurrent.ExecutionContext.Implicits.global

  val p = Promise[Double]()
  val f = p.future

  val producer = Future {
    val r = Math.PI
    println(s"Producer adding $r to promise")
    p success r
    println("Producer continuing with other work ..")
    Thread.sleep(2000)
  }

  val consumer = Future {
    println("Consumer doing initial work")
    Thread.sleep(500)
    f onComplete {
      case Success(r) => println(s"Consumer harvested future: $r")
      case Failure(t) => println(t)

    }
    f onComplete {
      case Failure(ex) => println(s"Consumer harvested failure: $ex")
      case _ =>
    }
  }

  Thread.sleep(2000)
}