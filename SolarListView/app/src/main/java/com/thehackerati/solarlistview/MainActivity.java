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
import java.util.HashMap;
import java.util.List;

//http://www.seasky.org/solar-system/venus.html
public class MainActivity extends Activity {
    private ListView celestialListView;
    private List<String> listCelestialBodies;

    private HashMap<String, ArrayList<String>> mapPlanetMoons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        celestialListView = (ListView) findViewById(R.id.celestial_list_view);

        celestialListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] links = getResources().getStringArray(R.array.celestial_bodies_links);
                Uri uri = Uri.parse(links[position]);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        Resources res = getResources();
        listCelestialBodies = Arrays.asList(res.getStringArray(R.array.celestial_bodies_array));

        mapPlanetMoons = new HashMap<String, ArrayList<String>>() {{
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
    }

    @Override
    protected void onResume() {
        CelestialAdapter celestialBodyAdapter;
        super.onResume();

        celestialBodyAdapter = new CelestialAdapter(listCelestialBodies, this.getApplicationContext());
        celestialListView.setAdapter(celestialBodyAdapter);
    }
}
