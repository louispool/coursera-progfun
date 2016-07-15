/*
 * Polynomials can be represented as a map from exponents to coefficients
 * e.g. x^3 - 2x + 5 = Map(0 -> 5, 1 -> -2, 3 -> 1)
 */
object polynomials {

  class Poly(terms0: Map[Int, Double]) {

    //Auxiliary constructor
    def this(bindings: (Int, Double)*) = this(bindings.toMap)

    val terms = terms0 withDefaultValue 0.0

    def + (other: Poly) = new Poly(terms ++ (other.terms map adjust))

    def adjust(term: (Int, Double)): (Int, Double) = {
      val (exp, coeff) = term
      terms get exp match {                           //or simply exp -> (coeff + terms(exp))
        case Some(coeff1) => exp -> (coeff + coeff1)
        case None => exp -> coeff
      }
    }

    //Alternate method
    def plus(other: Poly) = new Poly((other.terms foldLeft terms)(addTerm))

    def addTerm(terms: Map[Int, Double], term: (Int, Double)) = {
      val (exp, coeff) = term
      terms + (exp -> (coeff + terms(exp)))
    }

    override def toString() = (for ((exp, coeff) <- terms.toList.sorted.reverse) yield coeff + "x^" + exp) mkString " + "
  }

  val p1 = new Poly(Map(1 -> 2.0, 3 -> 4.0, 5 -> 6.2))
  val p2 = new Poly(0 -> 3.0, 3 -> 7.0)

  p1 + p2
  p1.terms(7)

  p1 plus p2
}