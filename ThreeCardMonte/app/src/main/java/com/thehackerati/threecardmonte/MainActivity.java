package com.thehackerati.threecardmonte;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends Activity {

    public static final int JACK_OF_DIAMONDS = 12,
            QUEEN_OF_SPADES = 13,
            KING_OF_HEARTS = 14,
            GAME_SIGNAL = 99;

    private List<Integer> cardList;

    private ImageButton btnCard1,
            btnCard2,
            btnCard3;
    private Button btnShuffle, btnExit;

    private Integer[] cards = new Integer[] {KING_OF_HEARTS, JACK_OF_DIAMONDS, QUEEN_OF_SPADES};

    // Shuffle the 3 card deck
    private void randomizeCards() {
        cardList = Arrays.asList(cards);
        Collections.shuffle(cardList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCard1 = (ImageButton) findViewById(R.id.card1);
        btnCard2 = (ImageButton) findViewById(R.id.card2);
        btnCard3 = (ImageButton) findViewById(R.id.card3);
        btnShuffle = (Button) findViewById(R.id.shuffle);
        btnExit = (Button) findViewById(R.id.exit);

        // Shuffle the cards in the beginning
        randomizeCards();
    }

    // Show the selected card
    private void showCard() {
        Intent launchComIntent = new Intent(this, CardFace.class);
        startActivityForResult(launchComIntent, GAME_SIGNAL);
    }

    // Save the shared preference - card value
    private void savePreference(String shared_pref_key, String pref_key, int value) {
        SharedPreferences sharedPreferences = getSharedPreferences(shared_pref_key, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(pref_key, value);
        editor.commit();
    }

    // Set onClick actions
    private void setupListeners() {
        final List<ImageButton> cardsList = Arrays.asList(btnCard1, btnCard2, btnCard3);
        for (final ImageButton cardElement : cardsList) {
            cardElement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int btnIndex = cardsList.indexOf(cardElement);
                    savePreference(getString(R.string.shared_pref_key), getString(R.string.pref_key), cardList.get(btnIndex));
                    showCard();
                }
            });
        }
        btnShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomizeCards();
                Toast toast = Toast.makeText(getApplicationContext(), R.string.shuffle_message, Toast.LENGTH_SHORT);
                TextView tvToast = (TextView) toast.getView().findViewById(android.R.id.message);
                tvToast.setTextColor(Color.YELLOW);
                toast.setGravity(Gravity.CENTER, 0, -120);
                toast.show();
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupListeners();
    }
}
