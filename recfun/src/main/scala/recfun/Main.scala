package recfun
import common._
import scala.annotation.tailrec

object Main {
  def main(args: Array[String]) {
    //    println("Pascal's Triangle")
    //    for (row <- 0 to 10) {
    //      for (col <- 0 to row)
    //        print(pascal(col, row) + " ")
    //      println()
    //    }
    println("countChange(4,List(1,2)):" + countChange(4, List(1, 2)));
  }

  /**
   * Exercise 1
   */
  //@tailrec
  def pascal(c: Int, r: Int): Int = {
    if ((c == 0) || (r == 0) || (r == c)) 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {

    @tailrec
    def check(parList: List[Char], restList: List[Char]): Boolean = {
      if (restList.isEmpty) true //finished

      else if (restList.head == '(') check(parList :+ restList.head, restList.tail) // put in a new one

      else if (restList.head == ')') if (parList.isEmpty) false // a ) can not be paired 

      else if (parList.last == '(') check(parList.init, restList.tail) //	( get pair,eliminate one

      else false //  ) get deep, impossible

      else check(parList, restList.tail)
    }
    check(List(), chars)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {

    if(coins.size==0) return 0
    var count = 0;
    var i = 0
    def combine(solListList: List[List[Int]], coinList: List[Int]): Unit = {
      //      while (i <= money / coinList.last) {
      if (i <= money / coinList.last) {
        var comListList: List[List[Int]] = List();
        for (solList <- solListList) {
          for (c <- coinList) {
            val tempList = solList :+ c;
            if (tempList.sum <= money) comListList = comListList :+ (tempList.sorted.reverse)
          }
        }
        comListList = comListList.distinct

//        println("====================="+comListList)
        for (solList <- comListList) {
//          print("    solList:")
//          solList.foreach(sol => print(" " + sol))
//          println()
          if (money == solList.sum) count = count + 1
        }

//        println("=====================")
        i = i + 1
        val inputListList = comListList;
        combine(inputListList, coinList)
      }

    }
    val orderCoins: List[Int] = coins.sorted.reverse
    var initList: List[List[Int]] = List();
    for (i <- 0 to coins.size) {
      val tempList: List[Int] = List();
      initList = initList :+ tempList
    }

    val emptyListList: List[List[Int]] = initList
    combine(emptyListList, orderCoins)
    count

    //    val solListList = combine(emptyListList, orderCoins)
    //      println("solListList:" + solListList)
    //      for (solList <- solListList) {
    //        print("    solList:")
    //        solList.foreach(sol => print(" " + sol))
    //        println()
    //        if (money == solList.sum) count = count + 1
    //      }
    //      i = i + 1
    //    }
    //
    //    count

    /*
    var count = 0;

    def combine(sol: List[List[Int]], coins: List[Int]): List[List[Int]] = {
      var comList: List[List[Int]] = List();
      for (sList <- sol) {
        for (c <- coins) {
          val temp = sList :+ c;
          if (temp.sum <= money) comList = comList :+ (temp.sorted.reverse)
        }
      }
      comList.removeDuplicates
    }

    val orderCoins: List[Int] = coins.sorted.reverse
    var emptyList: List[List[Int]] = List();
    for (i <- 0 to coins.size) {
      val tempList: List[Int] = List();
      emptyList = emptyList :+ tempList
    }

    var i = 0
    while (i < money / orderCoins.last) {
      val solListList = combine(emptyList, orderCoins)
      println("solListList:" + solListList)
      for (solList <- solListList) {
        print("    solList:")
        solList.foreach(sol => print(" " + sol))
        println()
        if (money == solList.sum) count = count + 1
      }
      i = i + 1
    }

    count
*/
    /*
    var r = 0;
    def getSolution(count: Int, sol: List[Int], money: Int, coins: List[Int]): Int = {

      if (coins.isEmpty) count
      else if (money > sol.sum) getSolution(count, sol :+ coins.head, money, coins)
      else if (money < sol.sum) getSolution(count, sol.init :+ coins.tail.head, money, coins.tail)
      else //(money==sol.sum)
      {
        r = r + 1
        println("r:"+r+" count:"+count+" sol:"+sol +" money:"+money +" coins:"+coins)
        for (i <- sol) {
          getSolution(count, List(), i, coins.filter(s => s < i))
        }
      }
      r
    }
    
    //coins, the list sorted by decrease
    getSolution(0, List(), money, coins.sorted.reverse)
    
    */
    ////////////////==================================================

    /*
    var result = 0;
//    var i:Int =0;
    for(i <-0 until coins.length){
      coins.drop(i)
    }
    
//    def forLoop(n: Int) {
//    var total = 0;
//    for (i <- 0 until n) {
//      total += i
//    }

    0*/

    //    var r = 0;
    //    def getSolution(count: Int, sol: List[Int], money: Int, coins: List[Int]): Int = {
    //
    //      if (coins.isEmpty) count
    //      else if (money > sol.sum) getSolution(count, sol :+ coins.head, money, coins)
    //      else if (money < sol.sum) getSolution(count, sol.init :+ coins.tail.head, money, coins.tail)
    //      else //(money==sol.sum)
    //      {
    //        r = r + 1
    //        println("r:"+r+" count:"+count+" sol:"+sol +" money:"+money +" coins:"+coins)
    //        for (i <- sol) {
    //          getSolution(count, List(), i, coins.filter(s => s < i))
    //        }
    //      }
    //      r
    //    }
    //    
    //    
    //    //coins, the list sorted by decrease
    //    getSolution(0, List(), money, coins.sorted.reverse)
  }

}
