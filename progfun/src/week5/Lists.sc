//Complexity of List.last = O(n)
def last[T](xs : List[T]): T = xs match {
  case List() => throw new Error("Last of empty list")
  case List(x) => x
  case y :: ys => last(ys)
}

//Complexity of List.init = O(n)
def init[T](xs: List[T]): List[T] = xs match {
  case List() => throw new Error("Init of empty list")
  case List(x) => List()
  case y :: ys => y :: init(ys)
}

//Complexity of xs ::: ys = ys.:::(xs) = O(n)
def concat[T](xs: List[T], ys: List[T]): List[T] = xs match { //prepend xs to ys
  case List() => ys
  case z :: zs => z :: concat(zs, ys)
}

//Complexity of reverse = O(n²)
def reverse[T](xs: List[T]): List[T] = xs match {
  case List() => xs
  case y :: ys => reverse(ys) ++ List(y)  // Concatenation!
}

def removeAt[T](n: Int, xs: List[T]) = (xs take n) ::: (xs drop n+1)

removeAt(1,  List('a', 'b', 'c', 'd'))

def flatten(xs: List[Any]): List[Any] = ???

flatten(List(List(1, 1), 2, List(2, List(5, 8))))


