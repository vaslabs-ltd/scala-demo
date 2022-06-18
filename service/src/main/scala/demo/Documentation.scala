package demo

import sttp.tapir.docs.openapi._
import demo.endpoints.demo._
import scala.concurrent.Future
import sttp.tapir.swagger.bundle.SwaggerInterpreter
import sttp.tapir.server.akkahttp.AkkaHttpServerInterpreter
import scala.concurrent.ExecutionContext.Implicits.global

object Documentation {

  val swaggerEndpoints = SwaggerInterpreter().fromEndpoints[Future](List(http, addition), "Demo addition", "1.0")

  //http://localhost:8080/docs/
  val swaggerRoute = AkkaHttpServerInterpreter().toRoute(swaggerEndpoints)

}
