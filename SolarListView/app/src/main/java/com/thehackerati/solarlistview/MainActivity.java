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

import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {
    private ListView celestialListView;
    private List<String> listCelestialBodies;

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
    }

    @Override
    protected void onResume() {
        CelestialAdapter celestialBodyAdapter;
        super.onResume();

        celestialBodyAdapter = new CelestialAdapter(listCelestialBodies, this.getApplicationContext());
        celestialListView.setAdapter(celestialBodyAdapter);
    }
}
