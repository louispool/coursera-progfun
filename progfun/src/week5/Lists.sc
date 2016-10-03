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

def msort[T](xs: List[T])(lt: (T, T) => Boolean): List[T] = {
  val n = xs.length/2
  if (n == 0) xs
  else {
    def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
      case (Nil, _) => ys
      case (_, Nil) => xs
      case (x :: xs1, y :: ys1) => if (lt(x, y)) x :: merge(xs1, ys) else y :: merge(xs, ys1)
    }

    val (fst, snd) = xs splitAt n
    merge(msort(fst)(lt), msort(snd)(lt))
  }
}

val nums = List(2, -4, 5, 7, 1)
val fruits = List("apple", "pineapple", "orange", "banana")

msort(nums)((x, y) => x < y)
msort(fruits)((x: String, y: String) => x.compareTo(y) < 0)

//Using an implicit comparator
def msortImplicit[T](xs: List[T])(implicit ord: Ordering[T]): List[T] = {
  val n = xs.length / 2
  if (n == 0) xs
  else {
    def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
      case (Nil, _) => ys
      case (_, Nil) => xs
      case (x :: xs1, y :: ys1) => if (ord.lt(x, y)) x :: merge(xs1, ys) else y :: merge(xs, ys1)
    }

    val (fst, snd) = xs splitAt n
    merge(msortImplicit(fst), msortImplicit(snd))
  }
}

msortImplicit(nums)
msortImplicit(fruits)

//MAP

//Transform each element of a list
def scaleList(xs: List[Double], factor: Double): List[Double] = xs match {
  case Nil => xs
  case y :: ys => y * factor :: scaleList(ys, factor)
}

scaleList(List(1, 2, 3, 4), 2)

def scaleListUsingMap(xs: List[Double], factor: Double): List[Double] = xs.map(x => x*factor)

scaleListUsingMap(List(1, 2, 3, 4), 2)


//FILTER

//Selection of elements of a list

def posElems(xs: List[Int]): List[Int] = xs match {
  case Nil => xs
  case y :: ys => if (y > 0) y :: posElems(ys) else posElems(ys)
}

def posElemsUsingFilter(xs: List[Int]): List[Int] = xs.filter(x => x > 0)

//OTHER

nums filter (x => x > 0)
nums filterNot (x => x > 0)
nums partition (x => x > 0)

nums takeWhile (x => x > 0)
nums dropWhile (x => x > 0)
nums span (x => x > 0)

//Pack consecutive duplicates of list elements into sublists
def pack[T](xs: List[T]): List[List[T]] = xs match {
  case Nil => Nil
  case x :: xs1 => {
    val (first, rest) = xs span (y => y == x)
    first :: pack(rest)
  }
}

val data = List("a", "a", "a", "b", "c", "c", "a")
pack(data)

def encode[T](xs: List[T]): List[(T, Int)] = pack(xs).map(ys => (ys.head, ys.size))
encode(data)

/**
  * Combine the elements of a list using a given operator i.e
  *
  * sum(List(x1, ..., xn))     = 0 + x1 + ... + xn
  * product(List(x1, ..., xn)) = 1 * x1 * ... * xn
  */

//We can implement this using recursion
def sumRecursive(xs: List[Int]): Int = xs match {
  case Nil => 0
  case y :: ys => y + sum(ys)
}

//Or reduce with a Left Leaning tree
def sum(xs: List[Int]) = (0 :: xs) reduceLeft(_ + _)  //((x, y) => x + y)
def product(xs: List[Int]) = (1 :: xs) reduceLeft(_ * _) // ((x, y) => x*y)

//Or fold with a left leaning tree (using an accumulator)
def sumFold(xs: List[Int]) = (xs foldLeft 0) (_ + _) //((x, y) => x + y)
def productFold(xs: List[Int]) = (xs foldLeft 1) (_ * _) // ((x, y) => x*y)

/**
  * List(x1, ..., xn) reduceLeft op    = (...(x1 op x2) op x3) op ...) op xn
  * List(x1, ..., xn).foldLeft(z)(op)  = (...( z op x1) op x2) op ...) op xn
  *
  * i.e. List(a,b,c).foldLeft(e)(f) = f(f(f(e, a), b), c)
  *
  * Reduce left (left leaning tree):
  *
  *           op
  *          /  \
  *         .   xn
  *        /
  *       op
  *      /  \
  *     op  x3
  *    /  \
  *  x1   x2
  *
  * Fold left (left leaning tree with accumulator z):
  *
  *           op
  *          /  \
  *         .   xn
  *        /
  *       op
  *      /  \
  *     op  x2
  *    /  \
  *   z   x1
  */

/**
  * List(x1, ..., xn) reduceRight op   = x1 op (... (x{n-1} op xn) ...)
  * List(x1, ..., xn).foldRight(z)(op) = x1 op (... (    xn op  z) ...)
  *
  * i.e. List(a,b,c).foldRight(e)(f) = f(a, f(b, f(c, e)))
  *
  * Reduce right (right leaning tree):
  *
  *    op
  *   /  \
  *  x1   op
  *      /  \
  *     x2   .
  *           \
  *            op
  *           /  \
  *        xn-1   xn
  *
  * Fold right (right leaning tree with accumulator z):
  *
  *    op
  *   /  \
  *  x1   op
  *      /  \
  *     x2   .
  *           \
  *            op
  *           /  \
  *        xn     z
  */


//Difference between fold left and fold right
def concatFoldRight[T](xs: List[T], ys: List[T]): List[T] = (xs foldRight ys)(_ :: _)

//def concatFoldLeft[T](xs: List[T], ys: List[T]): List[T] = (xs foldLeft ys) (_ :: _)

//Operations on collections are key to functional programming
//map: apply function to each element
List (1, 3, 8).map(x => x * x) == List(1, 9, 64)

//fold: combine elements with a given operation
List (1, 3, 8).fold(100)((s, x) => s + x) == 112

//scan: combine folds of all list prefixes
List ( 1, 3, 8).scan(100)((s, x) => s + x) == List(100, 101, 104, 112)



