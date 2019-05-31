package com.nairs.concurrency.stocks;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.nairs.concurrency.stocks.StockQuoteService;
public class Main {

    static public Map<String, Double> getQuotes(List<String> symbols) {

        Map<String, Double> quotes = new HashMap<>();

        symbols.parallelStream().forEach(symbol -> {
                    try {
                        Double price = StockQuoteService.getPrice(symbol);
                        quotes.put(symbol , price);
                    }catch (Exception e) {
                        //TODO
                    }
                }

                );
        return quotes;
    }


    public static void main(String... args) {
        List<String> symbols = Arrays.asList("MSFT", "BBY", "AMZN", "TSLA");

        Map<String, Double> quotes = getQuotes(symbols);

        quotes.forEach((k, v) -> {
            System.out.println(k + " " + v);
        });
    }
}
