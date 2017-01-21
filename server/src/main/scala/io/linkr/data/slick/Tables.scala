package io.linkr.data.slick

import java.sql.Date

import slick.lifted.Tag
import slick.driver.H2Driver.api._

class Users(tag: Tag) extends Table[(String, String, Date)](tag, "Users") {

  def username = column[String]("username", O.PrimaryKey)

  def password = column[String]("password")

  def registeredAt = column[Date]("STREET")

  def * = (username, password, registeredAt)
}

object Tables {


  val users = TableQuery[Users]

}
