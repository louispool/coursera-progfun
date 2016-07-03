import patmat.Huffman._

times(List('a', 'b', 'a'))
times(List('a', 'b', 'a',  'c', 'd', 'c', 'b', 'c'))

val leaves = makeOrderedLeafList(times(List('a', 'b', 'a', 'c', 'd', 'c', 'a')))

//singleton(List('a'))

combine(leaves)
until(singleton, combine)(leaves)

createCodeTree(List('a', 'b', 'a', 'c', 'd', 'c', 'a'))

List() ::: List('b')

decodedSecret

val test = List(Leaf('c', 1),
     Leaf('d', 1), Leaf('e', 1), Leaf('f', 1),
     Leaf('g', 1), Leaf('h', 1), Leaf('b', 3), Leaf('a', 8) )

until(singleton, combine)(test)

createCodeTree(List('b','b','b','c', 'd', 'e', 'f', 'g', 'h'))

val chars = List('a', 'a', 'a', 'a', 'a', 'b', 'b', 'b', 'b', 'c', 'c', 'c', 'd', 'd', 'e')

makeOrderedLeafList(times(chars))

val tree = createCodeTree(chars)
decode(tree, List(1,0,0,0,1,0,1,0))

val t1 = Fork(Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5), Leaf('c', 4), List('a', 'b', 'c'), 9)

encode(t1)("ab".toList)

decode(t1, encode(t1)("ab".toList))

encode(t1)("abca".toList)

decode(t1, encode(t1)("abca".toList))

convert(t1)

val t2 = Fork(Leaf('a', 1), Leaf('b', 1), List('a', 'b'), 2)

convert(t2)


val t3 = Fork(Fork(Leaf('a', 1), Leaf('b', 1), List('a', 'b'), 2), Fork(Leaf('c', 1), Leaf('d', 1), List('c', 'd'), 2), List('a', 'b', 'c', 'd'), 4)

convert(t3)
quickEncode(t3)("ab".toList)
quickEncode(t3)("abcd".toList)



val t4 = Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5)

convert(t4)
encode(t4)("ab".toList)
quickEncode(t4)("ab".toList)















