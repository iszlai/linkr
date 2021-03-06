package io.linkr.data

import java.util.Date

import doobie.imports._

import scalaz.Scalaz._
import scalaz.concurrent.Task

trait PersistenceOperations {

  def init(): Unit

  def findUser(id: String): Option[UserDTO]

  def insertUser(user: UserDTO): Unit

  def getAllUsers(): List[UserDTO]

  def deleteAllUsers(): Unit
}

object Database extends PersistenceOperations {

  val xa = DriverManagerTransactor[Task](
    "org.h2.Driver", "jdbc:h2:mem:todo;DB_CLOSE_DELAY=-1", "sa", ""
  )

  val drop: Update0 =
    sql"""
    DROP TABLE IF EXISTS users
  """.update

  val create: Update0 =
    sql"""
    DROP TABLE IF EXISTS users;
    CREATE TABLE users (
      username VARCHAR NOT NULL UNIQUE,
      password VARCHAR NOT NULL ,
      registeredAt TIMESTAMP NOT NULL
    );
  """.update


  def init() = create.run.transact(xa).unsafePerformSync


  def findUser(id: String): Option[UserDTO] =
    sql"select username, password from users where username = $id".query[UserDTO].option.transact(xa).unsafePerformSync

  def insertUser(user: UserDTO) =
    sql"insert into users (username,password, registeredAt) values (${user.name}, ${user.password},${new Date})".update.run.transact(xa).unsafePerformSync

  def getAllUsers(): List[UserDTO] = {
    sql"select username,password from users".query[UserDTO].list.transact(xa).unsafePerformSync
  }

  override def deleteAllUsers():Unit = {
    sql"delete from users".update.run.transact(xa).unsafePerformSync
  }
  // init()
}
