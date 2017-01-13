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

  val read = HttpService {
    case req@GET -> Root / "listUsers" =>
      Ok {
        getAllUsers().asJson
      }

    case req@GET -> Root / "echo2" =>
      Ok {
        insertUser(UserDTO("lehel", "root"))
        "ok"
      }


    case req@POST -> Root / "login" =>
      Ok().addCookie(Cookie("####################################", "fooba",
        httpOnly = true,
        domain = Some("localhost.ms.com:8090")
      ))
  }
}
