package com.thehackerati.threecardmonte;

import android.app.Activity;
import android.content.Intent;
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

    // Show the card and the message
    @Override
    protected void onResume() {
        super.onResume();

        Intent responseIntent = new Intent();
        int requestCode = getIntent().getExtras().getInt("requestCode");

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
