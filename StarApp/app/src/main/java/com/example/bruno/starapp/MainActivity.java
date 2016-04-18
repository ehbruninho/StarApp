package com.example.bruno.starapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.bruno.starapp.Funcoes.ConsultaEstrela;
import com.example.bruno.starapp.Funcoes.addEstrela;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addEstrela(View v){
        Intent i = new Intent(this.getApplication(), addEstrela.class);
        startActivity(i);
    }

    public void btVerEstrela(View v){
        Intent i = new Intent(this.getApplication(), ConsultaEstrela.class);
        startActivity(i);
    }
}
