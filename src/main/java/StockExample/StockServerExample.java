package StockExample;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import rx.observables.MathObservable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Created by debamit.dutta on 8/10/17.
 */
public class StockServerExample {

    public static void main(String[] args) throws InterruptedException{
        final List<String> symbols = Arrays.asList(
                "AMD", "HPQ", "IBM", "TXN", "VMW", "XRX", "AAPL", "ADBE",
                "AMZN", "CRAY", "CSCO", "SNE", "GOOG", "INTC", "INTU", "MSFT", "ORCL", "VRSN");


        //--------------Basic observable-------------
//        Observable<StockInfo> obsFeed = StockServer.getObsFeed(symbols);
//
//        obsFeed
//            .subscribeOn(Schedulers.io())
//
//            .filter(s-> s.getPrice() > 100)
//            .subscribe(r -> System.out.println(r), e -> System.out.println("error here"), () -> System.out.println("Done"));


        //--------------Basic Flowable-------------
//        Flowable<StockInfo> feed = StockServer.getFeed(symbols);
//
        //--------------Basic Flowable with share -------------
//        Flowable<StockInfo> sharedFeed = feed.share();
//
//
        //------------First subscriber-----------------
//        sharedFeed
//            .subscribeOn(Schedulers.newThread())
//            .filter(s-> s.getPrice() > 100)
//            .subscribe(System.out::println, e -> System.out.println("error here"), () -> System.out.println("Done"));
//
//
        //------------Second subscriber-----------------
//        sharedFeed
//                .subscribeOn(Schedulers.newThread())
//                .filter(s-> s.getPrice() > 100)
//                .subscribe(r -> System.out.println("Shared flowable : "+ r.toString()), e -> System.out.println("error here"), () -> System.out.println("Done"));
//


        //---------------Zip Example----------------
        Flowable<String> colors = Flowable.just("red", "green", "blue");
        Flowable<Long> timer = Flowable.interval(2, TimeUnit.SECONDS);

        Flowable<String> periodicEmitter = Flowable.zip(colors, timer, (key, val) -> key);
        periodicEmitter.subscribe(System.out::println);

        System.out.println("here");


           Thread.sleep(10000);
    }

}
