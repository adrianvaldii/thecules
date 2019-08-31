package com.example.belajardicoding;

import android.graphics.Typeface;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView imgVIew = this.findViewById(R.id.imagePoster);
        TextView namePoster = this.findViewById(R.id.namePoster);
        TextView positionPoster = this.findViewById(R.id.positionPoster);
        TextView numberPoster = this.findViewById(R.id.numberPoster);

        namePoster.setText(getIntent().getStringExtra("namePoster"));
        numberPoster.setText(getIntent().getStringExtra("noPoster"));
        positionPoster.setText(getIntent().getStringExtra("positionPoster"));
        Glide.with(DetailActivity.this).load(getIntent().getStringExtra("imagePoster")).apply(new RequestOptions().override(100, 100)).into(imgVIew);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Light.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/JerseyM54-aLX9.ttf");

        positionPoster.setTypeface(custom_font);

        numberPoster.setTypeface(custom_font2);
    }

}
