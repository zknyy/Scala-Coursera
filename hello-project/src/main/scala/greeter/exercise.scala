package greeter

import scala.annotation.tailrec

object exercise {

  def main(args: Array[String]): Unit = {

    println("factorial(5):" + factorial(5));
  }

  def factorial(n: Int): Int = {
    @tailrec
    def loop(acc: Int, n: Int): Int =
      if (n == 0) acc
      else loop(acc * n, n - 1)
    loop(1, n)
  }
}