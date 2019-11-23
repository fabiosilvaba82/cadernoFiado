package com.mrc.appcadernofiado.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mrc.appcadernofiado.R;
import com.mrc.appcadernofiado.model.Produto;

import java.util.List;

public class AdapterProduto extends BaseAdapter {
    private Activity activity;
    private List<Produto> lista;

    public AdapterProduto(Activity activity, List<Produto> lista) {
        this.activity = activity;
        this.lista = lista;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = this.activity.getLayoutInflater().inflate(R.layout.celula_produto,parent, false);
        TextView descricao = view.findViewById(R.id.celDescricao);
        descricao.setText(lista.get(position).getDescricao());
        TextView preco = view.findViewById(R.id.celPreco);
        preco.setText(String.valueOf(lista.get(position).getPreco()));
        return view;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lista.get(position).getId();
    }

}
