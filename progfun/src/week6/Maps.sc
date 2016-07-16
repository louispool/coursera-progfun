import scala.collection.immutable.IndexedSeq

val romanNumerals = Map("I" -> 1, "V" -> 5, "X" -> 10)
val capitalOfCountry = Map("US" -> "Washington", "Switzerland" -> "Bern")

capitalOfCountry("US")

capitalOfCountry get "Andorra"
capitalOfCountry get "US"

def showCapital(country: String) = capitalOfCountry.get(country) match {
  case Some(capital) => capital
  case None => "missing data"
}

showCapital("US")
showCapital("Andorra")

val fruit = List("apple", "pear", "orange", "pineapple")
fruit sortWith (_.length < _.length)
fruit.sorted

fruit groupBy (_.head)


val cap1 = capitalOfCountry withDefaultValue "<unknown>"
cap1("Andorra")

val word = "wanderer"

def wordOccurrences(w: String): List[(Char, Int)] = w.toLowerCase.sorted.groupBy(x => x).mapValues(str => str.length).toList.sorted

val sentence = List("the", "sorry", "wanderer", "wanders", "aimlessly")

sentence mkString

val dictionary = List("live", "evil", "ate", "tea", "eat")

dictionary groupBy(w => wordOccurrences(w))

//Generate tuples for (a,3) as (a, 3), (a, 2), (a, 1)

def multicombo(tuples: List[(Char, Int)]): List[List[(Char, Int)]] = {
  if (tuples.isEmpty) List(Nil)
  else List() :: {
    for {
      num <- 1 to tuples.head._2
      rest <- multicombo(tuples drop 1)
    } yield (tuples.head._1, num) :: rest
  }.toList
}

multicombo(List(('a', 2), ('b', 2), ('c', 2)))

def combo(tuple: (Char, Int)): List[(Char, Int)] = {
  (for {
    num <- 1 to tuple._2
  } yield (tuple._1, num)).toList
}

combo(('a', 4))

def combinations(tuples: List[(Char, Int)]): List[List[(Char, Int)]] = {
  (tuples foldRight List[List[(Char, Int)]](Nil)) ((tuple, z) => {
    (for {
      combo <- z
      num <- 1 to tuple._2
    } yield (tuple._1, num) :: combo) ++ z
  })
}
combinations(List(('a', 2), ('b', 2), ('c', 2)))

//def combinations(tuples: List[(Char, Int)]): List[List[(Char, Int)]] = {
//  if (tuples.isEmpty) List(Nil)
//  else  List() :: {
//    for {
//      idx <- 1 to tuples.length
//      tuple <- tuples take idx
//      occur <- 1 to tuple._2
//      rest <- combinations(tuples drop idx)
//    } yield (tuple._1, occur) :: rest
//  }.toList
//}
//
//combinations(List(('a', 2)))
//combinations(List(('a', 2), ('b', 2)))
//


//def multicombo2(tuples: List[(Char, Int)]): List[List[(Char, Int)]] = {
//  if (tuples.isEmpty) List(Nil)
//  else List() :: {
//    val singlecombos = multicombo(tuples)
//    for {
//      i <- 0 until singlecombos.length-1
//      j <- i+1 until singlecombos.length
//    } yield singlecombos(i) :: singlecombos(j)
//  }.toList
//}
//
//multicombo2(List(('a', 2), ('b', 2)))
//
//val l = List(('a', 4), ('b', 4), ('c', 4))

//multicombo2(l)










