object test {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(57); 
  println("Welcome to the Scala worksheet")
  
  import example.Lists._;$skip(50); val res$0 = 
  
  max(List(1,3,2));System.out.println("""res0: Int = """ + $show(res$0));$skip(31); 
  
  def loop: Boolean = loop;System.out.println("""loop: => Boolean""");$skip(18); 
  
  def x = loop;System.out.println("""x: => Boolean""");$skip(225); 
  
  //println(loop)
  //val y= loop
  /*
  def and(x:Boolean, y:Boolean)=
  if (x) y else false
  
  
  and (true, false)
  
  and (true, true)*/
  def and(x:Boolean, y: => Boolean)=  //=> call by name
  if (x) y else false;System.out.println("""and: (x: Boolean, y: => Boolean)Boolean""");$skip(22); val res$1 = 
  
  and(false, loop);System.out.println("""res1: Boolean = """ + $show(res$1));$skip(47); 
  def g(): String ="this String gets lost too";System.out.println("""g: ()String""");$skip(74); 
  //def f(): Unit = "this String gets lost"
  //println(f);
  println(g);$skip(55); ;
  
 	val filesHere = (new java.io.File(".")).listFiles;System.out.println("""filesHere  : Array[java.io.File] = """ + $show(filesHere ));$skip(39); 
	for (file <- filesHere) println(file);$skip(101); 
                                                  

def makeIncreaser(more: Int) = (x: Int) => x + 1;System.out.println("""makeIncreaser: (more: Int)Int => Int""");$skip(27); 


val t = makeIncreaser(1);System.out.println("""t  : Int => Int = """ + $show(t ));$skip(12); 
println(t);$skip(106); ;


//size=6
//4--  index = 3
//take 			i = index
//takeright	i = size - 3 -1
val l = List(5,0,1,2,3,4,5,6);System.out.println("""l  : List[Int] = """ + $show(l ));$skip(48); 
 for (i <- 0 to l.length-1) {
 	print(l(i)+" ")
 };$skip(14); 
 println();$skip(27); val res$2 = 
l.take(3):::l.takeRight(2);System.out.println("""res2: List[Int] = """ + $show(res$2));$skip(111); 

def removeElem[T >: Nothing](i:Int,l:List[T]):List[T] = {
        l.take(i):::l.takeRight(l.size-i-1)
      };System.out.println("""removeElem: [T](i: Int, l: List[T])List[T]""");$skip(7); val res$3 = 
l.head;System.out.println("""res3: Int = """ + $show(res$3));$skip(7); val res$4 = 
l.tail;System.out.println("""res4: List[Int] = """ + $show(res$4));$skip(7); val res$5 = 
l.init;System.out.println("""res5: List[Int] = """ + $show(res$5));$skip(7); val res$6 = 
l.last;System.out.println("""res6: Int = """ + $show(res$6));$skip(9); val res$7 = 
l take 3;System.out.println("""res7: List[Int] = """ + $show(res$7));$skip(9); val res$8 = 
l drop 3;System.out.println("""res8: List[Int] = """ + $show(res$8));$skip(12); val res$9 = 
l splitAt 3;System.out.println("""res9: (List[Int], List[Int]) = """ + $show(res$9));$skip(21); val res$10 = 

l filter (x => x>3);System.out.println("""res10: List[Int] = """ + $show(res$10));$skip(21); val res$11 = 
l filterNot (x=>x>3);System.out.println("""res11: List[Int] = """ + $show(res$11));$skip(20); val res$12 = 
l partition(x=>x>3);System.out.println("""res12: (List[Int], List[Int]) = """ + $show(res$12));$skip(27); val res$13 = 
      
l takeWhile(x=>x>3);System.out.println("""res13: List[Int] = """ + $show(res$13));$skip(20); val res$14 = 
l dropWhile(x=>x>3);System.out.println("""res14: List[Int] = """ + $show(res$14));$skip(15); val res$15 = 
l span(x=>x>3);System.out.println("""res15: (List[Int], List[Int]) = """ + $show(res$15));$skip(23); val res$16 = 
      
removeElem(0,l);System.out.println("""res16: List[Int] = """ + $show(res$16));$skip(23); val res$17 = 
      
removeElem(1,l);System.out.println("""res17: List[Int] = """ + $show(res$17));$skip(23); val res$18 = 
      
removeElem(2,l);System.out.println("""res18: List[Int] = """ + $show(res$18));$skip(23); val res$19 = 
      
removeElem(3,l);System.out.println("""res19: List[Int] = """ + $show(res$19));$skip(18); val res$20 = 
      
1::List(2);System.out.println("""res20: List[Int] = """ + $show(res$20));$skip(9); 

val n=7;System.out.println("""n  : Int = """ + $show(n ));$skip(58); 
def isPrime(x:Int):Boolean= (2 until x) forall(d=>n%d!=0);System.out.println("""isPrime: (x: Int)Boolean""");$skip(62); val res$21 = 
for{
i <-1 until n
j <-1 until i
if isPrime(i+j)
}yield (i,j);System.out.println("""res21: scala.collection.immutable.IndexedSeq[(Int, Int)] = """ + $show(res$21));$skip(31); 


val s = Set(5,0,1,2,3,4,5,6);System.out.println("""s  : scala.collection.immutable.Set[Int] = """ + $show(s ));$skip(24); val res$22 = 

s map {f=>
if (f>1)f/2
};System.out.println("""res22: scala.collection.immutable.Set[AnyVal] = """ + $show(res$22));$skip(72); val res$23 = 
//s flatMap(f=>List(f.size))// filter (f=>f.isEmpty)
s map (f => f/2);System.out.println("""res23: scala.collection.immutable.Set[Int] = """ + $show(res$23));$skip(12); val res$24 = 
s map (_/2);System.out.println("""res24: scala.collection.immutable.Set[Int] = """ + $show(res$24));$skip(28); 



val r: Range = 1 until 5;System.out.println("""r  : Range = """ + $show(r ));$skip(23); 
val sr: Range = 1 to 5;System.out.println("""sr  : Range = """ + $show(sr ));$skip(18); 

val tr=("s","r");System.out.println("""tr  : (String, String) = """ + $show(tr ));$skip(6); val res$25 = 
tr._2;System.out.println("""res25: String = """ + $show(res$25));$skip(22); val res$26 = 
List(5,0,1,2,3,4,5,6);System.out.println("""res26: List[Int] = """ + $show(res$26));$skip(72); 
val fl=  List(List(('a', 1), ('d', 4)), List(('l', 1)), List(('r', 1)));System.out.println("""fl  : List[List[(Char, Int)]] = """ + $show(fl ));$skip(11); val res$27 = 
fl.flatten;System.out.println("""res27: List[(Char, Int)] = """ + $show(res$27));$skip(45); 


  val w = "Welcome to the Scala worksheet";System.out.println("""w  : String = """ + $show(w ));$skip(166); 
  //w.toList.sort().groupBy((e: Char) => e)
  //val y = w.toList.groupBy((e: Char) => e).toList
  val y = w.map(e => e.toLower).toList.groupBy((e: Char) => e).toList;System.out.println("""y  : List[(Char, List[Char])] = """ + $show(y ))}
  
}
