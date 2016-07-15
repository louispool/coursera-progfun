def flatten(xs: List[Any]): List[Any] = xs match {
  case Nil => Nil
  case y :: ys => y match {
    case zs : List[Any] => flatten(zs) ++ flatten(ys)
    case z => z :: flatten(ys)
  }
}
flatten(List(List(1, 1), 2, List(2, List(5, 8))))

def mapFun[T, U](xs: List[T], f: T => U): List[U] =
  (xs foldRight List[U]()) (f(_) :: _)

def lengthFun[T](xs: List[T]): Int =
  (xs foldRight 0) ((x, y) => 1 + y)

val l = List(1, 2, 3, 4, 5)

l map (x => x*2)
l.length

mapFun(l, (x: Int) => x*2)
lengthFun(l)




