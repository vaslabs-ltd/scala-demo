package demo
import akka.http.scaladsl.model.StatusCodes
import demo.endpoints.AdditionOp
import sttp.tapir.server.akkahttp._
import demo.endpoints.demo._

import scala.concurrent.Future
import akka.http.scaladsl.server.Directives._
import demo.model._
import sttp.tapir.server.akkahttp.AkkaHttpServerInterpreter
import scala.concurrent.ExecutionContext.Implicits.global

object routes {



  val additionRoute = AkkaHttpServerInterpreter().toRoute(http.serverLogicSuccess[Future] {
    case (a,b) => Future.successful(AdditionOp.op(a, b))
  }) ~ AkkaHttpServerInterpreter().toRoute(addition.serverLogicSuccess[Future] {
    case Addition(a,b) => Future.successful(Sum(AdditionOp.op(a, b)))
  }) ~ path("health") {
    get {
      complete(StatusCodes.OK)
    }
  }


}
