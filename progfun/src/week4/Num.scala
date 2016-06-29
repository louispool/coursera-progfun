package week4

trait Expr {

  def isNumber: Boolean
  def isSum: Boolean
  def numValue: Int
  def leftOp: Expr
  def rightOp: Expr

  def eval: Int = this match {
    case Num(n) => n
    case Sum(e1, e2) => e1.eval + e2.eval
    case Prod(e1, e2) => e1.eval * e2.eval
  }

  def show: String = this match {
    case Num(n) => n.toString
    case Sum(e1, e2) => e1.show + " + " + e2.show
    case Prod(e1, e2) => e1.show + " * " + e2.show
    case Var(x) => x
  }
}

case class Num(n: Int) extends Expr {

  def isNumber: Boolean = true
  def isSum: Boolean = false
  def numValue: Int = n

  def rightOp: Expr = throw new Error("Number.rightOp")
  def leftOp: Expr = throw new Error("Number.leftOp")
}

case class Sum(e1: Expr, e2: Expr) extends Expr {

  def isNumber: Boolean = false
  def isSum: Boolean = true
  def numValue: Int = throw new Error ("Sum.numValue")

  def leftOp: Expr = e1
  def rightOp: Expr = e2
}

case class Prod(e1: Expr, e2: Expr) extends Expr {

  def isNumber: Boolean = false
  def isSum: Boolean = false
  def numValue: Int = throw new Error("Product.numValue")

  def leftOp: Expr = e1
  def rightOp: Expr = e2
}

case class Var(x: String) extends Expr {

  def isNumber: Boolean = false
  def isSum: Boolean = false
  def numValue: Int = throw new Error("Var.numValue")

  def leftOp: Expr = throw new Error ("Var.leftOp")
  def rightOp: Expr = throw new Error ("Var.rightOp")
}

