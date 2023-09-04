package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Favorites extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    FavoritesAdapter adapter1;
    List<FavoriteModel> favoriteList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.Favorites);
        recyclerView = findViewById(R.id.recyclerview1);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter1 = new FavoritesAdapter(favoriteList);
        recyclerView.setAdapter(adapter1);
        favoriteList.clear();
        String[] list = (getFavoriteCocktail().equals("") ? "No Favorites Yet, , , ;" : getFavoriteCocktail()).split(";");
        Log.e("list", list[0]);
        int i = 0;
        for (String l :
                list) {
            String[] object = l.split("#");
            FavoriteModel fav = new FavoriteModel(object[0], object[1], object[2], object[3]);
            favoriteList.add(fav);
            i++;
        }
        adapter1.notifyDataSetChanged();


        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.Cocktails:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.Random:
                        startActivity(new Intent(getApplicationContext(),SurpriseMe.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.Favorites:
                        return true;
                }
                return false;
            }
        });
    }
    public String getFavoriteCocktail() {
        FileInputStream fis = null;
        String final_text = "";
        try {
            fis = openFileInput("favoriteCocktails");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null) {
                sb.append(text).append("");
            }
            final_text = sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return final_text;
    }
}