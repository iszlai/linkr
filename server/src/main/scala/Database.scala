import java.util.Date

import doobie.imports._

import scalaz._
import Scalaz._
import scalaz.concurrent.Task

object Database {

  val xa = DriverManagerTransactor[Task](
    "org.h2.Driver", "jdbc:h2:mem:todo;DB_CLOSE_DELAY=-1", "sa", ""
  )

  val drop: Update0 =
    sql"""
    DROP TABLE IF EXISTS person
  """.update

  val create: Update0 =
    sql"""
    CREATE TABLE users (
      username VARCHAR NOT NULL UNIQUE,
      password VARCHAR NOT NULL ,
      registeredAt TIMESTAMP NOT NULL
    )
  """.update


  (drop.run *> create.run ).transact(xa).unsafePerformSync


  def findArticle(id: String): Task[Option[User]] =
    sql"select username, password from users where username = $id".query[User].option.transact(xa)

  def insertUser(user:User)=
    sql"insert into users (username,password, registeredAt) values (${user.name}, ${user.password},${new Date})".update.run.transact(xa).unsafePerformSync

  def getAllUsers():List[UserDTO]={
    sql"select username,password from users".query[UserDTO].list.transact(xa).unsafePerformSync
  }
}