package com.kevin.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

public class MyFirstVerticle extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) {


        Router router = Router.router(vertx);
        router.route("/").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response
                    .putHeader("content-type", "text/html")
                    .end("<h1>Hello from my first Vert.x 3 application</h1>");

        });

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(
                    config().getInteger("http.port", 8080),
                    result -> {
                    if(result.succeeded()) {
                        startFuture.complete();
                    } else {
                        startFuture.fail(result.cause());
                    }
                });
    }
}
