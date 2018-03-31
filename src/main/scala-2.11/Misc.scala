import scala.annotation.tailrec

object Misc {

  def isPalindrome(s: String): Boolean = {
    if (s.length <= 1) return true
    else {
      var l = 0
      var r = s.length - 1
      while (l <= r) {
        if (s(l) != s(r)) return false
        else { l += 1; r -= 1 }
      }
      true
    }
  }

  @tailrec
  def isPalRec(s: String): Boolean = {
    if (s.length <= 1) true
    else if (s(0) != s(s.length-1)) false
    else isPalRec(s.substring(1, s.length-1))
  }


  def main(args: Array[String]) = {
    println(isPalindrome(""))
    println(isPalindrome("a"))
    println(isPalindrome("madamadam"))
    println(isPalindrome("qwerrewq"))
    println(isPalindrome("qwer"))
    println("----------")
    println(isPalRec(""))
    println(isPalRec("a"))
    println(isPalRec("madamadam"))
    println(isPalRec("qwerrewq"))
    println(isPalRec("qwer"))
  }

}
