package com.thehackerati.threecardmonte;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
    private Button btnShuffle;

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

        // Shuffle the cards in the beginning
        randomizeCards();
    }

    // Show the selected card
    private void showCard(int cardWeight) {
        Intent launchComIntent = new Intent(this, CardFace.class);
        launchComIntent.putExtra("requestCode", cardWeight);
        startActivityForResult(launchComIntent, GAME_SIGNAL);
    }

    // Set onClick actions
    private void setupListeners() {
        final List<ImageButton> cardsList = Arrays.asList(btnCard1, btnCard2, btnCard3);
        for (final ImageButton cardElement : cardsList) {
            cardElement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int btnIndex = cardsList.indexOf(cardElement);
                    showCard(cardList.get(btnIndex));
                }
            });
        }
        btnShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomizeCards();
                Toast toast = Toast.makeText(getApplicationContext(), R.string.shuffle_message, Toast.LENGTH_SHORT);
                TextView aaav = (TextView) toast.getView().findViewById(android.R.id.message);
                aaav.setTextColor(Color.RED);
                toast.show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupListeners();
    }
}
