import week3._

val a: Array[NonEmpty] = Array(new NonEmpty(1, Empty, Empty))

//In Scala Arrays are not covariant because the are mutable
val b: Array[IntSet] = a
b(0) = Empty
val s: NonEmpty = a(0)


