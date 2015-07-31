package com.thehackerati.greetingintongues;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {

    // language and exit buttons
    private Button spanishButton,
            turkishButton,
            esperantoButton,
            englishButton,
            chineseButton,
            serbianButton,
            frenchButton,
            italianButton,
            germanButton,
            czechButton,
            swedishButton,
            russianButton,
            japaneseButton,
            zuluButton,
            yiddishButton,
            exitButton;

    /* maps to relate a two letter language abbreviation
    with appropriate button, language and translation */
    private HashMap<String, Button> buttonMap;
    private HashMap<String, String> languageMap,
            translationMap;

    // creates buttons
    private void setButtons() {
        spanishButton = (Button) findViewById(R.id.ES);
        turkishButton = (Button) findViewById(R.id.TR);
        esperantoButton = (Button) findViewById(R.id.EO);
        englishButton = (Button) findViewById(R.id.EN);
        chineseButton = (Button) findViewById(R.id.ZH);
        serbianButton = (Button) findViewById(R.id.SR);
        frenchButton = (Button) findViewById(R.id.FR);
        italianButton = (Button) findViewById(R.id.IT);
        germanButton = (Button) findViewById(R.id.DE);
        swedishButton = (Button) findViewById(R.id.SV);
        russianButton = (Button) findViewById(R.id.RU);
        japaneseButton = (Button) findViewById(R.id.JA);
        zuluButton = (Button) findViewById(R.id.ZU);
        yiddishButton = (Button) findViewById(R.id.JI);
        czechButton = (Button) findViewById(R.id.CS);
        exitButton = (Button) findViewById(R.id.EXIT);
    }

    // sets above mentioned maps
    private void setMaps() {
        buttonMap = new HashMap<String, Button>() {{
            put("ES", spanishButton);
            put("TR", turkishButton);
            put("EO", esperantoButton);
            put("EN", englishButton);
            put("ZH", chineseButton);
            put("SR", serbianButton);
            put("FR", frenchButton);
            put("IT", italianButton);
            put("DE", germanButton);
            put("CS", czechButton);
            put("SV", swedishButton);
            put("RU", russianButton);
            put("JA", japaneseButton);
            put("ZU", zuluButton);
            put("JI", yiddishButton);
        }};
        languageMap = new HashMap<String, String>() {{
            put("ES", getString(R.string.ES));
            put("TR", getString(R.string.TR));
            put("EO", getString(R.string.EO));
            put("EN", getString(R.string.EN));
            put("ZH", getString(R.string.ZH));
            put("SR", getString(R.string.SR));
            put("FR", getString(R.string.FR));
            put("IT", getString(R.string.IT));
            put("DE", getString(R.string.DE));
            put("CS", getString(R.string.CS));
            put("SV", getString(R.string.SV));
            put("RU", getString(R.string.RU));
            put("JA", getString(R.string.JA));
            put("ZU", getString(R.string.ZU));
            put("JI", getString(R.string.JI));
        }};
        translationMap = new HashMap<String, String>() {{
            put("ES", getString(R.string.EST));
            put("TR", getString(R.string.TRT));
            put("EO", getString(R.string.EOT));
            put("EN", getString(R.string.ENT));
            put("ZH", getString(R.string.ZHT));
            put("SR", getString(R.string.SRT));
            put("FR", getString(R.string.FRT));
            put("IT", getString(R.string.ITT));
            put("DE", getString(R.string.DET));
            put("CS", getString(R.string.CST));
            put("SV", getString(R.string.SVT));
            put("RU", getString(R.string.RUT));
            put("JA", getString(R.string.JAT));
            put("ZU", getString(R.string.ZUT));
            put("JI", getString(R.string.JIT));
        }};
    }

    // resets translation except for the selected language
    private void clearExcept(String currentLang) {
        for (Map.Entry<String, Button> entry : buttonMap.entrySet()) {

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 5, 0, 5);
            entry.getValue().setLayoutParams(params);

            entry.getValue().setGravity(Gravity.CENTER);
            entry.getValue().setTypeface(null, Typeface.BOLD);
            if (!entry.getKey().equals(currentLang)) {
                entry.getValue().setText(languageMap.get(entry.getKey()));
                entry.getValue().setBackgroundColor(0xFF20B9FF);
                entry.getValue().setTextColor(Color.DKGRAY);
            } else {
                entry.getValue().setBackgroundColor(0xFFFF6FDD);
                entry.getValue().setTextColor(Color.WHITE);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setButtons();
        setMaps();
        clearExcept("-"); // initially reset all buttons
    }

    // set onClick actions for buttons
    private void setupListeners(HashMap<String, Button> btnMap) {
        for (Map.Entry<String, Button> btnEntry : btnMap.entrySet()) {
            final String mapKey = btnEntry.getKey();
            final Button mapValue = btnEntry.getValue();
            mapValue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mapValue.setText(translationMap.get(mapKey));
                    clearExcept(mapKey);
                }
            });
        }

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        setupListeners(buttonMap);
    }
}