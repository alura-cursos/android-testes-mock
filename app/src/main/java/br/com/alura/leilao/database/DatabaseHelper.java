package br.com.alura.leilao.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.alura.leilao.database.contrato.UsuarioContrato;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO_DE_DADOS = "leilao-db";
    private static final int VERSAO = 1;

    private static final String TABELA_USUARIO = "CREATE TABLE " + UsuarioContrato.TABELA_NOME + " ("
            + UsuarioContrato._ID + " INTEGER PRIMARY KEY,"
            + UsuarioContrato.CHAVE_NOME + " TEXT)";


    protected DatabaseHelper(Context context) {
        super(context, NOME_BANCO_DE_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABELA_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int versaoNova) {

    }

}
