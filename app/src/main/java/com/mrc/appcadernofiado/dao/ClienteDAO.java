package com.mrc.appcadernofiado.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.mrc.appcadernofiado.model.Cliente;
import com.mrc.appcadernofiado.util.DataUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClienteDAO {
//    private final ConnectionFactory factory;

    private SQLiteDatabase db;
    private DataHelper banco;

    public ClienteDAO(Context context) {
        banco = new DataHelper(context);
    }


    public long salvarClienteDAO(Cliente cliente) {
        db = banco.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("nome", cliente.getNome());
        cv.put("telefone", cliente.getTelefone());

        return db.insert(Constantes.CLIENTE, null, cv);
    }

    public List<Cliente> listarTodosDAO() {
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + Constantes.CLIENTE, null);
        List<Cliente> clientes = new ArrayList<>();
        while (cursor.moveToNext()) {
            Cliente c = new Cliente();
            c.setId(cursor.getLong(cursor.getColumnIndex("clienteID")));
            c.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            c.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
              clientes.add(c);
        }
        cursor.close();
        return clientes;
    }

    public boolean removerClienteDAO(Cliente cliente) {
        db = banco.getWritableDatabase();
        if (1 == 1) {
            String[] args = {cliente.getId().toString()};
            db.delete(Constantes.CLIENTE, "clienteID=?", args);
            return true;
        }
        return false;
    }

    public boolean editarCliente(Cliente cliente) {
        db = banco.getWritableDatabase();
        if (1 == 1) {
            ContentValues cv = new ContentValues();
            cv.put("nome", cliente.getNome());
            cv.put("telefone", cliente.getTelefone());


            String[] args = {cliente.getId().toString()};
            db.update(Constantes.CLIENTE, cv, "clienteID=?", args);
            return true;
        }
        return false;
    }


    public Cliente obterClientePorID(Cliente cliente) {
        db = banco.getReadableDatabase();
        Long id = cliente.getId();
        String[] args = {id.toString()};
        Cursor cursor = db.rawQuery("select * from " + Constantes.CLIENTE + " where clienteID = ? ", args);
        Cliente cli = new Cliente();
        if (cursor.getCount() > 0) {
            cli.setId(cursor.getLong(cursor.getColumnIndex("clienteID")));
            cli.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            cli.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
        }
        cursor.close();
        return cli;
    }
}
