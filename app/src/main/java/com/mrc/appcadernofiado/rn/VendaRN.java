package com.mrc.appcadernofiado.rn;

import android.content.Context;
import android.util.Log;

import com.mrc.appcadernofiado.dao.VendaDAO;
import com.mrc.appcadernofiado.model.Cliente;
import com.mrc.appcadernofiado.model.Produto;
import com.mrc.appcadernofiado.model.Venda;
import com.mrc.appcadernofiado.model.VendaVO;
import com.mrc.appcadernofiado.util.CodUtil;
import com.mrc.appcadernofiado.util.SMSUtil;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class VendaRN {
    private VendaDAO dao;
    private Context context;
    private ClienteRN clienteRN;

    public VendaRN(Context context) {
        this.context = context;
        dao = new VendaDAO(context);
    }

    public void salvarVendaRN(Cliente c, List<VendaVO> listaCarrinhoCompra, Integer nf) {
        montarMsg(c, listaCarrinhoCompra, nf);
        for (VendaVO v : listaCarrinhoCompra) {
            v.setNotaFiscal(nf);

            dao.salvarDAO(converterVoEmVenda(v));
        }


    }

    private void montarMsg(Cliente c, List<VendaVO> listaCarrinhoCompra, Integer nf) {
        clienteRN = new ClienteRN(context);
//        c = clienteRN.obterClienteRN(c);

        StringBuilder sb = new StringBuilder();
        sb.append("Sr " + c.getNome() + "\n\n");
        sb.append("Voce comprou os produtos abaixo:\n");
        for (VendaVO v : listaCarrinhoCompra) {
            sb.append("Item: " + v.getDescricaoProd() + " " + v.getPrecoProd() + " * " + v.getQuantidade() + " = " + v.getPrecoProd().multiply(new BigDecimal(v.getQuantidade())) + "\n");
        }
        BigDecimal asdd = calcularSomatorio(listaCarrinhoCompra);
        sb.append("\n\nO total da sua compra e: "+asdd.setScale(2).toString());
        SMSUtil.enviarSMS(c.getTelefone(), sb.toString(), context);
    }

    private BigDecimal calcularSomatorio(List<VendaVO> listaCarrinhoCompra) {
        BigDecimal total = BigDecimal.ZERO;
        for (VendaVO v : listaCarrinhoCompra) {
            total = total.add(v.getPrecoProd().multiply(new BigDecimal(v.getQuantidade())));
        }
        return total;
    }

    private Venda converterVoEmVenda(VendaVO vo) {
        Venda v = new Venda();
        v.setNotaFiscal(vo.getNotaFiscal());
        v.setDataVenda(new Date());
        v.setIdCliente(new Cliente(vo.getIdCliente()));
        v.setQuantidade(vo.getQuantidade());
        v.setIdProduto(new Produto(vo.getIdProduto()));
        return v;
    }


    public List<VendaVO> listarVendaRN(Cliente cli) {
        return dao.listarVendasDAO(cli);
    }
}
