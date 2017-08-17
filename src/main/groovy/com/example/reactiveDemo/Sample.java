package com.example.reactiveDemo;

//import drone.DroneLocator;
import StockExample.YahooFinance;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

import java.util.*;

/**
 * Created by debamit.dutta on 7/19/17.
 */
public class Sample {
    public static void main(String[] args) throws InterruptedException {
//        Observable.interval(1, TimeUnit.SECONDS)
//                .map(data -> data *2)
//                .take(10)
//                .subscribe(System.out::println);
//
//        Thread.sleep(50000);

        final List<String> symbols = Arrays.asList(
                "AMD", "HPQ", "IBM", "TXN", "VMW", "XRX", "AAPL", "ADBE",
                "AMZN", "CRAY", "CSCO", "SNE", "GOOG", "INTC", "INTU", "MSFT", "ORCL", "VRSN");

        Observable.create(emitter -> emitStockPrice(emitter, symbols))
                .subscribe(System.out::println);
    }


    private static void emitStockPrice(ObservableEmitter<Object> emitter, List<String> symbols) {
        while(true) {
            for(String symbol : symbols) {
                emitter.onNext(symbol + "-" + YahooFinance.getPrice(symbol));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
//        DroneLocator.fetch("DR10")
//                .skipWhile(droneLocation -> droneLocation.getAltitude() < 50 )
//                .take(25)
//                .subscribe(
//                        droneLocation -> System.out.println(droneLocation)
//                        , error -> System.out.println(error)
//                        , () -> System.out.println("DroneLanded...")
//                );
//
//
////        Scanner scanner = new Scanner(System.in);
////        if(scanner.nextLine().endsWith("end")){
////
////        }


}
