
  import scala.swing._
object test {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(81); 
  println("Welcome to the Scala worksheet");$skip(32); 
  val a:List[Int]= List(1,2,3);System.out.println("""a  : List[Int] = """ + $show(a ));$skip(32); ;
  val b:List[Int]= List(1,2,3);System.out.println("""b  : List[Int] = """ + $show(b ));$skip(42); ;
  
  
  
  val c:List[Int]= List(1,3,2,3);System.out.println("""c  : List[Int] = """ + $show(c ));$skip(21); val res$0 = 
  c.removeDuplicates;System.out.println("""res0: List[Int] = """ + $show(res$0));$skip(39); 
  
  val x:List[List[Int]] = List(a,b);System.out.println("""x  : List[List[Int]] = """ + $show(x ));$skip(21); val res$1 = 
  x.removeDuplicates;System.out.println("""res1: List[List[Int]] = """ + $show(res$1))}
  
 
 
  
}
