package com.soltia.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainVerticle extends AbstractVerticle {

  private final static Logger LOG = LoggerFactory.getLogger(MainVerticle.class);

  public static final int Port = 8888;


  public static void main(String[] args){
    var vertx = Vertx.vertx();
    vertx.exceptionHandler(error -> LOG.error("Unhandled {} ",error));
    vertx.deployVerticle(new MainVerticle())
      .onFailure(err -> LOG.error("Unhandled {} with err {}",MainVerticle.class.getName(),err))
      .onComplete(id -> LOG.info("Deployed {} with id {}",MainVerticle.class.getName(), id));
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    vertx.deployVerticle(RestApiVerticle.class.getName(),
        new DeploymentOptions().setInstances(processors()))
      .onFailure(err -> {
        LOG.error("Unhandled {} with err {}",RestApiVerticle.class.getName(),err);
        startPromise.fail(err);
      })
      .onComplete(id -> {
        LOG.info("Deployed {} with id {}",RestApiVerticle.class.getName(), id);
        startPromise.complete();
      });
  }

  private int processors(){
    return Math.max(1, Runtime.getRuntime().availableProcessors()/2);
  }

}
