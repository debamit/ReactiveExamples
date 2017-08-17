package StockExample;

/**
 * Created by debamit.dutta on 8/11/17.
 */
public class StockInfo {

    private String symbol;

    private double price;

    StockInfo(String symbol, double price){
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString(){return String.format("ticker:%s price:%g", symbol, price);}
}
