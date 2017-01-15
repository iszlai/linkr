package io.linkr.service

import argonaut.Argonaut._
import io.linkr.data.Database.{getAllUsers, insertUser}
import io.linkr.data.Serialization._
import io.linkr.data.UserDTO
import io.linkr.util.AuthProvider
import org.http4s.{HttpService, Response}
import org.http4s.argonaut._
import org.http4s.dsl._
import scalaz.concurrent.Task

object LinkrService {

  val read = HttpService {
    case req@GET -> Root / "listUsers" =>
      Ok {
        getAllUsers().asJson
      }

    case req@GET -> Root / "echo2" =>
      Ok {
        AuthProvider.createUser(UserDTO("lehel", "root"))
        "ok"
      }

    case req@POST -> Root / "register" => {
      req.as(jsonOf[UserDTO]).flatMap { user =>
        AuthProvider.createUser(user)
        Ok("created")
      }
    }

    case req
      @POST -> Root / "login" => {
      req.as(jsonOf[UserDTO]).flatMap { user =>
        AuthProvider.login(user) match {
          case None => Task(Response(Unauthorized))
          case Some(res) => Ok(res)
        }
      }
    }
  }
}
