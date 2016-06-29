List("apples", "oranges", "pears")
List(1, 2, 3, 4)
List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1))
List()

val fruit = "apples" :: ("oranges" :: ("pears" :: Nil))

fruit.head
fruit.tail.head

val nums = 1 :: 2 :: 3 :: 4 :: Nil
val numsequiv = Nil.::(4).::(3).::(2).::(1)  // :: ~ prepend function

val diag3 = (1 :: 0 :: 0 :: Nil) :: (0 :: 1 :: 0 :: Nil) :: (0 :: 0 :: 1 :: Nil) :: Nil

//insertion sort
def isort(xs: List[Int]): List[Int] = xs match {
  case Nil => Nil
  case y :: ys => insert(y, isort(ys))
}

def insert(x: Int, xs: List[Int]): List[Int] = xs match {
  case Nil => List(x)
  case y :: ys => if (x <= y) x :: xs else y :: insert(x, ys)
}

isort(List(7, 3, 9, 2))


