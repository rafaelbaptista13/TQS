import java.util.*;

public class StocksPortfolio {
    private IStockmarketService stockmarket;
    private List<Stock> stocks = new ArrayList<>(); 
    private String name;

    public IStockmarketService getStockMarket() {
        return this.stockmarket;
    }

    public void setStockMarket(IStockmarketService stockmarket) {
        this.stockmarket = stockmarket;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTotalValue() {
        Double value = 0.0;
        for (Stock stock: stocks) {
            value += stockmarket.getPrice(stock.getLabel()) * stock.getQuantity();
        }
        return value;
    }

    public void addStock(Stock stock) {
        this.stocks.add(stock);
    }
}