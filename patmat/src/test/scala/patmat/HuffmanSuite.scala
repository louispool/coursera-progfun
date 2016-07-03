package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
	trait TestTrees {
		val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
		val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
    val t3 = Fork(Fork(Leaf('a', 1), Leaf('b', 1), List('a', 'b'), 2), Fork(Leaf('c', 1), Leaf('d', 1), List('c', 'd'), 2), List('a', 'b', 'c', 'd'), 4)
	}


  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }


  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("Times of a tree") {
    new TestTrees {
      assert(times(List('a', 'b', 'a')) === List(('a', 2), ('b', 1)))
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }


  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }


  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
      assert(decode(t2, encode(t2)("ab".toList)) === "ab".toList)
      assert(decode(t2, encode(t2)("abda".toList)) === "abda".toList)
      assert(decode(t3, encode(t3)("abcd".toList)) === "abcd".toList)
    }
  }

  test("test decoded secret") {
    assert(decodedSecret === "huffmanestcool".toList)
  }

  test("decode and quick encode a text should be identity") {
    new TestTrees {
      assert(decode(t1, quickEncode(t1)("ab".toList)) === "ab".toList)
      assert(decode(t2, quickEncode(t2)("ab".toList)) === "ab".toList)
      assert(decode(t2, quickEncode(t2)("abda".toList)) === "abda".toList)
      assert(decode(t3, quickEncode(t3)("abcd".toList)) === "abcd".toList)
    }
  }
}
