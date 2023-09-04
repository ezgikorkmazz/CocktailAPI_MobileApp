package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    RecyclerView recyclerView;
    ProgressBar progressBar;
    LinearLayoutManager layoutManager;
    RCocktailsAdapter adapter1;
    List<RCocktails> cocktailList = new ArrayList<>();
    RCoctailsObject coctailsObject = new RCoctailsObject();



    private SearchView searchView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerview1);
        progressBar = findViewById(R.id.progressBar);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter1 = new RCocktailsAdapter(cocktailList);
        recyclerView.setAdapter(adapter1);



        //setRetrofitSettings();


        fetchRCocktails("");
        
        //search view
        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });


        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.Cocktails);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.Cocktails:
                        return true;

                    case R.id.Random:
                        startActivity(new Intent(getApplicationContext(),SurpriseMe.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.Favorites:
                        startActivity(new Intent(getApplicationContext(),Favorites.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    private void filterList(String newText) {
        fetchRCocktails(newText);
        List<RCocktails> c = new ArrayList<>();
        for (RCocktails rc : cocktailList) {
            if (rc.getStrDrink().toLowerCase().contains(newText.toLowerCase()))
                c.add(rc);
        }

        if (c.isEmpty()){
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }else {
            adapter1.setCocktailsList(c);
        }
    }

    private void fetchRCocktails(String search) {
        progressBar.setVisibility(View.VISIBLE);
        RetrofitClient1.getRetrofitClient().getCocktails(search).enqueue(new Callback<RCoctailsObject>() {
            @Override
            public void onResponse(Call<RCoctailsObject> call, Response<RCoctailsObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    //cocktailList.addAll(response.body());
                    coctailsObject = response.body();
                    cocktailList.clear();
                    cocktailList.addAll(coctailsObject.getDrinks());
                    System.out.println("----------------------" + cocktailList.get(0).getStrDrink());
                    adapter1.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<RCoctailsObject> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }


}