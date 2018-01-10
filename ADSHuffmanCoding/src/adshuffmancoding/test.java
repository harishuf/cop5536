package adshuffmancoding;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kunal Phaltane
 */
public class test {
    
    public void test1(String _inputFilePath){
        long startTime = System.nanoTime();
        HashMap<String, Integer> wordDict = new HashMap<String, Integer>();
        int line_cnt =0, word_cnt=0;
        try (BufferedReader br = new BufferedReader(new FileReader(_inputFilePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] words = line.split(" ");
                    for(String word: words){
                        if(wordDict.containsKey(word)){
                            wordDict.put(word, wordDict.get(word)+1);
                        }
                        else{
                            wordDict.put(word, 1);
                        }
                    }
                    
                    line_cnt++;
                    word_cnt += words.length;
            }
        }   
        catch (IOException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        long endTime = System.nanoTime();
        System.out.println(line_cnt);
        System.out.println(word_cnt);
        
        System.out.println((endTime - startTime)/1000000000);   //secs
    }
    
}