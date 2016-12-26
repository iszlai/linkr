import Serialization._
import argonaut._
import Argonaut._
import org.http4s.{Cookie, HttpService}
import org.http4s.dsl._
import org.http4s.argonaut._
import org.http4s.server.middleware.CORSConfig
import org.http4s._

object LinkrService {
  val p = Person("Jane", 20)
  val read = HttpService {
    case req@GET -> Root / "echo" =>
      Ok {
        val cookies=req.headers.get(headers.Cookie)
        p.asJson
      }

    case req@POST -> Root / "login" =>
      Ok().addCookie(Cookie("####################################", "fooba",
        httpOnly = true,
        domain = Some("localhost.ms.com:8090")
        ))
  }
}
