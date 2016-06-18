package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = singletonSet(4)

    val su12 = union(s1, s2)
    val su23 = union(s2, s3)
    val su34 = union(s3, s4)
    val su123 = union(su12, su23)
    val su1234 = union(su123, su34)

    val si2 = intersect(su12, su23)
    val si23 = intersect(su123, su23)
    val si4 = intersect(su1234, s4)
    val siEmpty = intersect(su12, su34)

    val sd1 = diff(su12, su23)
    val sd12 = diff(su12, su34)
    val sd123 = diff(su123, s4)
    val sdEmpty = diff(su12, su1234)

    val evens = filter(x => -1000 < x && x < 1000, p => p % 2 == 0)
    val odds = filter(x => -1000 < x && x < 1000, p => p % 2 != 0)

    val squares = map(su1234, x => x*x)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
      assert(contains(s2, 2), "Singleton")
      assert(contains(s3, 3), "Singleton")
      assert(contains(s4, 4), "Singleton")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {

      assert(contains(su12, 1), "Union 1")
      assert(contains(su12, 2), "Union 2")
      assert(!contains(su12, 3), "Union 3")
      assert(!contains(su12, 4), "Union 4")

      assert(!contains(su23, 1), "Union 1")
      assert(contains(su23, 2), "Union 2")
      assert(contains(su23, 3), "Union 3")
      assert(!contains(su23, 4), "Union 4")

      assert(contains(su123, 1), "Union 1")
      assert(contains(su123, 2), "Union 2")
      assert(contains(su123, 3), "Union 3")
      assert(!contains(su123, 4), "Union 4")

      assert(contains(su1234, 1), "Union 1")
      assert(contains(su1234, 2), "Union 2")
      assert(contains(su1234, 3), "Union 3")
      assert(contains(su1234, 4), "Union 4")


    }
  }

  test("intersection of sets") {
    new TestSets {

      assert(!contains(si2, 1), "Intersect 1")
      assert(contains(si2, 2), "Intersect 2")

      assert(contains(si23, 2), "Intersect 2")
      assert(contains(si23, 3), "Intersect 3")
      assert(!contains(si23, 4), "Intersect 4")

      assert(!contains(si4, 3), "Intersect 3")
      assert(contains(si4, 4), "Intersect 4")

      assert(empty(siEmpty), "Empty Intersect")
    }
  }

  test("difference of sets") {
    new TestSets {

      assert(contains(sd1, 1), "Difference 1")
      assert(!contains(sd1, 2), "Difference 2")

      assert(contains(sd12, 1), "Difference 1")
      assert(contains(sd12, 2), "Difference 2")
      assert(!contains(sd12, 3), "Difference 3")

      assert(contains(sd123, 1), "Difference 1")
      assert(contains(sd123, 2), "Difference 2")
      assert(contains(sd123, 3), "Difference 3")
      assert(!contains(sd123, 4), "Difference 4")

      assert(empty(sdEmpty), "Empty Difference")
    }
  }

  test("filters") {
    new TestSets {

      assert(contains(evens, 2), "Set of evens contains 2")
      assert(contains(evens, 4), "Set of evens contains 4")
      assert(contains(evens, 606), "Set of evens contains 606")

      assert(contains(odds, 3), "Set of odds contains 3")
      assert(contains(odds, 9), "Set of odds contains 9")
      assert(contains(odds, 501), "Set of odds contains 501")

      assert(!contains(evens, 3), "Set of evens does not contain 3")
      assert(!contains(evens, 9), "Set of evens does not contain 9")
      assert(!contains(evens, 501), "Set of evens does not contain 501")


      assert(!contains(odds, 2), "Set of odds does not contain 2")
      assert(!contains(odds, 4), "Set of odds does not contain 4")
      assert(!contains(odds, 606), "Set of odds does not contain 606")

      assert(forall(evens, p => p % 2 == 0), "Set of even numbers")
      assert(forall(odds, p => p % 2 != 0), "Set of odd numbers")
    }
  }

  test("exists") {
    new TestSets {

      assert(exists(evens, x => x == 2), "The integer 2 exists in the set of evens")
      assert(exists(evens, x => x == 4), "The integer 4 exists in the set of evens")
      assert(exists(evens, x => x == 280), "The integer 280 exists in the set of evens")

      assert(!exists(evens, x => x == 3), "The integer 3 does not exist in the set of evens")
      assert(!exists(evens, x => x == 5), "The integer 5 does not exist in the set of evens")
      assert(!exists(evens, x => x == 909), "The integer 909 does not exist in the set of evens")

      assert(!exists(odds, x => x == 2), "The integer 2 does not exist in the set of odds")
      assert(!exists(odds, x => x == 4), "The integer 4 does not existin the set of odds")
      assert(!exists(odds, x => x == 280), "The integer 280 does not exist in the set of odds")

      assert(exists(odds, x => x == 3), "The integer 3 exists in the set of odds")
      assert(exists(odds, x => x == 5), "The integer 4 exists in the set of odds")
      assert(exists(odds, x => x == 909), "The integer 909 exists in the set of odds")
    }
  }

  test("empty sets") {
    new TestSets {

      assert(!empty(s1), "s1 not empty")
      assert(!empty(s2), "s2 not empty")
      assert(!empty(s3), "s3 not empty")

      assert(!empty(su123), "su123 not empty")
      assert(!empty(si4), "si4 not empty")
      assert(!empty(sd123), "sd123 not empty")

      assert(empty(sdEmpty), "sd is Empty")
      assert(empty(siEmpty), "si is Empty")
    }
  }

  test("map") {
    new TestSets {

      assert(exists(squares, x => x == 1), "The integer 1 exists in the set of squares")
      assert(exists(squares, x => x == 4), "The integer 4 exists in the set of squares")
      assert(exists(squares, x => x == 9), "The integer 9 exists in the set of squares")
      assert(exists(squares, x => x == 16), "The integer 16 exists in the set of squares")

      assert(!exists(squares, x => x == 2), "The integer 2 does not exist in the set of squares")
      assert(!exists(squares, x => x == 3), "The integer 3 does not exist in the set of squares")
      assert(!exists(squares, x => x == 32), "The integer 32 does not exist in the set of squares")
    }
  }
}



