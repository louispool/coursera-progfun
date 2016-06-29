import week4._

//This method is replaced by the pattern matching eval method in Expr
def eval(e: Expr): Int = {
  if (e.isNumber) e.numValue
  else if (e.isSum) eval(e.leftOp) + eval(e.rightOp)
  else throw new Error("Unknown expression " + e)
}

eval(new Sum(new Num(1), new Num(2)))

def show(e: Expr): String = e match {
  case Num(n) => n.toString
  case Sum(e1, e2) => show(e1) + " + " + show(e2)
  case Prod(e1, e2) => show(e1) + " * " + show(e2)
}

val s = new Sum(new Prod(new Num(1), new Num(2)), new Prod(new Num(3), new Num(4)))

s.show
s.eval

show(s)
