package com.example.bruno.starapp.Funcoes;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bruno.starapp.Banco.BancoController;
import com.example.bruno.starapp.Banco.CriaBanco;
import com.example.bruno.starapp.R;

public class Editar extends AppCompatActivity {
    Button btAlterar, btDelete,btAtualiza;
    Cursor cursor;
    BancoController crud;
    String cod;
    TextView txtNome, txtAula, txtNum;
    EditText edtEstrelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        try {
            Intent it = getIntent();
            if (it != null) {
                Bundle bundle = it.getExtras();
                if (bundle != null) {
                    cod = bundle.getString("cod");
                }
            }
            //Estanciando função do banco
            crud = new BancoController(getBaseContext());

            edtEstrelas = (EditText) findViewById(R.id.edtQtd);
            txtNome = (TextView) findViewById(R.id.txtNome);
            txtAula = (TextView) findViewById(R.id.txtAula);
            txtNum = (TextView) findViewById(R.id.txtNumEstrela);
            btAlterar = (Button) findViewById(R.id.btAlteraEstrela);
            btDelete = (Button)findViewById(R.id.btExcluir);
            btAtualiza = (Button)findViewById(R.id.btAtualiza);


            cursor = crud.carregarPeloID(Integer.parseInt(cod));

            txtNome.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.NOME_ALUNO)));
            txtAula.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.AULA)));
            txtNum.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.NUM_ESTRELAS)));

            edtEstrelas.setEnabled(false);


            btAtualiza.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    crud.alterarEstrela(Integer.parseInt(cod), edtEstrelas.getText().toString());
                    Intent it = new Intent(Editar.this, ConsultaEstrela.class);
                    startActivity(it);
                    finish();
                }
            });

            btDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    crud.deletaEstrela(Integer.parseInt(cod));
                    Intent it = new Intent(Editar.this, ConsultaEstrela.class);
                    startActivity(it);
                    finish();
                }
            });

        } catch (Exception e) {
            String msg;
            msg = e.getMessage();
        }
    }

    public void adiEstrela(View v){
        txtNum.setEnabled(false);
    }
}
