import java.util.concurrent.TimeUnit

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import akka.util.Timeout
import com.com.akkademy.AkkademyDb
import com.com.akkademy.messages.SetRequest
import org.scalatest.{FunSpecLike, Matchers}

class AkkademyDbSpec extends FunSpecLike with Matchers {

  implicit val system = ActorSystem()
  implicit val timeout = Timeout(5, TimeUnit.SECONDS)

  describe("akkademyDb") {
    describe("given SetRequest") {
      it("should place key value into map") {
        val actorRef = TestActorRef(new AkkademyDb)
        actorRef ! SetRequest("key", "value")

        val akkademyDb = actorRef.underlyingActor
        akkademyDb.map.get("key") should equal(Some("value"))
      }
    }
  }
}
