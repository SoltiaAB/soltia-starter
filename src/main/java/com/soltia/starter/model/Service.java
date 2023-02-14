package com.soltia.starter.model;


import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Service {

  private int id;
  private String name;
  private String url;

  public JsonObject toJsonObject(){
    return JsonObject.mapFrom(this);
  }

}
