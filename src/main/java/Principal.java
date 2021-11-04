
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author traiandida
 */
public class Principal {
        
    static final String BTH_URL = "https://query1.finance.yahoo.com/v7/finance/download/BTC-USD?period1=1604314064&period2=1635850064&interval=1d&events=history&includeAdjustedClose=true";
    static final String ETH_URL = "https://query1.finance.yahoo.com/v7/finance/download/ETH-USD?period1=1604314054&period2=1635850054&interval=1d&events=history&includeAdjustedClose=true";
    static final String MSFT_URL = "https://query1.finance.yahoo.com/v7/finance/download/MSFT?period1=1604314084&period2=1635850084&interval=1d&events=history&includeAdjustedClose=true";
    static final String AMZN_URL = "https://query1.finance.yahoo.com/v7/finance/download/AMZN?period1=1604314123&period2=1635850123&interval=1d&events=history&includeAdjustedClose=true";
    
    public static void main(String[] args) {
        
        CryptoRunnable bthObject = new CryptoRunnable(BTH_URL, "BTH");
        CryptoRunnable ethObject = new CryptoRunnable(ETH_URL,"ETH");
        CryptoRunnable msftObject = new CryptoRunnable(MSFT_URL,"MSFT");
        CryptoRunnable amznOjbect = new CryptoRunnable(AMZN_URL,"AMZN");

        FutureTask<String> bthFutureTask = 
                    new FutureTask<>(bthObject, "BTH Excel is downloaded");
        FutureTask<String> ethFutureTask = 
                    new FutureTask<>(ethObject, "ETH Excel is downloaded");
        FutureTask<String> msftFutureTask = 
                    new FutureTask<>(msftObject, "MSFT Excel is downloaded");
        FutureTask<String> amznFutureTask = 
                    new FutureTask<>(amznOjbect, "AMZN Excel is downloaded");

        ExecutorService executor = Executors.newFixedThreadPool(4);

        executor.submit(bthFutureTask);
        executor.submit(ethFutureTask);
        executor.submit(msftFutureTask);
        executor.submit(amznFutureTask);
        
        executor.shutdown();
                    
    }
    
    
    
    
    
   
    
}
