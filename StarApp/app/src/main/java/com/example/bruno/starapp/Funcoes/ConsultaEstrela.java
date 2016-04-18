package com.example.bruno.starapp.Funcoes;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.bruno.starapp.Banco.BancoController;
import com.example.bruno.starapp.R;


public class ConsultaEstrela extends AppCompatActivity {
    private ListView lista;
    EditText etAula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        etAula = (EditText)findViewById(R.id.etPesquisa);

        BancoController crud = new BancoController(getBaseContext());
        final Cursor cursor = crud.carregaDados();

        String[] nomeCampos={"nomeAluno"};
        int[] idView = new int[]{R.id.txtNomeAluno};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(this, R.layout.lista_estrelas,cursor,nomeCampos,idView,0);

        lista=(ListView)findViewById(R.id.ltvDados);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String c;
                cursor.moveToPosition(position);
                c = String.valueOf(cursor.getInt(cursor.getColumnIndex("_id")));
                Intent it = new Intent(ConsultaEstrela.this, Editar.class);
                it.putExtra("cod",c);
                startActivity(it);
                finish();
            }
        });
    }

    public void btPesquisa(View v){
        if(etAula.getText().length()<=0){
            etAula.setError("Campo Vazio!");
            etAula.requestFocus();
        }else{

        BancoController crud = new BancoController(getBaseContext());
        Cursor cursor = crud.buscaPorCadeira(etAula.getText().toString());
        String[] nomeCampos={"nomeAluno"};
        int[] idView = new int[]{R.id.txtNomeAluno};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(this, R.layout.lista_estrelas,cursor,nomeCampos,idView,0);

        lista=(ListView)findViewById(R.id.ltvDados);
        lista.setAdapter(adaptador);


        }
    }


}
