import week4._

def singleton[T](elem: T) = new Cons[T](elem, Nil)

singleton(1)
singleton(true)

def nth[T](n: Int, xs: List[T]): T = {
  if (xs.isEmpty) throw new IndexOutOfBoundsException
  else if (n == 0) xs.head
  else nth(n-1, xs.tail)
}

val list = new Cons(1, new Cons(2, new Cons(3, Nil)))

nth(2, list)
//nth(3, list)  //Exception

def create(n: Int, l: List[Int]): List[Int] = {
  if (n == 0) l
  else create(n-1, new Cons(n, l))
}

def printList[T](xs: List[T]): Any = {
  if (xs.isEmpty) println()
  else {
    print(xs.head + " ")
    printList(xs.tail)
  }
}

printList(list)

val l = create(5, Nil)

print(l.head + " ")
print(printList(l))

val r = l.reverse

print(r.head + " ")
print(printList(r))

List()
List(1)
List(1, 2)
List(1, 2, 3)
List(4.0, 3, 2, 1)

def f(xs: List[NonEmpty], x: Empty) = xs prepend x







