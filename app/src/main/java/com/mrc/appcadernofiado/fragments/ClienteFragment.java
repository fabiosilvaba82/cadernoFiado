package com.mrc.appcadernofiado.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.mrc.appcadernofiado.R;
import com.mrc.appcadernofiado.adapter.AdapterCliente;
import com.mrc.appcadernofiado.dao.ConnectionFactory;
import com.mrc.appcadernofiado.model.Cliente;
import com.mrc.appcadernofiado.permissoes.Permissao;
import com.mrc.appcadernofiado.rn.ClienteRN;
import com.mrc.appcadernofiado.util.StringUtil;

import java.util.ArrayList;
import java.util.List;


public class ClienteFragment extends Fragment  {
    private OnFragmentInteractionListener mListener;
    private Cliente cliente;
    private ClienteRN clienteRN;
    private EditText nomeCliente, telefoneCliente;
    private ListView listView;
    private Button btnSalvarCliente;
    private ConnectionFactory factory;

    public ClienteFragment() {
        cliente = new Cliente();
    }

    public static ClienteFragment newInstance() {
        ClienteFragment fragment = new ClienteFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cliente, container, false);
        nomeCliente = view.findViewById(R.id.nomeCliente);
        telefoneCliente = view.findViewById(R.id.telefoneCliente);
        listView = view.findViewById(R.id.listViewCliente);
        btnSalvarCliente = view.findViewById(R.id.btnSalvarCliente);
        btnSalvarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarCliente();
            }
        });
        registerForContextMenu(listView);
        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Cliente clienteSelecionado = (Cliente) listView.getAdapter().getItem(info.position);
        menu.setHeaderTitle("Cliente: " + clienteSelecionado.getNome());
//        final MenuItem itemLigar = menu.add("Ligar para contato");
        MenuItem itemRemover = menu.add("Remover contato");
        MenuItem itemSMS = menu.add("Enviar SMS para contato");
        MenuItem itemWhatsApp = menu.add("Enviar WhatsApp para contato");
        itemWhatsApp.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent sendIntent = new Intent("android.intent.action.MAIN");
                sendIntent.putExtra("jid", "+5571988417444@s.whatsapp.net");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Instale meu novo aplicativo " + clienteSelecionado.getNome() + "!");
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setPackage("com.whatsapp");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                return false;
            }
        });

        itemRemover.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                new AlertDialog.Builder(getContext()).setIcon(R.drawable.ic_menu_camera).setTitle("Apagar contato?")
                        .setMessage("Deseja realente apagar esse contato?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                removerCliente(clienteSelecionado);
                            }
                        }).setNegativeButton("Não", null).show();
                return false;
            }
        });

        itemSMS.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (Permissao.checkAndRequestPermissions(getContext(), getActivity())) {
                    SmsManager sms = SmsManager.getDefault();
                    StringBuilder sb = new StringBuilder();
                    sb.append("Ola, " + clienteSelecionado.getNome() + ".\n");
                    sb.append("Essa e uma mensagem automatica de teste de aplicacao.\n\n");
                    sb.append("Favor desconsiderar.\n\n");
                    sb.append("Caderno Fiado.");
                    String sbb = StringUtil.unaccent(sb.toString());
                    Log.i("TESTE", sbb);
                   sms.sendTextMessage(clienteSelecionado.getTelefone(), null, sb.toString(), null, null);
                    Toast.makeText(getContext(), "Foi enviado um sms para " + clienteSelecionado.getNome(), Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });
        super.onCreateContextMenu(menu, v, menuInfo);
    }


    private void salvarCliente() {
        if (Permissao.checkAndRequestPermissions(getContext(), getActivity())) {
            clienteRN = new ClienteRN(getContext());
            cliente = new Cliente();
            cliente.setId(null);
            cliente.setNome(nomeCliente.getText().toString());
            cliente.setTelefone(telefoneCliente.getText().toString());

            if (clienteRN.salvarClienteRN(cliente) != 0L) {
                listarContatos();
                limparCampos();
            }
        }
    }

    private void listarContatos() {
        clienteRN = new ClienteRN(getContext());
        List<Cliente> clientes = clienteRN.listarTodosRN();

        AdapterCliente clienteAdapter = new AdapterCliente(getActivity(), clientes);
        listView.setAdapter(clienteAdapter);
    }

    private void removerCliente(Cliente clienteSelecionado) {
        clienteRN = new ClienteRN(getContext());
        clienteRN.removerClienteRN(clienteSelecionado);
        listarContatos();
    }

    public void limparCampos() {
        cliente.setNome(null);
        cliente.setTelefone(null);
        nomeCliente.setText("");
        telefoneCliente.setText("");
    }

    @Override
    public void onResume() {
        super.onResume();
        listarContatos();
    }


    private void obterClientePorId(Cliente cliente) {
        Cliente clientePesquisado = clienteRN.obterClienteRN(cliente);

        nomeCliente.setText(clientePesquisado.getNome());
        telefoneCliente.setText(clientePesquisado.getTelefone());
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
        void onFragmentInteraction(Uri uri);
    }
}
