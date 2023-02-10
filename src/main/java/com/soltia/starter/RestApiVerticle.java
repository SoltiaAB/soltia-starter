package com.soltia.starter;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestApiVerticle extends AbstractVerticle {

  private static final Logger LOG = LoggerFactory.getLogger(RestApiVerticle.class);


  @Override
  public void start(final Promise<Void> startPromise) throws Exception {
    Router restAPI = Router.router(this.vertx);
    restAPI.route()
      .handler(BodyHandler.create()
        .setBodyLimit(1024)
        .setHandleFileUploads(true))
      .failureHandler(handleFailure());

    this.vertx.createHttpServer()
      .requestHandler(restAPI)
      .exceptionHandler(error -> LOG.error("HTTP Server error {} ",error))
      .listen(MainVerticle.Port, http -> {
        if (http.succeeded()) {
          startPromise.complete();
          LOG.info("HTTP server started on port {}",MainVerticle.Port);
        } else {
          startPromise.fail(http.cause());
        }
      });
  }

  private static Handler<RoutingContext> handleFailure() {
    return error -> {
      if (error.response().ended()) return;
      LOG.error("Route error : ", error.failure());
      error.response()
        .setStatusCode(500)
        .end(new JsonObject().put("message", "Something went wrong :(").toBuffer());
    };
  }
}
