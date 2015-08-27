package com.thehackerati.threecardmonte;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class CardFace extends Activity {

    ImageView cardFace;
    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_face);
    }

    // Get the shared preference
    public Integer LoadPreference(String shared_pref_key, String pref_key, int default_val) {
        SharedPreferences sharedPreferences = getSharedPreferences(shared_pref_key, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(pref_key, default_val);
    }

    // Show the card and the message
    @Override
    protected void onResume() {
        super.onResume();

        Intent responseIntent = new Intent();
        int requestCode = LoadPreference(getString(R.string.shared_pref_key), getString(R.string.pref_key), 13);

        cardFace = (ImageView) findViewById(R.id.iv_card_face);
        message = (TextView) findViewById(R.id.tv_message);

        switch (requestCode) {
            case 12:
                cardFace.setImageResource(R.drawable.jack);
                message.setText("Bad luck! Try again!");
                break;
            case 13:
                cardFace.setImageResource(R.drawable.queen);
                message.setText("You win!");
                break;
            case 14:
                cardFace.setImageResource(R.drawable.king);
                message.setText("Bad luck! Try again!");
                break;
            default:
                break;
        }

        setResult(RESULT_OK, responseIntent);
    }
}
