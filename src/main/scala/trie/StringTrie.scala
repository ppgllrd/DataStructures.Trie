/** ****************************************************************************
  * Tries for which keys are English words
  *
  * Pepe Gallardo, 2019
  *
  * ****************************************************************************/

package trie

object StringTrie {
  val minChar = 'a'
  val maxChar = 'z'

  val maxChars = maxChar - minChar + 1

  private def toIndex(x: Char): Int = {
    assert(minChar <= x && x <= maxChar, "StringTrie: non allowed character in key")
    x - minChar
  }

  def apply[V](): StringTrie[V] =
    new StringTrie[V]()
}

class StringTrie[V]() extends Trie[String,V] {
  protected var root: StringTrieNode[V] = null
  protected var sz = 0

  override def size: Int = sz

  override def isEmpty: Boolean = sz == 0

  override def insert(key: String, value: V): Unit = {
    def insert(n: StringTrieNode[V], i: Int): StringTrieNode[V] = {
      val node = if (n == null) new StringTrieNode[V]() else n
      if (i == key.length) {
        node.value match {
          case None =>
            sz += 1
          case _ =>
            ;
        }
        node.value = Some(value)
      } else {
        val idx = StringTrie.toIndex(key(i))
        node.children(idx) = insert(node.children(idx), i + 1)
      }
      node
    }

    root = insert(root, 0)
  }

  override def valueOf(key: String): Option[V] = {
    def valueOf(n: StringTrieNode[V], i: Int): Option[V] =
      if (n == null)
        None
      else if (i == key.length)
        n.value
      else {
        val idx = StringTrie.toIndex(key(i))
        valueOf(n.children(idx), i + 1)
      }

    valueOf(root, 0)
  }

  override def delete(key: String): Unit = {
    def delete(node : StringTrieNode[V], i: Int): StringTrieNode[V] = {
      if(node == null)
        return null

      if(i == key.length) {
        node.value match {
          case None =>
            ;
          case Some(value) =>
            node.value = None
            sz -= 1
        }
      } else {
        val idx = StringTrie.toIndex(key(i))
        node.children(idx) = delete(node.children(idx), i + 1)
      }

      node.value match {
        case None =>
          for(child <- node.children)
            if(child != null)
              return node

          null
        case Some(value) =>
          node
      }
    }

    root = delete(root, 0)
  }
}

protected class StringTrieNode[V]() {
  var value: Option[V] = None
  val children = new Array[StringTrieNode[V]](StringTrie.maxChars)
}


