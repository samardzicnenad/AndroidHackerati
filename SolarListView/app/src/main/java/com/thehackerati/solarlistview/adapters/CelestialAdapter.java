package com.thehackerati.solarlistview.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.thehackerati.solarlistview.R;

import java.util.List;
import java.util.Random;

public class CelestialAdapter extends BaseAdapter {
    private Context context;

    private final List<String> celestialBodies;

    public CelestialAdapter(List<String> celestialBodies, Context current) {
        this.celestialBodies = celestialBodies;
        this.context = current;
    }

    @Override
    public int getCount() {
        return celestialBodies.size();
    }

    @Override
    public Object getItem(int position) {
        return celestialBodies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.celestial_body, parent, false);
        TextView celestialTextView = (TextView) convertView.findViewById(R.id.celestialBodyName);
        String celestialName = celestialBodies.get(position);
        celestialTextView.setText(celestialName.toUpperCase());
        celestialTextView.setTextColor(Color.WHITE);
        celestialTextView.setTypeface(null, Typeface.BOLD_ITALIC);

        Random random = new Random();

        convertView.setBackgroundColor(Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256)));

        ImageView celestialImageView = (ImageView) convertView.findViewById(R.id.celestialBodyIcon);
        int resID = context.getResources().getIdentifier(celestialName, "drawable", "com.thehackerati.solarlistview");
        celestialImageView.setImageResource(resID);

        return convertView;
    }
}
