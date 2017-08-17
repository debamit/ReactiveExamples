package com.example.reactiveDemo.vertx;

import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.eventbus.EventBus;
import rx.Scheduler;

/**
 * Created by debamit.dutta on 8/15/17.
 */
public class Inventory extends AbstractVerticle {
    // Convenience method so you can run it in your IDE
    public static void main(String[] args) {
        Runner.runClusteredExample(Inventory.class);
    }


    @Override
    public void start() throws Exception {

        EventBus eb = vertx.eventBus();

        eb.consumer("inventory-req-feed").
                toObservable().
//                subscribeOn(rx.schedulers.Schedulers.io()).
                subscribe(message -> {System.out.println("Received inventory request: " + message.body());
                    eb.publish("inventory-res-feed", "Inventory Available");
                });

        System.out.println("Ready!");
    }
}
