package week4

import java.util.NoSuchElementException

trait List[+T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
  def reverse: List[T]
  def prepend[U >:T](elem: U): List[U] = new Cons(elem, this)
}

class Cons[T] (val head: T, val tail: List[T]) extends List[T] {
  def isEmpty = false

  def reverse: List[T] = {
    def loop(in: List[T], out: List[T]): List[T] = {
      if (in.isEmpty) out
      else loop(in.tail, new Cons(in.head, out))
    }
    loop(this, Nil)
  }
}

object Nil extends List[Nothing] {
  def isEmpty = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
  def reverse: Nothing = throw new NoSuchElementException("Nil.reverse")
}

object List {
  //List(1, 2) = list.apply(1, 2)
  def apply[T](x1: T, x2: T): List[T] = new Cons[T](x1, new Cons[T](x2, Nil))
  //List(1) = list.apply(1)
  def apply[T](x1: T): List[T] = new Cons[T](x1, Nil)
  //List() = list.apply()
  def apply[T]() = Nil
}

