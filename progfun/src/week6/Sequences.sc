//Vector access = log32(N)

val nums = Vector(1, 2, 3, -88)
val people = Vector("Bob", "James", "John")

//Java Strings and Arrays are like Seq (but do not really inherit)
val arr = Array(1, 2, 3, 44)
arr map (x => x*2)

arr.sum
arr.max
arr.min

val str = "Hello World"
str filter (c => c.isUpper)
str exists (c => c.isUpper)
str forall (c => c.isUpper)

val pairs = List(1, 2, 3) zip str
pairs.unzip

str map (c => List('.', c))
str flatMap (c => List('.', c))

//Ranges are evenly spaced integers stored as a (Lower bound, Upper bound, Step value)
val r: Range = 1 until 5
val s: Range = 1 to 5
1 to 10 by 3
6 to 1 by -2

//Sum(1..N) of x*y
def scalarProduct(xs : Vector[Double], ys : Vector[Double]): Double = (xs zip ys).map { case (x, y) => x*y }.sum
scalarProduct(Vector(1, 2), Vector(3, 4))

def isPrime(n: Int): Boolean = (2 until n) forall (d => n % d != 0)

isPrime(4)
isPrime(2)
isPrime(17)

// Nested Sequences - example: find all (i, j) where 1 <= j < i < n such that i + j is prime
val n = 7
(1 until n) flatMap (i =>
  (1 until i) map (j => (i, j))) filter (pair => isPrime(pair._1 + pair._2))  //xs flatMap f = (xs map f).flatten

// Double for loop
val M = 2
val N = 4

(1 to M) flatMap (x => (1 to N) map (y => (x, y)))

// For expressions
case class Person(name: String, age: Int)

val persons = List(Person("Bob", 21), Person("Lula", 18), Person("Cindy", 33), Person("Blammo", 12))

for (p <- persons if p.age > 20) yield p.name //Equivalent to:
persons filter (p => p.age > 20) map (p => p.name)

// Express, (i, j) where 1 <= j < i < n such that i + j is prime, as a for expression
for {
  i <- 1 until n //Generators
  j <- 1 until i
  if isPrime(i + j) //Filter
} yield (i, j) //Result

//Sum(1..N) of x*y
def scalarProductAsFor(xs: Vector[Double], ys: Vector[Double]): Double =  (for ((x, y) <- xs zip ys) yield x*y).sum
scalarProductAsFor(Vector(1, 2), Vector(3, 4))

