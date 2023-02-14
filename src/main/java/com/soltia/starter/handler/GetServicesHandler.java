package com.soltia.starter.handler;

import io.netty.handler.codec.http.HttpHeaderValues;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetServicesHandler implements Handler<RoutingContext> {

  private static final Logger log = LoggerFactory.getLogger(GetServicesHandler.class);


  @Override
  public void handle(final RoutingContext context) {
        JsonArray response = new JsonArray();
        log.info("Path {} responds with {}",context.normalizedPath(), response.encode());
    try {
      Thread.sleep(200);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    context.response()
          .putHeader(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
          .end(response.toBuffer());
  }
}
