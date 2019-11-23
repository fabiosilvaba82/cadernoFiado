package com.mrc.appcadernofiado.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.mrc.appcadernofiado.model.Produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private SQLiteDatabase db = null;
    private DataHelper banco;

    public ProdutoDAO(Context context) {
        banco = new DataHelper(context);
    }


    public void salvarProdutoDAO(Produto p) {
        db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("preco", p.getPreco().setScale(2).toString());
        values.put("descricao", p.getDescricao());
        db.insert(Constantes.PRODUTO, null, values);
        Log.i("FFFFFFFFFFFFFF", p.getDescricao());
    }

    public List<Produto> listarProdutosDAO() {
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + Constantes.PRODUTO, null);
        List<Produto> lista = new ArrayList<>();
        while ((cursor.moveToNext())) {
            Produto p = new Produto();
            p.setId(cursor.getLong(cursor.getColumnIndex("produtoID")));
            p.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            p.setPreco(new BigDecimal(cursor.getDouble(cursor.getColumnIndex("preco"))));
            lista.add(p);
        }
        cursor.close();
        return lista;

    }

    public boolean excluirProdutoDAO(Produto produto) {
        db = banco.getWritableDatabase();
        if (1 == 1) {
            String[] args = {produto.getId().toString()};
            db.delete(Constantes.PRODUTO, "produtoID=?", args);
            return true;
        }
        return false;
    }

    public Produto obterProdutoDAO(Long codProduto) {
        db = banco.getReadableDatabase();
        String[] args = {codProduto.toString()};
        Cursor cursor = db.rawQuery("select * from " + Constantes.PRODUTO + " where produtoID = ? ", args);
        cursor.moveToFirst();
        Produto produto = new Produto();
        if (cursor.getCount() > 0) {
            produto.setId(cursor.getLong(cursor.getColumnIndex("produtoID")));
            produto.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            produto.setPreco(new BigDecimal(cursor.getString(cursor.getColumnIndex("preco"))));
        }
        return produto;
    }
}
