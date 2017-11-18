package com.example.ilijaangeleski.translator.presenter;
import com.example.ilijaangeleski.translator.manager.TranslateManager;
import com.example.ilijaangeleski.translator.view.TranslateView;

import java.lang.ref.WeakReference;

/**
 * Created by Ilija Angeleski on 11/18/2017.
 */

public class MainPresenter {
    TranslateManager translateManager;
    WeakReference<TranslateView> weakView;

    public MainPresenter(TranslateView view) {
        translateManager = new TranslateManager();
        this.weakView=new WeakReference<>(view);
    }

    public void translate(String text){
       String translated = translateManager.getTranslation(text);
        String translatedSentance = translateManager.translateSentance(text);
        if(translatedSentance != null){
            weakView.get().translatedText(translated);
            weakView.get().translateSentance(translatedSentance);
        }
    }
}
