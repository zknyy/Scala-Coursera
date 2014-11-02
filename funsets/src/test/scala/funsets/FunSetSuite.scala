package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  test("adding ints") {
    assert(1 + 2 === 3)
  }

  import FunSets._
  //
  //  test("contains is implemented") {
  //    assert(contains(x => true, 100))
  //  }

  test("contains is implemented x>0") {
    assert(contains(x => x > 0, 100))
  }
  test("contains is implemented x<-1") {
    assert(contains(x => (x < -1), -2))
  }
  test("contains is implemented x = 0") {
    assert(contains(x => x == 0, 0))
  }
  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = singletonSet(4)
    val s5 = singletonSet(5)
    val s7 = singletonSet(7)
    val s1000 = singletonSet(1000)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  //  ignore("singletonSet(1) contains 1") {
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton1")
      assert(contains(s2, 2), "Singleton2")
    }
  }

  test("union contains all elements") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("union filter elements") {
    new TestSets {
      val s12 = union(s1, s2)
      val s123 = union(union(s1, s2),s3)
      filter(s12,x => x < 2)
      assert(contains(filter(s12,x => x < 2),1), "filter 1")
      assert(contains(filter(s123,x => x >1),2), "filter 12-2")
      assert(contains(filter(s123,x => x >1),3), "filter 12-3")
      assert(!contains(filter(s123,x =>x >1),1), "filter -1")
    }
  }

  test("union forall elements") {
    new TestSets {
      val s13 = union(s1, s3)
      val s134 = union(s13,s4)
      val s1345 = union(s134,s5)
      val s13457 = union(s1345,s7)
      val s134571000 = union(s13457,s1000)
      assert(forall(s13457, x=>x<8), "forall s13457")
      assert(forall(s134571000, x=>x>0), "forall s134571000")
      assert(!forall(s134571000, x=>x<10), "!forall s134571000")
//      assert(!forall(s, 3), "Union 3")
    }
  }


  test("union exists elements") {
    println("---------union exists elements--------")
    new TestSets {
      val s13 = union(s1, s3)
      val s134 = union(s13,s4)
      val s1345 = union(s134,s5)
      val s13457 = union(s1345,s7)
      val s134571000 = union(s13457,s1000)
      assert(exists(s13457, x=>x==5), "exists s13457")
      assert(exists(s134571000, x=>x==7), "exists s134571000")
      assert(!exists(s134571000, x=>x==2), "!exists s134571000")

      printSet(diff(s134571000,s13))
    }
  }
  
  test("map elements") {
    println("---------map elements--------")
    new TestSets {
      val s13 = union(s1, s3)
      val s134 = union(s13,s4)
      val s1345 = union(s134,s5)
      val s13457 = union(s1345,s7)
      val s134571000 = union(s13457,s1000)
      printSet(map(s13457, x=>x*2))
//      assert(printSet(map(s13457, x=>x*2)), "exists s13457")
//      assert(map(s134571000, x=>x-10), "exists s134571000")
//      assert(!exists(s134571000, x=>x==2), "!exists s134571000")
//
//      printSet(diff(s134571000,s13))
    }
  }
  
}
