package edu.umb.cs680.hw10;

import java.util.ArrayList;
import java.util.Random;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;


class StockQuoteTableChartObservationTest {

    private static StockQuoteObservable observable;
    private static TableChartObserver observer;

    @BeforeAll
    public static void setUp() {
        observable = new StockQuoteObservable();
        observer = new TableChartObserver();

    }

    @Test
    public void verifyChangeQuote() {
        observable.addObserver(observer);
        Random r = new Random();
        char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        System.out.println("hmm");
        ArrayList<String> tickers = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            StringBuilder tickerBuilder = new StringBuilder();
            for (int j = 0; j < 3; j++ ) {
                tickerBuilder.append(alphabet[Math.abs(r.nextInt()) % 26]);
            }
            tickers.add(tickerBuilder.toString());
        }
        for (int i = 0; i < 100; i++) {
            float quote = r.nextFloat();
            String ticker = tickers.get(Math.abs(r.nextInt()) % 10);
            observable.changeQuote(ticker, quote);
            StockEvent e = observer.getLastReceivedStockEvent();
            assertEquals(ticker, e.getTicker());
            assertEquals(quote, e.getQuote());
        }
    }

}
