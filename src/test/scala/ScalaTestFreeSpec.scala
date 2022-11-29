import org.scalatest.Assertions.assertThrows
import org.scalatest.freespec.AnyFreeSpec

import scala.Console.in

class ScalaTestFreeSpec extends AnyFreeSpec {
  "A Set" - {
    "when empty" - {
      "should have size 0" in {
        assert(Set.empty.size == 0)
      }

      "should produce NoSuchElementException when head is invoked" in {
        assertThrows[NoSuchElementException] {
          Set.empty.head
        }
      }
    }
  }
}

