//import collection.mutable.Stack
//import org.scalatest._
//
//class ScalaTestFlatSpec extends FlatSpec {
//  "An empty Set" should "have size 0" in {
//    assert(Set.empty.size == 0)
//  }
//
//  it should "produce NoSuchElementException when head is invoked" in {
//    assertThrows[NoSuchElementException] {
//      Set.empty.head
//    }
//  }
//
//  "A Stack" should "pop values in last-in-first-out order" in {
//    val stack = new Stack[Int]
//    stack.push(1)
//    stack.push(2)
//    assert(stack.pop() === 2)
//    assert(stack.pop() === 1)
//  }
//
//  it should "throw NoSuchElementException if an empty stack is popped" in {
//    val emptyStack = new Stack[String]
//    assertThrows[NoSuchElementException] {
//      emptyStack.pop()
//    }
//  }
//
//}