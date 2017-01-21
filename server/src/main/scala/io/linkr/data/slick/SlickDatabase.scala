package io.linkr.data.slick


import io.linkr.data.slick.Tables.users
import io.linkr.data.{PersistenceOperations, UserDTO}
import io.linkr.util.Utility.currentTime
import slick.driver.H2Driver.api._
import slick.driver.JdbcProfile

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class SlickDatabase(db: JdbcProfile#Backend#Database) extends PersistenceOperations {
  override def init(): Unit = {
    db.run(
      DBIO.seq(
        users.schema.create
      )
    )
  }

  override def findUser(id: String): Option[UserDTO] = {

    Await.result(
      db.run(
        sql"select username, password from users where username = $id".as[(String, String)].headOption
      ),
      Duration.Inf
    ).map(UserDTO.apply _ tupled)
  }

  override def insertUser(user: UserDTO): Unit = {
    db.run(users.map(x => (x.username, x.password, x.registeredAt)) +=
      (user.name, user.password, currentTime()))
  }

  override def getAllUsers(): List[UserDTO] = {
    Await.result(db.run(users.result), Duration.Inf).map(x => UserDTO(x._1, x._2)).toList
  }

  override def deleteAllUsers(): Unit = {
    Await.result(db.run(users.delete), Duration.Inf)
  }
}
