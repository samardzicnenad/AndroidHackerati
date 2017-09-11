package com.thehackerati.solarlistview;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.thehackerati.solarlistview.adapters.CelestialAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

    private ListView lvCelestialLinks;
    private List<String> listSunPlanets,
            listCelestialBodies = new ArrayList<String>();
    private HashMap<String, String> mapCBodyLink,
            mapPlanetMoon;

    // creates a HashMap from a String array
    private HashMap<String, String> createHashMap(int resourceId) {
        // read strings from resources
        String[] stringArray = getResources().getStringArray(resourceId);
        // declare a HashMap to return
        HashMap<String, String> aHashMap = new HashMap<String, String>();
        // iterate over the strings array and split them on "|"
        for (String entry : stringArray) {
            String[] splitResult = entry.split("\\|");
            aHashMap.put(splitResult[0], splitResult[1]);
        }
        return aHashMap;
    }

    // create a complete list of celestial bodies - sun, planets and moons
    private void setFullCelestialList() {
        // set a list for the adapter
        for(String planet : listSunPlanets) {
            // add the sun or a planet to the list
            listCelestialBodies.add(planet);
            // add all (if any) moons to the list
            for (Map.Entry<String, String> entry : mapPlanetMoon.entrySet()) {
                String moonPlanet = entry.getValue();
                if(moonPlanet.equals(planet)) {
                    listCelestialBodies.add(entry.getKey());
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create a shuffled list of (sun and) planets
        Resources res = getResources();
        listSunPlanets = Arrays.asList(res.getStringArray(R.array.sun_planets));
        //Collections.shuffle(listSunPlanets);

        // create HashMaps from resource String arrays and the complete list of bodies
        mapCBodyLink = createHashMap(R.array.celestial_bodies_links);
        mapPlanetMoon = createHashMap(R.array.moon_planet);
        setFullCelestialList();

        // layout ListView
        lvCelestialLinks = (ListView) findViewById(R.id.celestial_list_view);
    }

    // sets listeners
    private void setupListeners() {
        // defines onClick action for ListView elements
        lvCelestialLinks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // get a url for the celestial body and open it
                Uri uri = Uri.parse(mapCBodyLink.get(listCelestialBodies.get(position)));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        CelestialAdapter celestialBodyAdapter;

        super.onResume();
        setupListeners();

        // create and set an adapter
        celestialBodyAdapter = new CelestialAdapter(listCelestialBodies,
                mapPlanetMoon,
                this.getApplicationContext());
        lvCelestialLinks.setAdapter(celestialBodyAdapter);
    }
}
