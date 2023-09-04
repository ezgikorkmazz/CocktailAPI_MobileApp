package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class InfoActivity extends AppCompatActivity {
    TextView title, body;
    ImageView img;

    ImageButton fav;
    List<Favorites> f= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        title = (TextView) findViewById(R.id.detailTitle);
        body = (TextView) findViewById(R.id.detailsDesc);
        img = (ImageView) findViewById(R.id.detailImage);
        fav = (ImageButton) findViewById(R.id.imageButton);
        body.setVisibility(View.INVISIBLE);
        img.setVisibility(View.INVISIBLE);
        fav.setVisibility(View.INVISIBLE);
        Bundle data = getIntent().getExtras();
        title.setText(data.getString("title"));

        if (!title.getText().toString().equals("No Favorites Yet")) {
            body.setVisibility(View.VISIBLE);
            img.setVisibility(View.VISIBLE);
            fav.setVisibility(View.VISIBLE);
            body.setText(data.getString("body"));
            body.setMovementMethod(new ScrollingMovementMethod());
            if (!data.getString("img").equals("")){
                Picasso.get().load(data.getString("img")).into(img);
            }

            fav = (ImageButton) findViewById(R.id.imageButton);

            if (isFavorite(title.getText().toString())) {
                fav.setColorFilter(Color.parseColor("#00FF00"));
            } else {
                fav.clearColorFilter();
            }
            fav.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (isFavorite(title.getText().toString())) {
                        fav.clearColorFilter();
                        removeFavorite(title.getText().toString());
                    } else {
                        fav.setColorFilter(Color.parseColor("#00FF00"));
                        saveCocktail(title.getText().toString() + "#" + data.getString("body")
                                + "#" + data.getString("body2") + "#" + data.getString("img"));
                    }
                }

            });
        }

    }
    public boolean isFavorite(String title) {
        boolean isExist = false;
        String[] list = (getFavoriteCocktail().equals("") ? "No Favorites Yet, , , ;" : getFavoriteCocktail()).split(";");
        for (String item:
             list) {
            String[] object = item.split("#");
            if (object[0].equals(title)) {
                isExist = true;
            }
        }
        return isExist;
    }
    public void removeFavorite(String title) {
        String willBeSaved = "";
        String[] list = (getFavoriteCocktail().equals("") ? "No Favorites Yet, , , ;" : getFavoriteCocktail()).split(";");
        for (String item:
                list) {
            String[] object = item.split("#");
            if (!object[0].equals(title)) {
                if (willBeSaved.equals("")) {
                    willBeSaved = object[0] + "#" + object[1]  + "#" + object[2]  + "#" + object[3];
                } else {
                    willBeSaved = willBeSaved + ";" + object[0] + "#" + object[1]  + "#" + object[2]  + "#" + object[3];
                }
            }
        }
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("favoriteCocktails", MODE_PRIVATE);
            fos.write(willBeSaved.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void saveCocktail(String userName) {
        String willBeSaved = "";
        if (getFavoriteCocktail().equals("")) {
            willBeSaved = userName;
        } else {
            willBeSaved = getFavoriteCocktail() + ";" + userName;
        }
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("favoriteCocktails", MODE_PRIVATE);
            fos.write(willBeSaved.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

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