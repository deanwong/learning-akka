import java.util.concurrent.TimeUnit

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import akka.util.Timeout
import com.com.akkademy.Echo
import org.scalatest.{FunSpecLike, Matchers}

class EchoSpec extends FunSpecLike with Matchers {

  implicit val system = ActorSystem()
  implicit val timeout = Timeout(5, TimeUnit.SECONDS)
  val msgs = Vector("foo", "bar")

  describe("store last string") {
    describe("send one string") {
      it("should save the string") {
        val actorRef = TestActorRef(new Echo)
        actorRef ! msgs.head
        val echo = actorRef.underlyingActor
        echo.lastString should equal(Some("foo"))
      }
    }

    describe("send two strings") {
      it("should save last string") {
        val actorRef = TestActorRef(new Echo)
        msgs.foreach({ m => actorRef ! m })
        val echo = actorRef.underlyingActor
        echo.lastString should equal(Some("bar"))
      }
    }
  }
}
