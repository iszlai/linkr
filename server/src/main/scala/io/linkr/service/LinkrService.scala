package io.linkr.service

import argonaut.Argonaut._
import io.linkr.data.Database.{getAllUsers, insertUser}
import io.linkr.data.UserDTO
import org.http4s.argonaut._
import org.http4s.dsl._
import org.http4s.{Cookie, HttpService}
import io.linkr.data.Serialization._
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
