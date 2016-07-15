import scala.io.Source

object PhoneKeys {

  val in = Source.fromURL("http://lamp.epfl.ch/files/content/sites/lamp/files/teaching/progfun/linuxwords.txt")
  val words = in.getLines.toList filter (word => word forall (ch => ch.isLetter))

  val mnem = Map('2'-> "ABC", '3'-> "DEF", '4' -> "GHI", '5' -> "JKL",
                 '6' -> "MNO", '7' -> "PQRS", '8' -> "TUV", '9'-> "WXYZ")

  /** Invert mnap to map chars 'A' .. 'Z' to '2' .. '9' */
  val charCode: Map[Char, Char] = for {
    (digit, str) <- mnem
    ch <- str
  } yield ch -> digit

  /** Maps a word to the digit string it can represent, e.g. "Java"-> "5282" */
  def wordCode(word: String) = word.toUpperCase map charCode

  wordCode("Java")

  /**
    * A map from digit strings to words that can represent them
    * e.g. "5282" -> List("Java", "Kata", "Lava", ...)
    *
    * Missing numbers should map to empty set (e.g. "1111" -> List())
    */
  val wordsForNum: Map[String, Seq[String]] =  words groupBy wordCode withDefaultValue Seq()

  /** Return all ways to encode a number as a list of words */
  def encode(num: String): Set[List[String]] = {
    if (num.isEmpty) Set(List())
    else {
      for {
        split <- 1 to num.length
        word <- wordsForNum(num take split)
        rest <- encode(num drop split)
      } yield word :: rest
    }.toSet
  }

  encode("")
  encode("7225247386")

  def translate(num: String): Set[String] = encode(num) map (_ mkString " ")
  translate("7225247386")


}