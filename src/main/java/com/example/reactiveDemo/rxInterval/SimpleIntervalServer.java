package com.example.reactiveDemo.rxInterval;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 * Created by debamit.dutta on 8/10/17.
 */
public class SimpleIntervalServer {

    public static void main(String[] args) throws InterruptedException{

        
        Observable.interval(1, TimeUnit.SECONDS)
                .map(r -> r * 2)
                .subscribe(System.out::println);

        Thread.sleep(50000);
    }
}
