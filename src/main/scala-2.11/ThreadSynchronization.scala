import scala.collection.mutable
import scala.collection.mutable.SynchronizedMap

case class User(name: String, id: Int)

class InvertedIndex(val userMap: mutable.Map[String, User]) {

  def this() = this(new mutable.HashMap[String, User])

  def tokenizeName(name: String): Seq[String] = {
    name.split(" ").map(_.toLowerCase)
  }

  def add(term: String, user: User) {
    userMap += term -> user
  }

  def add(user: User) {
    // TokenizeName was measured to be the most expensive operation.
    val tokens = tokenizeName(user.name)

    tokens.foreach { term =>
      userMap.synchronized { // Lock at finest level of granularity possible ..
        add(term, user)
      }
    }

  }
}


// Deprecated - using SynchronizedMap is unsafe. Also synchronizes on each and every map op - expensive.
//class SynchronizedInvertedIndex(userMap: mutable.Map[String, User]) extends InvertedIndex(userMap) {
//  def this() = this(new mutable.HashMap[String, User] with SynchronizedMap[String, User])
//}


// Java comes with a nice thread-safe ConcurrentHashMap. Thankfully, we can use JavaConverters to
// give us nice Scala semantics. In fact, we can seamlessly layer our new, thread-safe InvertedIndex
// as an extension of the old unsafe one.
import java.util.concurrent.ConcurrentHashMap
import collection.concurrent
import collection.JavaConverters._

class ConcurrentInvertedIndex(userMap: concurrent.Map[String, User])
  extends InvertedIndex(userMap) { // NB: concurrent.Map is a subtype of mutable.Map
  def this() = this(new ConcurrentHashMap[String, User] asScala) // 'asScala' converts the Java ConcurrentHashMap to a Scala concurrent.Map
}


object ThreadSynchronization {
  // ...
}
