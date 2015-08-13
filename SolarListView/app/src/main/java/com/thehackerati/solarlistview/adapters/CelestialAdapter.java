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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class CelestialAdapter extends BaseAdapter {
    private Context context;
    private HashMap<String, ArrayList<String>> mapPlanetMoons = new HashMap<String, ArrayList<String>>() {{
        put("sun", new ArrayList<String>() {{}});
        put("mercury", new ArrayList<String>() {{}});
        put("venus", new ArrayList<String>() {{}});
        put("earth", new ArrayList<String>() {{add("moon");}});
        put("mars", new ArrayList<String>() {{add("phobos"); add("deimos");}});
        put("jupiter", new ArrayList<String>() {{add("europa"); add("ganymede"); add("io"); add("callisto");}});
        put("saturn", new ArrayList<String>() {{add("mimas"); add("enceladus"); add("tethys"); add("dione"); add("rhea"); add("titan"); add("iapetus");}});
        put("uranus", new ArrayList<String>() {{add("miranda"); add("ariel"); add("umbriel"); add("titania"); add("oberon");}});
        put("neptune", new ArrayList<String>() {{add("triton");}});
        put("pluto", new ArrayList<String>() {{add("charon");}});
    }};

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
