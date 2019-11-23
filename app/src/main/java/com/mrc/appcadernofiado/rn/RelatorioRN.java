package com.mrc.appcadernofiado.rn;

import android.content.Context;

import com.mrc.appcadernofiado.dao.RelatorioDAO;
import com.mrc.appcadernofiado.model.Cliente;
import com.mrc.appcadernofiado.model.VendaVO;

import java.util.List;

public class RelatorioRN {
    private RelatorioDAO dao;
    private Context context;

    public RelatorioRN(Context context) {
        this.context = context;
        dao = new RelatorioDAO(context);
    }

    public List<VendaVO> listarRelatorioRN(Cliente cli) {
        return dao.listarRelatorioDAO(cli);
    }
}
