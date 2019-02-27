/** ****************************************************************************
  * Some tests for tries
  *
  * Pepe Gallardo, 2019
  *
  * ****************************************************************************/

import trie.StringTrie

object StringTrieTest extends App {
  val trie = StringTrie[Int]()
  trie.insert("pepe", 1)
  trie.insert("pepero", 2)
  trie.insert("pedro", 5)
  trie.insert("pe", 8)
  trie.insert("pedro", 15)


  println(trie.size)

  println(trie.valueOf("pero"))
  println(trie.valueOf("pepe"))
  println(trie.valueOf("pedro"))
  println(trie.valueOf("pepero"))
  println(trie.valueOf("pe"))
  
  trie.delete("pe")
  trie.delete("pepe")

  println(trie.size)
  println(trie.valueOf("pero"))
  println(trie.valueOf("pepe"))
  println(trie.valueOf("pedro"))
  println(trie.valueOf("pepero"))
  println(trie.valueOf("pe"))
}