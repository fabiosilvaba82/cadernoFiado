package com.mrc.appcadernofiado.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mrc.appcadernofiado.R;
import com.mrc.appcadernofiado.model.VendaVO;

import java.math.BigDecimal;
import java.util.List;

public class AdapterRelatorio extends BaseAdapter {
    private final Activity context;
    private final List<VendaVO> lista;

    public AdapterRelatorio(Activity context, List<VendaVO> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = context.getLayoutInflater().inflate(R.layout.calula_relatorio, parent, false);
        TextView celVendaNF = view.findViewById(R.id.celVendaNF);
        celVendaNF.setText(String.valueOf(lista.get(position).getNotaFiscal()));
        TextView celVendaQuantidade = view.findViewById(R.id.celVendaQuantidade);
        celVendaQuantidade.setText(String.valueOf(lista.get(position).getQuantidade()));
        TextView celVendaDescricao = view.findViewById(R.id.celVendaDescricao);
        celVendaDescricao.setText(lista.get(position).getDescricaoProd());
        TextView celVendaPreco = view.findViewById(R.id.celVendaPreco);
        celVendaPreco.setText(String.valueOf(lista.get(position).getPrecoProd()));
        TextView celVendaTotal = view.findViewById(R.id.celVendaTotal);
        BigDecimal preco = lista.get(position).getPrecoProd();
        Integer qtd = lista.get(position).getQuantidade();
        celVendaTotal.setText(String.valueOf(calcularTotal(preco, qtd)));
        return view;
    }

    private BigDecimal calcularTotal(BigDecimal preco, Integer qtd) {
        return preco.multiply(new BigDecimal(qtd));
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
        return lista.get(position).getIdVenda();
    }


}
