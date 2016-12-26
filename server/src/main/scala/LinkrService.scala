import akka.actor.Actor
import spray.routing._
import spray.http._
import MediaTypes._
import MyJsonProtocol._
import spray.httpx.SprayJsonSupport
import spray.json._

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class LinkrService extends Actor with MyService {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(myRoute)
}


// this trait defines our service behavior independently from the service actor
trait MyService extends HttpService with SprayJsonSupport {

  override val myRoute =
    path("echo") {
      get {
        complete {
          Person("Name", 12)
        }

      }
    } ~ path("login") {
      post {
        entity(as[User]) {
          user =>
            if (user.name == "lehel") {
              setCookie(HttpCookie("##lehel##", "bar")) {
                complete {
                  print(user)
                  "ok"
                }
            }
            } else{
              complete {
                print(user)
                "nok"
              }
            }
        }
      }
    }
}
