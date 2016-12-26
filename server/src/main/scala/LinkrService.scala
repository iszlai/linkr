import MyJsonProtocol._
import akka.actor.Actor
import spray.http._
import spray.httpx.SprayJsonSupport
import spray.routing._
import spray.http.HttpHeaders._
import spray.http.HttpMethods._

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
trait MyService extends HttpService with SprayJsonSupport with CORSSupport {

   val myRoute = respondWithCORS("*"){
  path("echo") {
    get {
      complete {
        Person("Name", 12)
      }

    }
  } ~ path("login") {
    post {
      entity(as[User]) { user =>
        authenticator(user).map {
          credential =>
            setCookie(HttpCookie("###Lehel##" + credential.name, "Bar",domain=Some("localhost")))
            complete(StatusCodes.OK)
        }.getOrElse(complete(StatusCodes.Unauthorized))
      }
    }
  }
  }

  def authenticator(user: User): Option[User] = {
    if (user.name == "lehel") Some(user) else None
  }

}

trait CORSSupport extends Directives {
  private val CORSHeaders = List(
    `Access-Control-Allow-Methods`(GET, POST, PUT, DELETE, OPTIONS),
    `Access-Control-Allow-Headers`("Origin, X-Requested-With, Content-Type, Accept, Accept-Encoding, Accept-Language, Host, Referer, User-Agent"),
    `Access-Control-Allow-Credentials`(true)
  )

  def respondWithCORS(origin: String)(routes: => Route) = {
    val originHeader = `Access-Control-Allow-Origin`(AllOrigins)
    respondWithHeaders(originHeader :: CORSHeaders) {
      routes ~ options {
        complete(StatusCodes.OK)
      }
    }
  }
}

