package com.soltia.starter.handler;


import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static com.soltia.starter.api.ServicesRestApi.SERVICES;
import static com.soltia.starter.api.ServicesRestApi.getServiceId;
import static com.soltia.starter.handler.ExceptionHandler.idNotFound;


public class GetServiceHandler implements Handler<RoutingContext> {

  private static final Logger log = LoggerFactory.getLogger(GetServiceHandler.class);

  @Override
  public void handle(final RoutingContext context) {

    Optional
      .ofNullable(getServiceId(context))
      .ifPresentOrElse(id -> {
          if(! id.matches("\\d+")) log.error("Service Not found for ID : {}",id);
          SERVICES.stream()
          .filter(service -> service.getId() == Integer.parseInt(id))
          .findFirst()
          .ifPresentOrElse(
            service -> context.response().end(service.toJsonObject().toBuffer()),
            () -> idNotFound(context, "services", id)
          );
      },
        ()-> log.error("Id not exist"));
  }
}
