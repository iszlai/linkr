package io.linkr.data.slick

import slick.backend.DatabaseConfig
import slick.driver.JdbcProfile



class SlickDatabaseConfig {

  private val dbConfig : DatabaseConfig[JdbcProfile]  = DatabaseConfig.forConfig("h2db")

   val profile: JdbcProfile = dbConfig.driver
   val db: JdbcProfile#Backend#Database = dbConfig.db
}
