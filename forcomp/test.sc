object test {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  val l = List("Every", "student", "likes", "Scala")
                                                  //> l  : List[String] = List(Every, student, likes, Scala)
                          l(0)                    //> res0: String = Every
  l.groupBy((element: String) => element.length)  //> res1: scala.collection.immutable.Map[Int,List[String]] = Map(5 -> List(Every
                                                  //| , likes, Scala), 7 -> List(student))
  val w = "Welcome to the Scala worksheet"        //> w  : String = Welcome to the Scala worksheet
  //w.toList.sort().groupBy((e: Char) => e)
  //val y = w.toList.groupBy((e: Char) => e).toList
  val y = w.map(e => e.toLower).toList.groupBy((e: Char) => e).toList
                                                  //> y  : List[(Char, List[Char])] = List((e,List(e, e, e, e, e)), (s,List(s, s))
                                                  //| , (t,List(t, t, t)), (a,List(a, a)), (m,List(m)), ( ,List( ,  ,  ,  )), (l,L
                                                  //| ist(l, l)), (c,List(c, c)), (h,List(h, h)), (r,List(r)), (w,List(w, w)), (k,
                                                  //| List(k)), (o,List(o, o, o)))
  //val x = w.toList.groupBy((e: Char) => e).toList.sortWith(_.compareTo(_) <0)
  y.map(e => (e._1, e._2.size))                   //> res2: List[(Char, Int)] = List((e,5), (s,2), (t,3), (a,2), (m,1), ( ,4), (l,
                                                  //| 2), (c,2), (h,2), (r,1), (w,2), (k,1), (o,3))
  y.map(e => (e._1, e._2.size)).sortWith((s, t) => s._1.compareTo(t._1) < 0)
                                                  //> res3: List[(Char, Int)] = List(( ,4), (a,2), (c,2), (e,5), (h,2), (k,1), (l,
                                                  //| 2), (m,1), (o,3), (r,1), (s,2), (t,3), (w,2))
  
  type Word = String
  type Sentence = List[Word]
  val s = List("abcd", "e")                       //> s  : List[String] = List(abcd, e)
  
}