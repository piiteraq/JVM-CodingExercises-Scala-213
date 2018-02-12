/**
  * Created by petec on 7/31/16.
  * Example from Odersky, Spoon, Venners, Chapter 4.
  */
import scala.collection.mutable

class ChecksumAccumulator {
  private var sum = 0
  def add(b: Byte): Unit = sum += b
  def checksum(): Int = ~(sum & 0xFF) + 1
}

object ChecksumAccumulator {

  private val cache = mutable.Map[String, Int]()

  def calculate(s: String): Int =
    if (cache.contains(s)) {
      println(s"Cache hit for '$s'")
      cache(s)
    } else {
      println(s"Cache miss for '$s'")
      val acc = new ChecksumAccumulator
      for (c <- s)
        acc.add(c.toByte)
      val cs = acc.checksum()
      cache += (s -> cs)
      cs
    }
}

object CheckSummer {
  import ChecksumAccumulator.calculate

  def main(args: Array[String]) = {
    for (arg <- args)
      println(arg + ": " + calculate(arg))
  }
}
