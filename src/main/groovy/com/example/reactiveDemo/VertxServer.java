package com.example.reactiveDemo;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;

/**
 * Created by debamit.dutta on 7/27/17.
 */
public class VertxServer extends AbstractVerticle {

    public void main (String[] args ){
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(VertxServer.class.getName());
    }

    @Override
    public void start() throws Exception {

        HttpServer server =
                vertx.createHttpServer();

        server.requestHandler(req -> {
            req.response().putHeader("content-type", "text/html").end("<html><body>" +
                    "<h1>Hello from vert.x!</h1>" +
                    "<p>version = " + req.version() + "</p>" +
                    "</body></html>");
        }).listen(8443);
    }
}
