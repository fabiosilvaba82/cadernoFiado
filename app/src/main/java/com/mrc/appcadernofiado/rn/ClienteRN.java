package com.mrc.appcadernofiado.rn;

import android.content.Context;
import android.widget.Toast;

import com.mrc.appcadernofiado.dao.ClienteDAO;
import com.mrc.appcadernofiado.dao.ConnectionFactory;
import com.mrc.appcadernofiado.model.Cliente;
import com.mrc.appcadernofiado.util.SMSUtil;

import java.util.Date;
import java.util.List;

public class ClienteRN {
    private ClienteDAO dao;
    private Context context;

    public ClienteRN(Context context) {
        this.context = context;
        dao = new ClienteDAO(context);
    }


    public long salvarClienteRN(Cliente cliente) {
        if (validarDados(cliente)) {
            long resultado = dao.salvarClienteDAO(cliente);
            if (resultado != 0L) {
                montarSMS(cliente);
                Toast.makeText(context, " O cliente " + cliente.getNome() + " foi salvo com sucesso!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "O cliente NÃO pôde ser salvo!", Toast.LENGTH_LONG).show();
            }
            return resultado;
        }
        return 0L;
    }

    private boolean validarDados(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            Toast.makeText(context, "Informe o NOME do cliente!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (cliente.getTelefone() == null || cliente.getTelefone().isEmpty()) {
            Toast.makeText(context, "Informe o TELEFONE do cliente!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public List<Cliente> listarTodosRN() {
        return dao.listarTodosDAO();
    }

    private void montarSMS(Cliente cliente) {
        StringBuilder sb = new StringBuilder();
        sb.append("Ola, " + cliente.getNome() + ".\n");
        sb.append("Seu contato foi salvo na base de dados do Caderno Fiado.\n\n");
        sb.append("Att, \n");
        sb.append("Caderno Fiado");
        if (!cliente.getTelefone().isEmpty()) {
            SMSUtil.enviarSMS(cliente.getTelefone(), sb.toString(), context);
        }
    }

    public boolean removerClienteRN(Cliente clienteSelecionado) {
        boolean retorno = dao.removerClienteDAO(clienteSelecionado);
        if (retorno) {
            Toast.makeText(context, "Cliente " + clienteSelecionado.getNome() + " foi removido", Toast.LENGTH_SHORT).show();
            return retorno;
        }
        Toast.makeText(context, "Cliente " + clienteSelecionado.getNome() + " NÃO pode ser removido.", Toast.LENGTH_SHORT).show();
        return retorno;
    }

    public Cliente obterClienteRN(Cliente cliente) {
        Cliente clientePesquisado = dao.obterClientePorID(cliente);
        return clientePesquisado;
    }
}
