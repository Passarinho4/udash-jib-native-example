package com.tegess

import io.udash.rest.{DefaultRestApiCompanion, RestServlet}
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.{ServletContextHandler, ServletHolder}

object Main extends App {

  // translate UserApiImpl into a Servlet
  val userApiServlet = RestServlet[UserApi](new UserApiImpl)

  // do all the Jetty related plumbing
  val server = new Server(9090)
  val handler = new ServletContextHandler
  handler.addServlet(new ServletHolder(userApiServlet), "/*")
  server.setHandler(handler)
  server.start()
  server.join()

}

trait UserApi {
}

object UserApi extends DefaultRestApiCompanion[UserApi]

class UserApiImpl extends UserApi {

}
