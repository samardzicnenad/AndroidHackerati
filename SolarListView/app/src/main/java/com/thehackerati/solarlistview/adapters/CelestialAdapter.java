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

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class CelestialAdapter extends BaseAdapter {

    private Context context;
    private final List<String> celestialBodies;
    private final HashMap<String, String> mapPlanetMoons;
    private final HashMap<String, Integer> mapPlanetColor = new HashMap<String, Integer>();

    // set a random color for a planet (and its moons)
    private void setColors() {
        Random random = new Random();
        Integer planetColor;
        // iterate all of the celestial bodies
        for (String cBody: celestialBodies) {
            // look for the body in the list of the moons
            String moon = mapPlanetMoons.get(cBody);
            // if not a moon - set a color to the celestial body
            if (moon == null) {
                planetColor = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
                mapPlanetColor.put(cBody, planetColor);
            }
        }
    }

    // class constructor
    public CelestialAdapter(List<String> celestialBodies,
                            HashMap<String, String> mapPlanetMoons,
                            Context currentContext) {
        this.celestialBodies = celestialBodies;
        this.mapPlanetMoons = mapPlanetMoons;
        this.context = currentContext;

        setColors();
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

        String parentPlanet;

        if(convertView == null) {
            convertView = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.celestial_body, parent, false);
        }

        // set TextView part of the layout
        TextView celestialTextView = (TextView) convertView.findViewById(R.id.celestialBodyName);
        String celestialName = celestialBodies.get(position);
        celestialTextView.setText(celestialName.toUpperCase());
        celestialTextView.setTextColor(Color.WHITE);
        celestialTextView.setTypeface(null, Typeface.BOLD_ITALIC);

        // set padding and the background color for the view
        if (mapPlanetMoons.containsKey(celestialName)) {
            // if it is a moon - indent more and get the color from the planet
            celestialTextView.setPadding(100, 0, 0, 0);
            parentPlanet = mapPlanetMoons.get(celestialName);
            convertView.setBackgroundColor(mapPlanetColor.get(parentPlanet));
        } else {
            // indent less and get the planet's color
            celestialTextView.setPadding(30, 0, 0, 0);
            convertView.setBackgroundColor(mapPlanetColor.get(celestialName));
        }

        // set a source for the ImageView
        ImageView celestialImageView = (ImageView) convertView.findViewById(R.id.celestialBodyIcon);
        int resID = context.getResources().getIdentifier(celestialName, "drawable", "com.thehackerati.solarlistview");
        celestialImageView.setImageResource(resID);

        return convertView;
    }
}
