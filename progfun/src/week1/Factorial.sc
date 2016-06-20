def factorial(n: Int): Int =
  if (n == 0) 1 else n * factorial(n-1)

def tailrecfactorial(n: Int)  = {
  def loop(acc: Int, n: Int): Int = {
    if (n == 0) acc
    else loop (acc * n, n-1)
  }
  loop(1, n)
}

factorial(4)
tailrecfactorial(4)
