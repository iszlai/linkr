package io.linkr.core

import io.linkr.service.LinkrService.read
import org.http4s.server.blaze.BlazeBuilder
import org.http4s.server.middleware.{CORS, CORSConfig}
import org.http4s.server.{Server, ServerApp}

import scalaz.concurrent.Task

object Main extends ServerApp {

  override def server(args: List[String]): Task[Server] = {

    val config = CORSConfig(anyOrigin = true,
      allowCredentials = false,
      maxAge = 1200000,
      allowedMethods = Some(Set("GET", "POST"))
    )

    BlazeBuilder
      .bindHttp(8090, "localhost.ms.com")
      .mountService(CORS(read, config), "/")
      .start
  }

}