import Serialization._
import argonaut._
import Argonaut._
import org.http4s.{Cookie, HttpService}
import org.http4s.dsl._
import org.http4s.argonaut._
import org.http4s.server.middleware.CORSConfig
import org.http4s._
import Database._

object LinkrService {
  val p = Person("Jane", 20)
  val read = HttpService {
    case req@GET -> Root / "echo" =>
      Ok {
        val cookies = req.headers.get(headers.Cookie)
        p.asJson
        getAllUsers().mkString(",")


      }

    case req@GET -> Root / "echo2" =>
      Ok {

          insertUser(User("lehle","dsda","dsa"))
        "ok"
      }


    case req@POST -> Root / "login" =>
      Ok().addCookie(Cookie("####################################", "fooba",
        httpOnly = true,
        domain = Some("localhost.ms.com:8090")
      ))
  }
}
