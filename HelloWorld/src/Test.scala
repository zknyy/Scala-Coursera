object Test extends App {
  val l = List(5, 0, 1, 2, 3, 4, 5, 6) //> l  : List[Int] = List(5, 0, 1, 2, 3, 4, 5, 6)
  for (i <- 0 to l.length - 1) {
    print(l(i) + " ") //> 5 0 1 2 3 4 5 6 
  }
  println() //> 
  l.take(3) ::: l.takeRight(2) //> res2: List[Int] = List(5, 0, 1, 5, 6)

  def removeElem[T >: Nothing](i: Int, l: List[T]): List[T] = {
    l.take(i) ::: l.takeRight(l.size - i - 1)
  } //> removeElem: [T](i: Int, l: List[T])List[T]
  l.head //> res3: Int = 5
  l.tail //> res4: List[Int] = List(0, 1, 2, 3, 4, 5, 6)
  l.init //> res5: List[Int] = List(5, 0, 1, 2, 3, 4, 5)
  l.last //> res6: Int = 6
  l take 3 //> res7: List[Int] = List(5, 0, 1)

  l drop 3 //> res8: List[Int] = List(2, 3, 4, 5, 6)
  l splitAt 3 //> res9: (List[Int], List[Int]) = (List(5, 0, 1),List(2, 3, 4, 5, 6))

  l filter (x => x > 3) //> res10: List[Int] = List(5, 4, 5, 6)
  l filterNot (x => x > 3) //> res11: List[Int] = List(0, 1, 2, 3)
  l partition (x => x > 3) //> res12: (List[Int], List[Int]) = (List(5, 4, 5, 6),List(0, 1, 2, 3))

  l takeWhile (x => x > 3) //> res13: List[Int] = List(5)
  l dropWhile (x => x > 3) //> res14: List[Int] = List(0, 1, 2, 3, 4, 5, 6)
  l span (x => x > 3) //> res15: (List[Int], List[Int]) = (List(5),List(0, 1, 2, 3, 4, 5, 6))

  removeElem(0, l) //> res16: List[Int] = List(0, 1, 2, 3, 4, 5, 6)

  removeElem(1, l) //> res17: List[Int] = List(5, 1, 2, 3, 4, 5, 6)

  removeElem(2, l) //> res18: List[Int] = List(5, 0, 2, 3, 4, 5, 6)

  removeElem(3, l) //> res19: List[Int] = List(5, 0, 1, 3, 4, 5, 6)

  1 :: List(2) //> res20: List[Int] = List(1, 2)

  val n = 7 //> n  : Int = 7
  def isPrime(x: Int): Boolean = (2 until x) forall (d => n % d != 0)
  //> isPrime: (x: Int)Boolean
  for {
    i <- 1 until n
    j <- 1 until i
    if isPrime(i + j)
  } yield (i, j) //> res21: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (3
  //| ,1), (3,2), (4,1), (4,2), (4,3), (5,1), (5,2), (6,1))

  val s = Set(5, 0, 1, 2, 3, 4, 5, 6) //> s  : scala.collection.immutable.Set[Int] = Set(0, 5, 1, 6, 2, 3, 4)

  s map { f =>
    if (f > 1) f / 2 //> res22: scala.collection.immutable.Set[AnyVal] = Set((), 2, 3, 1)
  }
  s map (f => f / 2) //> res23: scala.collection.immutable.Set[Int] = Set(0, 2, 3, 1)
  s map (_ / 2) //> res24: scala.collection.immutable.Set[Int] = Set(0, 2, 3, 1)

  val r: Range = 1 until 5 //> r  : Range = Range(1, 2, 3, 4)
  val sr: Range = 1 to 5 //> sr  : Range = Range(1, 2, 3, 4, 5)

  println(l.flatMap(f => List(f % 2 == 0, f)))

  println(l map(f => List(f % 2 == 0, f)))
  println(l map(f => (f % 2 == 0, f)))

}