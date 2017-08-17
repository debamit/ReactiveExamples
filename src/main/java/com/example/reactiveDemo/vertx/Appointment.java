package com.example.reactiveDemo.vertx;


import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.eventbus.EventBus;
import rx.schedulers.Schedulers;


public class Appointment extends AbstractVerticle {

    // Convenience method so you can run it in your IDE
    public static void main(String[] args) {
        Runner.runClusteredExample(Appointment.class);
    }


    @Override
    public void start() throws Exception {

        EventBus eb = vertx.eventBus();

        eb.consumer("appointment-req-feed").
                toObservable().
//                subscribeOn(Schedulers.io()).
                subscribe(message -> {System.out.println("Received appointment request: " + message.body());
                eb.publish("inventory-req-feed", "Checking if inventory is available");
                });

        eb.consumer("inventory-res-feed").
                toObservable().
//                subscribeOn(Schedulers.io()).
                subscribe(message -> {System.out.println("Received inventory response: " + message.body());
                    eb.publish("appointment-res-feed", "Appointment has been successfully created");
                });

        System.out.println("Ready!");
    }
}