/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 *
 * @author JT
 */
public class Dictionary {
        private HashMap<String, String> dictionary;
    
    public Dictionary() {
        this.dictionary = new HashMap();
    }
    
    public void add(String word, String translation) {
        this.dictionary.putIfAbsent(word, translation);
    }
    
    public String randomValue() {
        Random generator = new Random();
        List<String> keyList = new ArrayList();
        
        for (String key : this.dictionary.keySet()) {
            keyList.add(key);
        }
        
        if (keyList.isEmpty()) {
            return "";
        }
        
        return keyList.get(generator.nextInt(keyList.size()));
        
    }
    
    public boolean checkIfIn(String key, String value) {
        
        if (value.equals(this.dictionary.get(key))) {
            return true;
        }
        return false;
    }
    
    public String getValue(String word) {
        return this.dictionary.get(word);
    }
}
