
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author traiandida
 */
public class CryptoRunnable implements Runnable{
    
    private final String DOWNLOAD_URL;
    private final String PATH = "files";
    private final String CRYPTO_NAME;
      
    public CryptoRunnable(String downloadUrl, String fileName){
        this.DOWNLOAD_URL = downloadUrl;
        if(!Files.exists(Paths.get(PATH))){
            new File(PATH).mkdir();
        }
        this.CRYPTO_NAME = fileName;
    } 
    
    @Override
    public void run() {     
        if(getCSV()){
            System.out.println(CRYPTO_NAME + " downloaded...");
        }
        
        String [] lastEntry = getCloseValue();
        
        System.out.println("Preu tancament "+CRYPTO_NAME+" dia: "+lastEntry[0]
                + " es "+ lastEntry[4] );
        
    }
    
    private boolean getCSV(){
          try{
            BufferedInputStream in = 
                    new BufferedInputStream(new URL(DOWNLOAD_URL).openStream());
            FileOutputStream fileOutputStream = 
                    new FileOutputStream(PATH+"/"+CRYPTO_NAME+".csv");
            byte data[] = new byte[1024];
            int byteContent;
            while((byteContent = in.read(data,0,1024)) != -1){
                fileOutputStream.write(data, 0 , byteContent);
            }     
            return true;
        } catch (IOException ex) {
            Logger.getLogger(CryptoRunnable.class.getName()).log(Level.SEVERE, null, ex); 
        }    
          return false;
    }
    
    private  String[] getCloseValue(){
        String [] rows = null;
        String line;
        
        try(BufferedReader br = new BufferedReader(
                new FileReader(PATH+"/"+CRYPTO_NAME+".csv"))){
            
            while((line = br.readLine()) != null){
                rows = line.split(",");
            }
            
        } catch (IOException ex) {
            Logger.getLogger(CryptoRunnable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return rows;
    }
    
}
