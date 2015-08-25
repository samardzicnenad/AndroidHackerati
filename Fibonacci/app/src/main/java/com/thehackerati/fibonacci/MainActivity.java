package com.thehackerati.fibonacci;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    private SQLiteDatabase dbHack;

    // create DB and table, if needed
    private void createDB() {
        dbHack=openOrCreateDatabase("HackeratiDB", Context.MODE_PRIVATE, null);
        dbHack.execSQL("CREATE TABLE IF NOT EXISTS fibonacci(fib_no VARCHAR);");
    }

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

        // set the DB component
        createDB();
    }

    // calculate the fibonacci sequence
    private List<String> calculate_fib(int length) {

        BigInteger first, second, result;
        List<String> listFibonacci;

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
        return listFibonacci;
    }

    // delete all existing data from the DB
    private void deleteDB() {
        dbHack.execSQL("DELETE FROM fibonacci");
    }

    // write the fibonacci sequence to the DB
    private void writeDB(List<String> fib_list) {
        for(int i = 0; i < fib_list.size(); i++)
        {
            ContentValues initialValues = new ContentValues();
            initialValues.put("fib_no", fib_list.get(i));
            dbHack.insert("fibonacci", null, initialValues);
        }
    }

    // read the fibonacci sequence from the DB to a String list
    private List<String> readDB() {
        List<String> listFibonacci = new ArrayList<String>();
        Cursor cur = dbHack.rawQuery("SELECT * FROM fibonacci", null);
        if (cur.moveToFirst()) {
            while (!cur.isAfterLast()) {
                String fib_no = cur.getString(cur.getColumnIndex("fib_no"));
                listFibonacci.add(fib_no);
                cur.moveToNext();
            }
        }
        cur.close();
        return listFibonacci;
    }

    // set the list view
    private void setListView(List<String> listFibonacci) {
        FibonacciAdapter fibonacciAdapter;

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
                        List<String> fib_list = calculate_fib(max_fib);
                        deleteDB();
                        writeDB(fib_list);
                        List<String> listFibonacci = readDB();
                        setListView(listFibonacci);
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
