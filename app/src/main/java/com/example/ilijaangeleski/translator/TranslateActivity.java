package com.example.ilijaangeleski.translator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ilijaangeleski.translator.presenter.MainPresenter;
import com.example.ilijaangeleski.translator.view.TranslateView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TranslateActivity extends AppCompatActivity implements TranslateView {
    @BindView(R.id.input)
    EditText input;

    @BindView(R.id.translatedLanguage)
    TextView translatedLanguage;

    @BindView(R.id.output)
    TextView output;

    @BindView(R.id.language)
    TextView language;

    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenter(this);
        inputText();

    }
    public void inputText(){
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                presenter.translate(editable.toString());

            }
        });
    }

    @Override
    public void translatedText(String text) {
        output.setText(text);
    }

    @Override
    public void translateSentance(String text) {
        output.setText(text);
    }
}
