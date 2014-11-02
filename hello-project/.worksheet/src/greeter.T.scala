package greeter

object T {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(72); 
  println("Welcome to the Scala worksheet");$skip(14); 

  val x = 1;System.out.println("""x  : Int = """ + $show(x ));$skip(31); 
  def increase(i: Int) = i + 1;System.out.println("""increase: (i: Int)Int""");$skip(14); val res$0 = 
  increase(x);System.out.println("""res0: Int = """ + $show(res$0));$skip(24); 
  val l = List(1, 3, 2);System.out.println("""l  : List[Int] = """ + $show(l ));$skip(9); val res$1 = 
  l.tail;System.out.println("""res1: List[Int] = """ + $show(res$1));$skip(9); val res$2 = 
  l.head;System.out.println("""res2: Int = """ + $show(res$2));$skip(5); val res$3 = 
  l.;System.out.println("""res3: <error> = """ + $show(res$3))}
}
