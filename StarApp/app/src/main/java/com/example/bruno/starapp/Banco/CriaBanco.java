package com.example.bruno.starapp.Banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bruno on 09/03/2016.
 */
public class CriaBanco extends SQLiteOpenHelper{

    // static final, pois a variavel não sera modificada
    public static final String NOME_BANCO = "banco.db";
    public static final String TABELA = "estrelas";
    public static final String ID = "_id";
    public static final String NOME_ALUNO = "nomeAluno";
    public static final String AULA = "aula";
    public static final String NUM_ESTRELAS = "numEstrelas";
    private static final int VERSAO = 1;


    public CriaBanco(Context context) {
        super(context,NOME_BANCO, null, VERSAO);
    }

    //Cria Banco de dados
    @Override
    public void onCreate(SQLiteDatabase db) {
       String sql = "CREATE TABLE estrelas ("+
               "_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
               "nomeAluno TEXT,"+
               "aula TEXT,"+
               "numEstrelas TEXT)";

        db.execSQL(sql);

    }
    // Edita Banco

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS"+TABELA);
        onCreate(db);
    }
}
