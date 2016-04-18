package com.example.bruno.starapp.Funcoes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.bruno.starapp.Banco.BancoController;
import com.example.bruno.starapp.MainActivity;
import com.example.bruno.starapp.R;

import java.util.Random;

public class addEstrela extends AppCompatActivity {
    EditText edtNome,edtAula;
    RatingBar rtEstrela;
    Button btSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_estrela);
        BtSalvarEstrela();
    }

    public void BtSalvarEstrela(){

        btSalvar = (Button)findViewById(R.id.btSalvar);

            btSalvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // chamar o controlador do banco
                    BancoController crud = new BancoController(getBaseContext());
                    EditText etNome = (EditText) findViewById(R.id.edtNome);
                    EditText etAula = (EditText) findViewById(R.id.edtAula);
                    RatingBar rtEstrela = (RatingBar) findViewById(R.id.rtEstrelas);
                    String nomeAString = etNome.getText().toString();
                    String aulaString = etAula.getText().toString();
                    int numEstrelaInt = Integer.parseInt(Integer.toString(rtEstrela.getNumStars()));
                    String resultado;

                  if(etNome.getText().toString().length()<=0){
                      etNome.setError("Campo Vazio!");
                      etNome.requestFocus();
                  }else if(etAula.getText().toString().length()<=0){
                      etAula.setError("Campo Vazio!");
                      etAula.requestFocus();
                  } else {
                      resultado = crud.InserirDados(nomeAString, aulaString, String.valueOf(numEstrelaInt));

                      Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                      MenuPrincipal(v);
                  }
                }
            });
    }

    public void MenuPrincipal(View v){
        Intent i = new Intent(this.getApplicationContext(),MainActivity.class);
        startActivity(i);
    }
}
