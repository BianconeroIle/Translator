package com.example.ilijaangeleski.translator.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ilija Angeleski on 11/18/2017.
 */

public class TranslateManager {
    static Map<String, String> translation = new HashMap<String, String>();
    static Map<String, Map<String, Map<String, String>>> multilanguage = new HashMap<>();
    static List<String> supportedLanguages = new ArrayList<>();

    private static void fillLanguages() {
        translation.put("dog", "kuce");
        translation.put("cat", "macka");
        translation.put("frog", "zaba");
        translation.put("car", "kola");
        translation.put("you", "ti");
    }

    private static void fillMultilanguage() {
        Map<String, Map<String, String>> mkEn = new HashMap<>();
        Map<String, String> mkEnWords = new HashMap<>();
        mkEnWords.put("kuce", "dog");
        mkEnWords.put("zaba", "frog");
        mkEnWords.put("macka", "cat");
        mkEnWords.put("ptica", "bird");
        mkEn.put("mk_en", mkEnWords);

        multilanguage.put("mk", mkEn);

        Map<String, Map<String, String>> enMk = new HashMap<>();
        Map<String, String> enMkWords = new HashMap<>();
        enMkWords.put("dog", "kuce");
        enMkWords.put("frog", "zaba");
        enMkWords.put("cat", "macka");
        enMkWords.put("bird", "ptuca");
        enMk.put("en_mk", enMkWords);

        multilanguage.put("en", enMk);
    }

    private static void extractSupportedLanguages() {
        for (Map.Entry<String, Map<String, Map<String, String>>> entry : multilanguage.entrySet()) {
            supportedLanguages.add(entry.getKey());
        }
    }

    static {
        fillLanguages();
        fillMultilanguage();
        extractSupportedLanguages();
    }

    public String getTranslation(String text) {
        return translation.get(text.toLowerCase().trim());
    }

    private String translateSentance(String text, Map<String, String> translationMap) {
        StringBuilder translatedSentance = new StringBuilder();
        String[] words = text.split(" ");
        for (String word : words) {
            String translatedWord = translationMap.get(word.toLowerCase().trim());
            if (translatedWord != null) {
                translatedSentance.append(translatedWord + " ");
            } else {
                translatedSentance.append(word + " ");
            }
        }
        return translatedSentance.toString();
    }

    public List<String> getSupportedLanguages() {
        return supportedLanguages;
    }

    public String translateSentances(String text, String fromLanguage, String toLanguage) {
        Map<String, Map<String, String>> fromSelectedLanguageMap = multilanguage.get(fromLanguage);
        Map<String, String> translationMap = fromSelectedLanguageMap.get(fromLanguage + "_" + toLanguage);
        if (translationMap != null) {
            return translateSentance(text, translationMap);
        } else {
            return text;
        }
    }

}
