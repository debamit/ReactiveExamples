package com.example.reactiveDemo.vertx;

/**
 * Created by debamit.dutta on 8/15/17.
 */
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.eventbus.EventBus;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.handler.StaticHandler;
import io.vertx.rxjava.ext.web.handler.sockjs.SockJSHandler;
import rx.Observable;
import rx.Subscription;
import rx.schedulers.Schedulers;

public class SockJsServer extends AbstractVerticle {
    // Convenience method so you can run it in your IDE
    public static void main(String[] args) {
        Runner.runClusteredExample(SockJsServer.class);
    }

    @Override
    public void start() throws Exception {

        Router router = Router.router(vertx);

        router.route("/site-appointment-feed/*").handler(SockJSHandler.create(vertx).socketHandler(sockJSSocket -> {

            // Consumer the event bus address as an Observable
            Observable<String> msg = vertx.eventBus().<String>consumer("site-appointment-feed")
                    .bodyStream()
                    .toObservable();

            // Send the event to the client
            Subscription subscription = msg.subscribe(sockJSSocket::write);

            // Unsubscribe when the socket closes
            sockJSSocket.endHandler(v -> {
                subscription.unsubscribe();
            });
        }));

        // Serve the static resources
        router.route().handler(StaticHandler.create());

        vertx.createHttpServer().requestHandler(router::accept).listen(8080);

        // Publish a message to the address "news-feed" every second
//        vertx.setPeriodic(1000, t -> vertx.eventBus().publish("news-feed", "news from the server!"));

        EventBus eb = vertx.eventBus();
        vertx.setPeriodic(1000, v -> eb.publish("appointment-req-feed", "Requesting an appointment!"));

        eb.consumer("appointment-res-feed").
                toObservable().
                subscribeOn(Schedulers.io()).
                subscribe(message -> {System.out.println("Received appointment confirmation: " + message.body());
                    eb.publish("site-appointment-feed","Appointment confirmed! ");
                });
    }
}
