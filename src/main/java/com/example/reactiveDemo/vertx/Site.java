package com.example.reactiveDemo.vertx;


import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.eventbus.EventBus;
import rx.schedulers.Schedulers;

/*
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class Site extends AbstractVerticle {

    // Convenience method so you can run it in your IDE
    public static void main(String[] args) {
        Runner.runClusteredExample(Site.class);
    }

    @Override
    public void start() throws Exception {

        EventBus eb = vertx.eventBus();

        // Send a message every second

        vertx.setPeriodic(1000, v -> eb.publish("appointment-req-feed", "Requesting an appointment!"));

        eb.consumer("appointment-res-feed").
                toObservable().
//                subscribeOn(Schedulers.io()).
                subscribe(message -> {System.out.println("Received appointment confirmation: " + message.body());
                    eb.publish("site-appointment-feed", "data here");
                }, System.out::println, ()-> System.out.println("DONE"));
    }
}