object test {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(57); 
  println("Welcome to the Scala worksheet");$skip(53); 
  val l = List("Every", "student", "likes", "Scala");System.out.println("""l  : List[String] = """ + $show(l ));$skip(31); val res$0 = 
                          l(0);System.out.println("""res0: String = """ + $show(res$0));$skip(49); val res$1 = 
  l.groupBy((element: String) => element.length);System.out.println("""res1: scala.collection.immutable.Map[Int,List[String]] = """ + $show(res$1));$skip(43); 
  val w = "Welcome to the Scala worksheet";System.out.println("""w  : String = """ + $show(w ));$skip(166); 
  //w.toList.sort().groupBy((e: Char) => e)
  //val y = w.toList.groupBy((e: Char) => e).toList
  val y = w.map(e => e.toLower).toList.groupBy((e: Char) => e).toList;System.out.println("""y  : List[(Char, List[Char])] = """ + $show(y ));$skip(112); val res$2 = 
  //val x = w.toList.groupBy((e: Char) => e).toList.sortWith(_.compareTo(_) <0)
  y.map(e => (e._1, e._2.size));System.out.println("""res2: List[(Char, Int)] = """ + $show(res$2));$skip(77); val res$3 = 
  y.map(e => (e._1, e._2.size)).sortWith((s, t) => s._1.compareTo(t._1) < 0)
  
  type Word = String
  type Sentence = List[Word];System.out.println("""res3: List[(Char, Int)] = """ + $show(res$3));$skip(81); 
  val s = List("abcd", "e");System.out.println("""s  : List[String] = """ + $show(s ))}
  
}
