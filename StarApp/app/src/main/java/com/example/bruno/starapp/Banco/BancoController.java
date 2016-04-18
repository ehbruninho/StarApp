package com.example.bruno.starapp.Banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by bruno on 09/03/2016.
 */
public class BancoController {
    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoController (Context context){
        banco = new CriaBanco(context);
        //utiliza funçoes da classe Cria Banco
    }

    public String InserirDados (String nome, String tipoAula, String numEstrelas){
        ContentValues valores; // variavel armazena valores
        long resultado;

        db = banco.getWritableDatabase(); // Cria ou abre o banco
        valores = new ContentValues();
        valores.put("nomeAluno", nome);
        valores.put("aula", tipoAula);
        valores.put("numEstrelas",numEstrelas);
        resultado = db.insert("estrelas", null, valores);
        db.close();// muito importante fechar banco após operação

        if(resultado==-1)
            return "Erro ao inserir registro";
                else
                    return "Registro inserido com sucesso";

    }
    public Cursor carregaDados(){
        Cursor cursor;
        db = banco.getReadableDatabase();
        //Comando para pesquisar na tabela, todos nomes de aluno
        cursor = db.rawQuery("SELECT _id, nomeAluno, numEstrelas FROM estrelas", null);

        if(cursor.getCount()>0){
            cursor.moveToFirst();


        }
        db.close();
        return cursor;
    }

    public Cursor buscaPorCadeira(String aula){
        Cursor c;
        db = banco.getReadableDatabase();

        c = db.query(CriaBanco.TABELA, new String[]{"_id", "nomeAluno"}, "aula LIKE ?", new String[]{"%" + aula + "%"}, null, null, null);

        if(c.getCount()>0){
            c.moveToFirst();
        }

        db.close();
        return c;
    }

    public Cursor carregarPeloID(int id) {

            Cursor cursor;
            String[] campos = {"_id", "nomeAluno", "aula", "numEstrelas"};
            String where = "_id=" + id;
            db = banco.getReadableDatabase();

                cursor = db.query(CriaBanco.TABELA, campos, where, null, null, null, null, null);

                if (cursor.getCount()>0) {
                    cursor.moveToFirst();
                }
                db.close();
                return cursor;

    }

    public void alterarEstrela(int id,String numEstrelas ){

            ContentValues valores;
            String where;

        db= banco.getWritableDatabase();

        where= CriaBanco.ID + "=" + id;
        valores = new ContentValues();
        valores.put(CriaBanco.NUM_ESTRELAS, numEstrelas);

        db.update(CriaBanco.TABELA, valores, where, null);
        db.close();
    }

    public void deletaEstrela(int id){
        String where = CriaBanco.ID + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(CriaBanco.TABELA, where,null);
        db.close();
    }
}
