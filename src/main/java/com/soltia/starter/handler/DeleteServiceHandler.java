package com.soltia.starter.handler;


import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static com.soltia.starter.api.ServicesRestApi.SERVICES;
import static com.soltia.starter.api.ServicesRestApi.getServiceId;
import static com.soltia.starter.handler.ExceptionHandler.idNotFound;


public class DeleteServiceHandler implements Handler<RoutingContext> {

  private static final Logger log = LoggerFactory.getLogger(DeleteServiceHandler.class);

  @Override
  public void handle(final RoutingContext context) {

    Optional
      .ofNullable(getServiceId(context))
      .ifPresentOrElse(id -> {
        if(! id.matches("\\d+")) log.error("Service Not found for ID : {}",id);
          var i = Integer.parseInt(id);
            var serviceToDelete = SERVICES.stream().filter(service -> service.getId() == i).findFirst();
            serviceToDelete.ifPresentOrElse(
              service -> {
                SERVICES.removeIf(s -> s.getId() == i);
                log.info("Deleted: {} Remaining: {}",serviceToDelete, SERVICES);
                context.response().end(service.toJsonObject().toBuffer());
              },
              () -> idNotFound(context, "services", id)
            );
        },
        () -> log.error("Service ID is empty"));
  }

}
