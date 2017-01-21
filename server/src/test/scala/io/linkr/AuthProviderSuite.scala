package io.linkr

import io.linkr.data.Database._
import io.linkr.data.UserDTO
import io.linkr.util.AuthProvider
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{BeforeAndAfter, FunSuite}

@RunWith(classOf[JUnitRunner])
class AuthProviderSuite extends FunSuite with BeforeAndAfter {
  val user = UserDTO("user1", "password")
  val userWrongPassword = UserDTO("user1", "password1")

//  init()

  test("User creation") {
    assert(getAllUsers().size == 0)
    AuthProvider.createUser(user)
    assert(getAllUsers().size == 1)
  }

  test("User Login") {
    assert(getAllUsers().size == 0)
    AuthProvider.createUser(user)
    assert(getAllUsers().size == 1)

    assert(AuthProvider.login(user).isDefined)
  }

  test("User Login for bad password") {
    assert(getAllUsers().size == 0)
    AuthProvider.createUser(user)
    assert(getAllUsers().size == 1)

    assert(AuthProvider.login(userWrongPassword).isEmpty)
  }

  test("User Login for invalid user") {
    assert(getAllUsers().size == 0)
    assert(AuthProvider.login(user).isEmpty)
  }

  test("User verify token") {
    assert(getAllUsers().size == 0)
    AuthProvider.createUser(user)
    assert(getAllUsers().size == 1)

    val token = AuthProvider.login(user).get
    assert(AuthProvider.verifyToken(token) == Some(user.name))
  }

  test("User verify badtoken") {
    assert(getAllUsers().size == 0)
    AuthProvider.createUser(user)
    assert(getAllUsers().size == 1)

    val token = AuthProvider.login(user).get
    val badToken = token + "evil"
    assert(AuthProvider.verifyToken(badToken) == None)
  }

  after {
    deleteAllUsers()
  }
}


