package com.thehackerati.fibonacci.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.thehackerati.fibonacci.R;

import java.util.List;

// custom Adapter class
public class FibonacciAdapter extends BaseAdapter {

    private final List<String> listFibonacci;

    public FibonacciAdapter(List<String> listFibonacci) {
        this.listFibonacci = listFibonacci;
    }

    @Override
    public int getCount() {
        return listFibonacci.size();
    }

    @Override
    public Object getItem(int position) {
        return listFibonacci.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.fibonacci_layout, parent, false);
        }

        // set TextView
        TextView fibonacciTextView = (TextView) convertView.findViewById(R.id.fibonacci_number);
        String fibNum = listFibonacci.get(position);
        fibonacciTextView.setText(fibNum);

        return convertView;
    }
}
