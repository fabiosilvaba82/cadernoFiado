package com.mrc.appcadernofiado.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mauricio.carvalho.
 */
public class DataHelper extends SQLiteOpenHelper {


    public DataHelper(Context context) {
        super(context, Constantes.BANCO, null, Constantes.VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + Constantes.PRODUTO + "(" +
                " produtoID integer primary key autoincrement," +
                " descricao text ," +
                " preco decimal(10,2)" +
                " )");

        db.execSQL("insert into " + Constantes.PRODUTO + " values (null, 'Arroz', 2.99)");
        db.execSQL("insert into " + Constantes.PRODUTO + " values (null, 'Feijão', 5.99)");
        db.execSQL("insert into " + Constantes.PRODUTO + " values (null, 'Acucar', 1.99)");
        db.execSQL("insert into " + Constantes.PRODUTO + " values (null, 'Macarrão', 3.99)");
        db.execSQL("insert into " + Constantes.PRODUTO + " values (null, 'Farinha', 2.99)");
        db.execSQL("insert into " + Constantes.PRODUTO + " values (null, 'Peixe', 5.99)");
        db.execSQL("insert into " + Constantes.PRODUTO + " values (null, 'Sardinha', 1.99)");
        db.execSQL("insert into " + Constantes.PRODUTO + " values (null, 'Ração', 3.99)");
        db.execSQL("insert into " + Constantes.PRODUTO + " values (null, 'Salsa', 1.99)");
        db.execSQL("insert into " + Constantes.PRODUTO + " values (null, 'Camarão', 3.99)");
        db.execSQL("insert into " + Constantes.PRODUTO + " values (null, 'Tomate', 1.99)");
        db.execSQL("insert into " + Constantes.PRODUTO + " values (null, 'Cebola', 3.99)");

        db.execSQL("create table if not exists " + Constantes.CLIENTE + "(" +
                " clienteID integer primary key autoincrement," +
                " nome text," +
                " telefone text" +
                ")");

        db.execSQL("insert into " + Constantes.CLIENTE + " values (null, 'Mauricio Carvalho', '71 988417444')");
        db.execSQL("insert into " + Constantes.CLIENTE + " values (null, 'Fabio', '71 988529422' )");
        db.execSQL("insert into " + Constantes.CLIENTE + " values (null, 'Everton ', '71 98856778' )");
        db.execSQL("insert into " + Constantes.CLIENTE + " values (null, 'Maíra', '71 988417444' )");
        db.execSQL("insert into " + Constantes.CLIENTE + " values (null, 'Mariana', '71 991188060' )");

        db.execSQL("create table if not exists " + Constantes.VENDA + "(" +
                " vendaID integer primary key autoincrement," +
                " notaFiscal integer," +
                " quantidade integer," +
                " dataVenda datetime," +
                " idCliente integer," +
                " idProduto integer," +
                " foreign key (idCliente) references cliente(clienteID)," +
                " foreign key (idProduto) references produto(produtoID)" +
                " );");

        db.execSQL("insert into " + Constantes.VENDA + " values (null, 2343122, 2, '12-12-2019', 1, 1)");
        db.execSQL("insert into " + Constantes.VENDA + " values (null, 2343122, 2, '12-12-2019', 1, 2)");
        db.execSQL("insert into " + Constantes.VENDA + " values (null, 2343123, 2, '13-12-2019', 2, 1)");
        db.execSQL("insert into " + Constantes.VENDA + " values (null, 2343123, 2, '13-12-2019', 2, 2)");
        db.execSQL("insert into " + Constantes.VENDA + " values (null, 2343124, 2, '14-12-2019', 1, 3)");
        db.execSQL("insert into " + Constantes.VENDA + " values (null, 2343125, 2, '14-12-2019', 3, 3)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" drop table if exists " + Constantes.CLIENTE);
        db.execSQL(" drop table if exists " + Constantes.PRODUTO);
        db.execSQL(" drop table if exists " + Constantes.VENDA);
        onCreate(db);
    }
}
