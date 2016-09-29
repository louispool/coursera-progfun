/**
  * call-by-value functions compute the passed-in expression's value before calling the function,
  * thus the same value is accessed every time. However, call-by-name functions recompute the
  * passed-in expression's value every time it is accessed.
  */


//Function with side effect
def something() = {
  println("calling something")
  1 // return value
}

//call-by-value style (x: Int)
def callByValue(x: Int) = {
  println("x1=" + x)
  println("x2=" + x)
}

//call-by-name style (x: => Int)
def callByName(x: => Int) = {
  println("x1=" + x)
  println("x2=" + x)
}

//Passes the result of executing something() - an int
callByValue(something())
//Passes the function something
callByName(something())
