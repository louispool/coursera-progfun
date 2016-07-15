val fruit = Set("apple", "banana", "pear")
val s = (1 to 6).toSet

s map (_ + 2)
fruit filter (_.startsWith("app"))
s.nonEmpty

//N-Queens (place n chess queens on an n×n chessboard so that no two queens threaten each other)
def nqueens(n: Int): Set[List[Int]] = {
  def isSafe(col: Int, queens: List[Int]): Boolean = {
    val row = queens.length
    val queensWithRow = (row -1 to 0 by -1) zip queens
    queensWithRow forall {
      case (r, c) => col != c && //Column check (row is implicit)
                     math.abs(col - c) != row - r //Diagonals check
    }
  }

  def placeQueens(k: Int): Set[List[Int]] = {
    if (k == 0) Set(List())
    else {
      for {
        queens <- placeQueens(k-1)
        col <- 0 until n
        if isSafe(col, queens)
      } yield col :: queens
    }
  }
  placeQueens(n)
}

nqueens(4)

def show(queens: List[Int]) = {
  val lines = {
    for (col <- queens.reverse)
      yield Vector.fill(queens.length)("* ").updated(col, "X ").mkString
  }
  "\n" + (lines mkString "\n")
}

(nqueens(4) map show ) mkString "\n"


(nqueens(8) take 3 map show) mkString "\n"




