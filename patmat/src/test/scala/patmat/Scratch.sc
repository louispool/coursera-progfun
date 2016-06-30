import patmat.Huffman._

times(List('a', 'b', 'a'))
times(List('a', 'b', 'a',  'c', 'd', 'c', 'b', 'c'))

val leaves = makeOrderedLeafList(times(List('a', 'b', 'a', 'c', 'd', 'c', 'a')))

//singleton(List('a'))

combine(leaves)
until(singleton, combine)(leaves)

createCodeTree(List('a', 'b', 'a', 'c', 'd', 'c', 'a'))




