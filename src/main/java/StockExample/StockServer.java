package StockExample;

import io.reactivex.*;


import java.util.List;


/**
 * Created by debamit.dutta on 8/11/17.
 */
public class StockServer {

    public static Flowable<StockInfo > getFeed(List<String> symbols){
        return Flowable.create(emiter -> {
            try {
//                if(Math.random() > 0.1)
//                    throw new Exception("ohoh");
                symbols.stream()
                        .map(s -> new StockInfo(s, YahooFinance.getPrice(s)))
                        .forEach(emiter::onNext);
                emiter.onComplete();
            } catch (Exception e){
                throw e;
            }
        }, BackpressureStrategy.BUFFER);
    }


    public static  Observable<StockInfo> getObsFeed(List<String> symbols){
        return Observable.create(emiter -> {
            try {
//                if(Math.random() > 0.1)
//                    throw new Exception("ohoh");
                symbols.stream()
                        .map(s -> new StockInfo(s, YahooFinance.getPrice(s)))
                        .forEach(emiter::onNext);
                emiter.onComplete();
            } catch (Exception e){
                throw e;
            }
        });
    }


}
