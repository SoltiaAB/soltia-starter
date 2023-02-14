package com.soltia.starter.api;

import com.soltia.starter.handler.DeleteServiceHandler;
import com.soltia.starter.handler.GetServiceHandler;
import com.soltia.starter.handler.GetServicesHandler;
import com.soltia.starter.handler.PutServiceHandler;
import com.soltia.starter.model.Service;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class ServicesRestApi {

  private static final Logger log = LoggerFactory.getLogger(ServicesRestApi.class);

  private static final String PATH = "/service/:id";

  public static final List<Service> SERVICES = Arrays.asList(Service.builder().id(1).name("test").url("test.com").build());
  public static void attach(Router parent){
    parent.get("/services").handler( new GetServicesHandler());
    parent.get(PATH).handler( new GetServiceHandler());
    parent.put(PATH).handler( new PutServiceHandler());
    parent.delete(PATH).handler( new DeleteServiceHandler());
  }

  public static String getServiceId(RoutingContext context) {
    var serviceId = context.pathParam("serviceId");
    log.info("Path {} responds with {}", context.normalizedPath(), serviceId);
    return serviceId;
  }
}

