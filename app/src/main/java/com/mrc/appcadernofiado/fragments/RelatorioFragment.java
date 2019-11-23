package com.mrc.appcadernofiado.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.mrc.appcadernofiado.R;
import com.mrc.appcadernofiado.adapter.AdapterRelatorio;
import com.mrc.appcadernofiado.model.Cliente;
import com.mrc.appcadernofiado.model.VendaVO;
import com.mrc.appcadernofiado.rn.ClienteRN;
import com.mrc.appcadernofiado.rn.RelatorioRN;

import java.util.ArrayList;
import java.util.List;

public class RelatorioFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private ListView lvRelatorio;
    private Spinner spnRelClientes;
    private RelatorioRN relatorioRN;
    private ClienteRN clienteRN;
    private List<Cliente> clientes;

    public RelatorioFragment() {
    }

    public static RelatorioFragment newInstance(String param1, String param2) {
        RelatorioFragment fragment = new RelatorioFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_relatorio, container, false);
        lvRelatorio = view.findViewById(R.id.lvRelatorio);
        spnRelClientes = view.findViewById(R.id.spnRelClientes);
        clientes = new ArrayList<>();

        listarClientes();
        spnRelClientes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                clienteRN = new ClienteRN(getContext());
                listarRelatorio(clientes.get(position).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return view;
    }

    private List<Cliente> listarClientes() {
        clienteRN = new ClienteRN(getContext());
        clientes = clienteRN.listarTodosRN();
        ArrayAdapter<Cliente> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_expandable_list_item_1, clientes);
        spnRelClientes.setAdapter(adapter);
    return clientes;
    }

    public void listarRelatorio(Long clienteID) {
        relatorioRN = new RelatorioRN(getContext());
        Cliente cli = new Cliente();
        cli.setId(clienteID);
        List<VendaVO> lista = relatorioRN.listarRelatorioRN(cli);
        AdapterRelatorio adapter = new AdapterRelatorio(getActivity(), lista);
        lvRelatorio.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
