import org.http4s.server.{Server, ServerApp}
import org.http4s.server.blaze.BlazeBuilder
import LinkrService._
import org.http4s.server.middleware.{CORS, CORSConfig}

import scalaz.concurrent.Task

object Main extends ServerApp {

  override def server(args: List[String]): Task[Server] = {

    val config=CORSConfig( anyOrigin=true,
      allowCredentials=false,
      maxAge=1200000,
      allowedMethods = Some(Set("GET","POST"))
    )

    BlazeBuilder
      .bindHttp(8090, "localhost.ms.com")
      .mountService(CORS(read,config), "/")
      .start
  }

}