class Rational(x: Int, y: Int) {
  require(y != 0, "Denominator must be non-zero")

  def this(x: Int) = this(x, 1)

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
  private val g = gcd(x, y)

  val numer = x / g
  val denom = y / g

  def unary_- = new Rational(-numer, denom)

  def +(that: Rational) = {
    new Rational(this.numer * that.denom + that.numer * this.denom,
                 this.denom * that.denom)
  }

  def -(that: Rational) = this+(-that)

  def *(that: Rational) = {
    new Rational(this.numer * that.numer, this.denom * that.denom)
  }

  def <(that: Rational) = this.numer * that.denom < that.numer * this.denom

  def >(that: Rational) = this.numer * that.denom > that.numer * this.denom

  def max(that: Rational) = if (<(that)) that else this

  override def toString = numer + "/" + denom
}

val half = new Rational(1, 2)
half.numer
half.denom

val twoThirds = new Rational(2, 3)
half + twoThirds

val x = new Rational(1, 3)
val y = new Rational(5, 7)
val z = new Rational(3, 2)

x - y - z
y + y
x < y

//val strange = new Rational(1, 0)

new Rational(2)




