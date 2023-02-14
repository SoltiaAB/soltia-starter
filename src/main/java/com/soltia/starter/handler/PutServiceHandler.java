package com.soltia.starter.handler;


import com.soltia.starter.model.Service;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.soltia.starter.api.ServicesRestApi.SERVICES;
import static com.soltia.starter.api.ServicesRestApi.getServiceId;


public class PutServiceHandler implements Handler<RoutingContext> {

  private static final Logger log = LoggerFactory.getLogger(PutServiceHandler.class);

  @Override
  public void handle(final RoutingContext context) {
    var serviceId = getServiceId(context);
    log.info("Path {} responds with {}",context.normalizedPath(), serviceId);
    var json = context.getBodyAsJson();
    var service = json.mapTo(Service.class);
    SERVICES.add(service);
    context.response().end(json.toBuffer());
  }
}
