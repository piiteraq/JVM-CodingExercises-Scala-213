object Misc {

  def isPalindrome(s: String): Boolean = {
    if (s.length == 0 ) return false
    else if (s.length == 1) return true
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

  def main(args: Array[String]) = {
    println(isPalindrome("madamadam"))
    println(isPalindrome("qwerrewq"))
    println(isPalindrome("qwer"))
  }

}
