def sum(f: Int => Int, a: Int, b: Int): Int = {
  if (a > b) 0
  else f(a) + sum(f, a + 1, b)
}

sum(x => x, 1, 5)
sum(x => x*x, 1, 5)

def tailrecsum(f: Int => Int, a: Int, b: Int): Int = {

  def loop(a: Int, acc: Int): Int = {
    if (a > b) acc
    else loop(a+1, f(a) + acc)
  }
  loop(a, 0)
}


tailrecsum(x => x, 1, 5)
tailrecsum(x => x*x, 1, 5)
