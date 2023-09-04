package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SurpriseMe extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    List<RCocktails> cocktailList = new ArrayList<>();
    RCoctailsObject coctailsObject = new RCoctailsObject();
    TextView title;
    ImageView img;
    TextView desc1;
    Button reroll;

    //SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surprise_me);

        title = (TextView) findViewById(R.id.detailTitle);
        img = (ImageView) findViewById(R.id.detailImage);
        desc1 = (TextView) findViewById(R.id.detailsDesc);
        reroll = (Button) findViewById(R.id.button);

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.Random);

        desc1.setMovementMethod(new ScrollingMovementMethod());

        /*swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });*/

        System.out.println("---------------------- once");
        fetchRandom();
        System.out.println("---------------------- sonra");
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.Cocktails:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.Random:
                        return true;

                    case R.id.Favorites:
                        startActivity(new Intent(getApplicationContext(),Favorites.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
        reroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchRandom();
            }
        });


    }



    private void fetchRandom() {
        RetrofitClient1.getRetrofitClient().getRandom().enqueue(new Callback<RCoctailsObject>() {
            @Override
            public void onResponse(Call<RCoctailsObject> call, Response<RCoctailsObject> response) {
                System.out.println("---------------------- icerde" +  response);
                if (response.isSuccessful() && response.body() != null) {
                    System.out.println("---------------------- response successfull");
                    coctailsObject = response.body();
                    cocktailList.clear();
                    cocktailList.addAll(coctailsObject.getDrinks());
                    Log.e("suprise me", cocktailList.get(0).getStrDrink());
                    Log.e("response body", String.valueOf(response.body()));
                    System.out.println("----------------------" + cocktailList.get(0).getStrDrink());
                    title.setText(cocktailList.get(0).getStrDrink());
                    Picasso.get().load(cocktailList.get(0).getStrDrinkThumb()).into(img);
                    String bodyStr = "Drink Type : " + ((cocktailList.get(0).getStrAlcoholic() == null) ?  "" : cocktailList.get(0).getStrAlcoholic() + "\n\n") +
                            "Ingredients\n" + ((cocktailList.get(0).getStrMeasure1() == null) ? "" : (cocktailList.get(0).getStrMeasure1() + cocktailList.get(0).getStrIngredient1() + "\n")) +
                            ((cocktailList.get(0).getStrMeasure2() == null) ? "" : (cocktailList.get(0).getStrMeasure2())) + ((cocktailList.get(0).getStrIngredient2() == null) ? "" : (cocktailList.get(0).getStrIngredient2() + "\n")) +
                            ((cocktailList.get(0).getStrMeasure3() == null) ? "" : (cocktailList.get(0).getStrMeasure3())) + ((cocktailList.get(0).getStrIngredient3() == null) ? "" : (cocktailList.get(0).getStrIngredient3() + "\n")) +
                            ((cocktailList.get(0).getStrMeasure4() == null) ? "" : (cocktailList.get(0).getStrMeasure4())) + ((cocktailList.get(0).getStrIngredient4() == null) ? "" : (cocktailList.get(0).getStrIngredient4() + "\n")) +
                            ((cocktailList.get(0).getStrMeasure5() == null) ? "" : (cocktailList.get(0).getStrMeasure5())) + ((cocktailList.get(0).getStrIngredient5() == null) ? "" : (cocktailList.get(0).getStrIngredient5() + "\n")) +
                            ((cocktailList.get(0).getStrMeasure6() == null) ? "" : (cocktailList.get(0).getStrMeasure6())) + ((cocktailList.get(0).getStrIngredient6() == null) ? "" : (cocktailList.get(0).getStrIngredient6() + "\n")) +
                            ((cocktailList.get(0).getStrMeasure7() == null) ? "" : (cocktailList.get(0).getStrMeasure7())) + ((cocktailList.get(0).getStrIngredient7() == null) ? "" : (cocktailList.get(0).getStrIngredient7() + "\n")) +
                            ((cocktailList.get(0).getStrMeasure8() == null) ? "" : (cocktailList.get(0).getStrMeasure8())) + ((cocktailList.get(0).getStrIngredient8() == null) ? "" : (cocktailList.get(0).getStrIngredient8() + "\n")) +
                            ((cocktailList.get(0).getStrMeasure9() == null) ? "" : (cocktailList.get(0).getStrMeasure9())) + ((cocktailList.get(0).getStrIngredient9() == null) ? "" : (cocktailList.get(0).getStrIngredient9() + "\n")) +
                            ((cocktailList.get(0).getStrMeasure10() == null) ? "" : (cocktailList.get(0).getStrMeasure10())) + ((cocktailList.get(0).getStrIngredient10() == null) ? "" : (cocktailList.get(0).getStrIngredient10() + "\n")) +
                            ((cocktailList.get(0).getStrMeasure11() == null) ? "" : (cocktailList.get(0).getStrMeasure11())) + ((cocktailList.get(0).getStrIngredient11() == null) ? "" : (cocktailList.get(0).getStrIngredient11() + "\n")) +
                            ((cocktailList.get(0).getStrMeasure12() == null) ? "" : (cocktailList.get(0).getStrMeasure12())) + ((cocktailList.get(0).getStrIngredient12() == null) ? "" : (cocktailList.get(0).getStrIngredient12() + "\n")) +
                            ((cocktailList.get(0).getStrMeasure13() == null) ? "" : (cocktailList.get(0).getStrMeasure13())) + ((cocktailList.get(0).getStrIngredient13() == null) ? "" : (cocktailList.get(0).getStrIngredient13() + "\n")) +
                            ((cocktailList.get(0).getStrMeasure14() == null) ? "" : (cocktailList.get(0).getStrMeasure14())) + ((cocktailList.get(0).getStrIngredient14() == null) ? "" : (cocktailList.get(0).getStrIngredient14() + "\n")) +
                            ((cocktailList.get(0).getStrMeasure15() == null) ? "" : (cocktailList.get(0).getStrMeasure15())) + ((cocktailList.get(0).getStrIngredient15() == null) ? "" : cocktailList.get(0).getStrIngredient15());

                    String bodyStr2= cocktailList.get(0).getStrInstructions();
                    String[] arr = bodyStr.split("null");
                    String temp =  "";

                    for (String s :
                            arr) {
                        temp = temp+ s;
                    }
                    bodyStr = temp + "\n" + bodyStr2;

                    desc1.setText(bodyStr);
                }
            }


            @Override
            public void onFailure(Call<RCoctailsObject> call, Throwable t) {
                Toast.makeText(SurpriseMe.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
}