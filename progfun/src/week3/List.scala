package week3

import java.util.NoSuchElementException

trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
  def reverse: List[T]
}

class Cons[T] (val head: T, val tail: List[T]) extends List[T] {
  def isEmpty = false

  def reverse: List[T] = {
    def loop(in: List[T], out: List[T]): List[T] = {
      if (in.isEmpty) out
      else loop(in.tail, new Cons(in.head, out))
    }
    loop(this, new Nil)
  }
}

class Nil[T] extends List[T] {
  def isEmpty = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
  def reverse: Nothing = throw new NoSuchElementException("Nil.reverse")
}

