package com.thehackerati.fibonacci;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.thehackerati.fibonacci.adapters.FibonacciAdapter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {

    private Button againButton,
        calculateButton;
    private View viewA,
            viewQ;
    private EditText edtText;
    private ListView lvFibonacci;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set the views and initial visibility
        viewA = (View) findViewById(R.id.modeA);
        viewA.setVisibility(View.INVISIBLE);
        viewQ = (View) findViewById(R.id.modeQ);

        // set the ListView
        lvFibonacci = (ListView) findViewById(R.id.fibonacci_list_view);

        // set the buttons
        againButton = (Button) findViewById(R.id.again);
        calculateButton = (Button) findViewById(R.id.calculate);

        // set the EditText control
        edtText = (EditText) findViewById(R.id.no_fibonacci);
    }

    // calculate the fibonacci sequence
    private void calculate_fib(int length) {

        BigInteger first, second, result;

        List<String> listFibonacci;
        FibonacciAdapter fibonacciAdapter;

        // declare the array
        String[] fibonacci_array = new String[length+1];
        // set seed values
        fibonacci_array[0] = "0";
        if (length > 0) fibonacci_array[1] = "1";
        // use the formula to calculate all of the fibonacci numbers
        if (length > 1) {
            for (int i = 2; i <= length; i++) {
                first = new BigInteger(fibonacci_array[i - 2]);
                second = new BigInteger(fibonacci_array[i - 1]);
                result = first.add(second);
                fibonacci_array[i] = result.toString();
            }
        }
        listFibonacci = Arrays.asList(fibonacci_array);

        // create and set an adapter
        fibonacciAdapter = new FibonacciAdapter(listFibonacci);
        lvFibonacci.setAdapter(fibonacciAdapter);
    }

    // set onClick actions for the buttons
    private void setupListeners() {
        againButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewQ.setVisibility(View.VISIBLE);
                viewA.setVisibility(View.INVISIBLE);
                edtText.setText("");
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = edtText.getText().toString();
                try {
                    int max_fib = Integer.parseInt(text);
                    if (max_fib < 0) {
                        Toast.makeText(getApplicationContext(), R.string.negative_err, Toast.LENGTH_SHORT).show();
                        edtText.setText("");
                    } else {
                        viewA.setVisibility(View.VISIBLE);
                        viewQ.setVisibility(View.INVISIBLE);
                        calculate_fib(max_fib);
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), R.string.conversion_err, Toast.LENGTH_SHORT).show();
                    edtText.setText("");
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupListeners();
    }
}
