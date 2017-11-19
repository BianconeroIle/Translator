package com.example.ilijaangeleski.translator.manager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ilija Angeleski on 11/18/2017.
 */

public class TranslateManager {
    static Map<String,String> translation = new HashMap<String, String>();


    private static void fillLanguages() {
        translation.put("dog", "kuce");
        translation.put("cat", "macka");
        translation.put("frog", "zaba");
        translation.put("car", "kola");
        translation.put("you", "ti");
    }
    static {
        fillLanguages();
    }

    public String getTranslation(String text){
        return translation.get(text.toLowerCase().trim());
    }
    public String translateSentance(String text){
        StringBuilder translatedSentance = new StringBuilder();
        String[] words = text.split(" ");
        for(String word : words){
            String translatedWord = translation.get(word.toLowerCase().trim());
            if(translatedWord != null){
                translatedSentance.append(translatedWord + " ");
            }else {
                translatedSentance.append(word + " ");
            }
        }
        return translatedSentance.toString();
    }

}
