def sum(f: Int => Int): (Int, Int) => Int = {
  def sumF(a: Int, b: Int): Int = {
    if (a > b) 0
    else f(a) + sumF(a + 1, b)
  }
  sumF
}

def sumInts  = sum(x => x)
def sumCubes = sum(x => x * x * x)

sumInts(1, 10)
sumCubes(1, 10)

sum(x => x)(1, 10)
sum(x => x * x * x)(1, 10)

//Multiple parameter lists
def sumRed(f: Int => Int)(a: Int, b: Int):Int = {
    if (a > b) 0
    else f(a) + sum(f)(a + 1, b)
}

sumRed(x => x)(1, 10)
sumRed(x => x * x * x)(1, 10)

def product(f: Int => Int)(a: Int, b: Int): Int = {
  if (a > b) 1
  else f(a) * product(f)(a + 1, b)
}

product(x => x)(1, 3)

def fact(n: Int) = product(x => x)(1, n)
fact(5)

def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int = {
  if (a > b) zero
  else combine(f(a), mapReduce(f, combine, zero)(a + 1, b))
}

def newProduct(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x, y) => x*y, 1)(a, b)
def newSum(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x, y) => x + y, 0)(a, b)

def newFact(n: Int) = newProduct(x => x)(1, n)
fact(5)

newSum(x => x)(1, 10)
newSum(x => x * x * x)(1, 10)




