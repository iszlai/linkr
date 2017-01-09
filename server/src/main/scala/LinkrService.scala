import Serialization._
import argonaut._
import Argonaut._
import org.http4s.HttpService
import org.http4s.dsl._
import org.http4s.argonaut._

object LinkrService {
  val p = Person("Jane", 20)
  val read = HttpService {
    case GET -> Root / "echo" =>
      Ok {
        p.asJson
      }
  }
}
