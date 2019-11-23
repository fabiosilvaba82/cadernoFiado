package com.mrc.appcadernofiado.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.mrc.appcadernofiado.model.Cliente;
import com.mrc.appcadernofiado.model.Produto;
import com.mrc.appcadernofiado.model.Venda;
import com.mrc.appcadernofiado.model.VendaVO;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VendaDAO {
    private DataHelper banco;
    private SQLiteDatabase db;

    public VendaDAO(Context context) {
        banco = new DataHelper(context);
    }

    public void salvarDAO(Venda venda) {
        db = banco.getWritableDatabase();
        ContentValues cv = new ContentValues();
//        cv.put("vendaID", venda.getId());
        cv.put("quantidade", venda.getQuantidade());
        cv.put("dataVenda", venda.getDataVenda().toString());
        cv.put("notaFiscal", venda.getNotaFiscal());
        cv.put("idCliente", venda.getIdCliente().getId());
        cv.put("idProduto", venda.getIdProduto().getId());

        db.insert(Constantes.VENDA, null, cv);
    }

    public List<VendaVO> listarVendasDAO(Cliente cli) {
        db = banco.getReadableDatabase();
        String[] args = {cli.getId().toString()};
        Cursor cursor = db.rawQuery("select * from " + Constantes.VENDA + " v " +
                " inner join " + Constantes.PRODUTO + " p on p.produtoID = v.idProduto " +
                " inner join " + Constantes.CLIENTE + " c on c.clienteID = v.idCliente " +
                " where v.idCliente=?", args);
        List<VendaVO> lista = new ArrayList<>();

        while (cursor.moveToNext()) {
            VendaVO v = new VendaVO();
            v.setIdVenda(cursor.getLong(cursor.getColumnIndex("vendaID")));
            v.setQuantidade(cursor.getInt(cursor.getColumnIndex("quantidade")));
            v.setDescricaoProd(cursor.getString(cursor.getColumnIndex("descricao")));
            v.setPrecoProd(new BigDecimal(cursor.getString(cursor.getColumnIndex("preco"))));

            Log.i("VENDA: ", v.getIdVenda() + " " + v.getDescricaoProd());
            lista.add(v);
        }
        cursor.close();
        return lista;
    }

    private Date converterParaDate(String dataVenda) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return sdf.parse(dataVenda);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


}
