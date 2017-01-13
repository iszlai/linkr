import org.mindrot.jbcrypt.BCrypt

import scalaz.{Kleisli, Validation}
import Database._
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm

import scala.util.Try

/**
  * Created by lehel on 1/13/2017.
  */


object AuthProvider {

  def createUser(userDTO: UserDTO): Unit = {
    val passwd = BCrypt.hashpw(userDTO.password, BCrypt.gensalt())
    insertUser(userDTO.copy(password = passwd))
  }

  def login(userDTO: UserDTO): Option[String] = {
    checkCredentials(userDTO) match {
      case Some(true) => createToken(userDTO)
      case _ => None
    }
  }

  def verifyToken(token: String): Option[String] = {
    val verifier = JWT.require(Algorithm.HMAC256("secret")).withIssuer("linkr").build()
    Try {
      verifier.verify(token)
    }.map(_.getClaim("username").asString())
      .toOption
  }


  def checkCredentials(userDTO: UserDTO): Option[Boolean] = {
    findUser(userDTO.name).map(u => BCrypt.checkpw(userDTO.password, u.password))
  }

  def createToken(user: UserDTO): Option[String] = {
    Try {
      JWT.create()
        .withClaim("username", user.name)
        .withIssuer("linkr")
        .sign(Algorithm.HMAC256("secret"))
    }.toOption
  }
}
