
  import scala.swing._
object test {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  val a:List[Int]= List(1,2,3);                   //> a  : List[Int] = List(1, 2, 3)
  val b:List[Int]= List(1,2,3);                   //> b  : List[Int] = List(1, 2, 3)
  
  
  
  val c:List[Int]= List(1,3,2,3)                  //> c  : List[Int] = List(1, 3, 2, 3)
  c.removeDuplicates                              //> res0: List[Int] = List(1, 3, 2)
  
  val x:List[List[Int]] = List(a,b)               //> x  : List[List[Int]] = List(List(1, 2, 3), List(1, 2, 3))
  x.removeDuplicates                              //> res1: List[List[Int]] = List(List(1, 2, 3))
  
 
 
  
}