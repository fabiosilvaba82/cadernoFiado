package com.mrc.appcadernofiado.rn;

import android.content.Context;
import android.widget.Toast;

import com.mrc.appcadernofiado.dao.ProdutoDAO;
import com.mrc.appcadernofiado.model.Produto;

import java.util.List;

public class ProdutoRN {
    private ProdutoDAO dao;
    private final Context context;

    public ProdutoRN(Context conext) {
        this.context = conext;
        this.dao = new ProdutoDAO(context);
    }

    public void salvarProdutoRN(Produto p) {
        dao.salvarProdutoDAO(p);
    }

    public List<Produto> listarProdutosRN() {
        return dao.listarProdutosDAO();
    }

    public Produto obterProdutoRN(Long codProduto) {
        return dao.obterProdutoDAO(codProduto);
    }

    public String removerProdutoRN(Produto produtoSelecionado) {
        boolean retorno = dao.excluirProdutoDAO(produtoSelecionado);
        String msg = "";
        if (retorno) {
            msg = "O Produto foi removido com sucesso.";
        } else {
            msg = "Não foi possível remover o Produto.";
        }
        return msg;
    }
}
