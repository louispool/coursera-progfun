package week3

abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x:Int): Boolean
}
