package io.linkr.util

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.linkr.data.Database.{findUser, insertUser}
import io.linkr.data.UserDTO
import org.mindrot.jbcrypt.BCrypt

import scala.util.Try

object AuthProvider {

  def createUser(userDTO: UserDTO): Unit = {
    println(s"Creating user [$userDTO]")
    val passwd = BCrypt.hashpw(userDTO.password, BCrypt.gensalt())
    insertUser(userDTO.copy(password = passwd))
  }

  def login(userDTO: UserDTO): Option[String] = {
    println(s"Logging in user [$userDTO]")
    checkCredentials(userDTO) match {
      case Some(true) => createToken(userDTO)
      case _ => None
    }
  }

  def checkCredentials(userDTO: UserDTO): Option[Boolean] = {
    println(s"Checking credentials user [$userDTO]")
    findUser(userDTO.name).map(u => BCrypt.checkpw(userDTO.password, u.password))
  }

  def createToken(user: UserDTO): Option[String] = {
    println(s"Creating token [$user]")
    Try {
      JWT.create()
        .withClaim("username", user.name)
        .withIssuer("linkr")
        .sign(Algorithm.HMAC256("secret"))
    }.toOption
  }

  def verifyToken(token: String): Option[String] = {
    println(s"Verifying token [$token]")
    val verifier = JWT.require(Algorithm.HMAC256("secret")).withIssuer("linkr").build()
    Try {
      verifier.verify(token)
    }.map(_.getClaim("username").asString())
      .toOption
  }
}
