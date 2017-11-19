package com.example.ilijaangeleski.translator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ilijaangeleski.translator.presenter.MainPresenter;
import com.example.ilijaangeleski.translator.view.TranslateView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TranslateActivity extends AppCompatActivity implements TranslateView {
    @BindView(R.id.input)
    EditText input;

    @BindView(R.id.output)
    TextView output;

    @BindView(R.id.spinner)
    Spinner fromLanguageSpinner;

    @BindView(R.id.toLanguageSpinner)
    Spinner toLanguageSpinner;

    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenter(this);
        setListeners();
       /* fromLanguageSpinnerAdapter = new SpinnerAdapter(this,presenter.getSupportedLanguages());
        fromLanguageSpinner.setAdapter(fromLanguageSpinnerAdapter);*/

        ArrayAdapter<String> fromLanguage = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,presenter.getSupportedLanguages());
        fromLanguageSpinner.setAdapter(fromLanguage);

        ArrayAdapter<String> toLanguage = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,presenter.getSupportedLanguages());
        toLanguageSpinner.setAdapter(toLanguage);



    }
    public void setListeners(){
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                presenter.translateFromTo(editable.toString(),getFromSelectedLanguage(), getToSelectedLanguage());
            }
        });
        fromLanguageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                presenter.translateFromTo(input.getText().toString(),getFromSelectedLanguage(), getToSelectedLanguage());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        toLanguageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                presenter.translateFromTo(input.getText().toString(),getFromSelectedLanguage(), getToSelectedLanguage());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private String getFromSelectedLanguage(){
        return fromLanguageSpinner.getSelectedItem().toString();
    }
    private String getToSelectedLanguage(){
        return toLanguageSpinner.getSelectedItem().toString();
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
