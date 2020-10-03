package Exercises

/**
  * Created by petec on 8/11/16.
  */
object ConvCityState {

  def convert(inputFile: String): Map[String, List[String]] = {
    val stateCitiesMap = Map[String, List[String]]()
    val lines = scala.io.Source.fromFile(inputFile).getLines()
    val stateCityPairs = for {
      line <- lines
      _ = println(line.toList)
      arr = line.split(",")
      //_ = println(arr.toList)
    } yield arr
    //®print(stateCityPairs)
    stateCitiesMap
  }

  def main(args: Array[String]) = {
    val inputFile = args(0) //TBD: Error handling
    val output = convert(inputFile)
    // Save output to file
  }

}
