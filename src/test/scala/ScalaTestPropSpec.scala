import org.scalatest._
import matchers._
import org.scalatest.propspec.AnyPropSpec
import prop._

import scala.collection.immutable._

class SetSpec extends AnyPropSpec with TableDrivenPropertyChecks with should.Matchers {

  val examples =
    Table(
      "set",
      BitSet.empty
    )

  property("an empty Set should have size 0") {
    forAll(examples) { set =>
      set.size should be (0)
    }
  }

  property("invoking head on an empty set should produce NoSuchElementException") {
    forAll(examples) { set =>
      a [NoSuchElementException] should be thrownBy { set.head }
    }
  }
}