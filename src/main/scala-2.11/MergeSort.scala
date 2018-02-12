object MergeSort {

  def msort[T](less: (T,T) => Boolean)(xs: List[T]): List[T] = {

    def merge(xs: List[T], ys: List[T]): List[T] = {
      (xs, ys) match {
        case (Nil, _) => ys
        case (_, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (less(x, y)) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }
    }

    val n = xs.length / 2
    if (n == 0) xs // No work to do
    else {
      val (ys, zs) = xs splitAt n
      merge(msort(less)(ys), msort(less)(zs))
    }
  }

  def orderedMergeSort[T](xs: List[T])(implicit orderer: T => Ordered[T]): List[T] = {

    def merge(xs: List[T], ys: List[T]): List[T] = {
      (xs, ys) match {
        case (Nil, _) => ys
        case (_, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (x < y) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }
    }

    val n = xs.length / 2
    if (n == 0) xs // No work to do
    else {
      val (ys, zs) = xs splitAt n
      merge(orderedMergeSort(ys), orderedMergeSort(zs))
    }
  }

  def viewBoundMergeSort[T <% Ordered[T]](xs: List[T]): List[T] = {

    def merge(xs: List[T], ys: List[T]): List[T] = {
      (xs, ys) match {
        case (Nil, _) => ys
        case (_, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (x < y) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }
    }

    val n = xs.length / 2
    if (n == 0) xs // No work to do
    else {
      val (ys, zs) = xs splitAt n
      merge(viewBoundMergeSort(ys), viewBoundMergeSort(zs))
    }
  }


  def main(args: Array[String]) = {
    def less(a: Int, b: Int) = if (a <= b) true else false
    val lst = List(23, 5, 100, 2, 9, 45, 1)
//    val res = msort(less)(lst)
//    println(res)
//    val res1 = orderedMergeSort[Int](lst)
//    println(res1)
    val res2 = viewBoundMergeSort[Int](lst)
    println(res2)
  }

}
