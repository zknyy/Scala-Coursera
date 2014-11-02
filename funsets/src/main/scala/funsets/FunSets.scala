package funsets

import common._

/**
 * 2. Purely Functional Sets.
 */
object FunSets {
  /**
   * We represent a set by its characteristic function, i.e.
   * its `contains` predicate.
   */
  type Set = Int => Boolean

  /**
   * Indicates whether a set contains a given element.
   */
  def contains(s: Set, elem: Int): Boolean = s(elem)

  /**
   * Returns the set of the one given element.
   */
  def singletonSet(elem: Int): Set =
    {
      def set(test: Int): Boolean = {
        elem == test
      }
      set
    }

  /**
   * Returns the union of the two given sets,
   * the sets of all elements that are in either `s` or `t`.
   */
  def union(s: Set, t: Set): Set = {
    def set(elem: Int): Boolean = {
      s(elem) || t(elem)
    }
    set
  }

  /**
   * Returns the intersection of the two given sets,
   * the set of all elements that are both in `s` and `t`.
   */
  def intersect(s: Set, t: Set): Set = {
    def set(elem: Int): Boolean = {
      s(elem) && t(elem)
    }
    set
  }

  /**
   * Returns the difference of the two given sets,
   * the set of all elements of `s` that are not in `t`.
   */
  def diff(s: Set, t: Set): Set = {
    def set(elem: Int): Boolean = {
      s(elem) && (!t(elem))
    }
    set
  }

  /**
   * Returns the subset of `s` for which `p` holds.
   */
  def filter(s: Set, p: Int => Boolean): Set = {
    def set(elem: Int): Boolean = {
      s(elem) && (p(elem))
    }
    set

  }

  /**
   * The bounds for `forall` and `exists` are +/- 1000.
   */
  val bound = 1000

  /**
   * Returns whether all bounded integers within `s` satisfy `p`.
   */
  def forall(s: Set, p: Int => Boolean): Boolean = {
    def iter(a: Int): Boolean = {
      if (a > 1000) true
      else if (s(a) && (!p(a)))
        false
      else iter(a + 1)
    }
    iter(-1000)
  }

  /**
   * Returns whether there exists a bounded integer within `s`
   * that satisfies `p`.
   */
  def exists(s: Set, p: Int => Boolean): Boolean = {
    def iter(a: Int): Boolean = {
      if (a > 1000) false
      else if (s(a) && p(a)) true
      else iter(a + 1)
    }
    iter(-1000)
  }

  /**
   * Returns a set transformed by applying `f` to each element of `s`.
   */
  def map(s: Set, f: Int => Int): Set =
    // ???
    {
      //singletonSet
      def iter(s: Set, u: Set, a: Int): Set = {
        val temp: Set = singletonSet(a)
        val fset: Set = singletonSet(f(a))
        if (a > 1000) u
        else if (contains(s, a))
          if (u == null) iter(diff(s, temp), fset, a + 1)
          else iter(diff(s, temp), union(u, fset), a + 1)
        else iter(s, u, a + 1)
      }
      iter(s, null, 1)
    }

  /**
   * Displays the contents of a set
   */
  def toString(s: Set): String = {
    val xs = for (i <- -bound to bound if contains(s, i)) yield i
    xs.mkString("{", ",", "}")
  }

  /**
   * Prints the contents of a set on the console.
   */
  def printSet(s: Set) {
    println(toString(s))
  }
}
