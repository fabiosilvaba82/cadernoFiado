package com.mrc.appcadernofiado.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mrc.appcadernofiado.R;
import com.mrc.appcadernofiado.model.Cliente;

import java.util.List;

public class AdapterCliente extends BaseAdapter {

    private final Activity act;
    private final List<Cliente> clientes;

    public AdapterCliente(Activity activity, List<Cliente> lista) {
        this.act = activity;
        this.clientes = lista;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Cliente c = clientes.get(position);
        view = this.act.getLayoutInflater().inflate(R.layout.celula_cliente, parent, false);

        TextView celNome = view.findViewById(R.id.celNome);
        celNome.setText(c.getNome());

        TextView celTelefone = view.findViewById(R.id.celTelefone);
        celTelefone.setText(c.getTelefone());

        if (position % 2 == 0) {
            view.setBackgroundColor(act.getResources().getColor(R.color.colorCinza));
        }
        return view;
    }

    @Override
    public int getCount() {
        return clientes.size();
    }

    @Override
    public Object getItem(int position) {
        return this.clientes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.clientes.get(position).getId();
    }

}
