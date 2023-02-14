package com.soltia.starter.handler;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class ExceptionHandler {


  public static Future<Void> idNotFound(RoutingContext context,String module , String id) {
    return context.response()
      .setStatusCode(HttpResponseStatus.NOT_FOUND.code())
      .end(new JsonObject()
        .put("message", module+" for id " + id + " not available!")
        .put("path ", context.normalizedPath())
        .toBuffer());
  }

}
