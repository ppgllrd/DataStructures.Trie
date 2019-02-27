/** ****************************************************************************
  * Tries
  *
  * Pepe Gallardo, 2019
  *
  * ****************************************************************************/

package trie

trait Trie[K, V] {
  def isEmpty: Boolean

  def size: Int

  def insert(key: K, value: V)

  def valueOf(key: K): Option[V]

  def delete(key: K)
}
