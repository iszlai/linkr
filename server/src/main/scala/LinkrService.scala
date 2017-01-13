import Database._
import Serialization._
import argonaut.Argonaut._
import argonaut._
import org.http4s.argonaut._
import org.http4s.dsl._
import org.http4s.{Cookie, HttpService}

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
