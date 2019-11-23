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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mrc.appcadernofiado.R;
import com.mrc.appcadernofiado.adapter.AdapterVenda;
import com.mrc.appcadernofiado.model.Cliente;
import com.mrc.appcadernofiado.model.Produto;
import com.mrc.appcadernofiado.model.VendaVO;
import com.mrc.appcadernofiado.rn.ClienteRN;
import com.mrc.appcadernofiado.rn.ProdutoRN;
import com.mrc.appcadernofiado.rn.VendaRN;
import com.mrc.appcadernofiado.util.CodUtil;
import com.mrc.appcadernofiado.util.SMSUtil;

import java.util.ArrayList;
import java.util.List;

public class VendaFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private ListView lvVenda;
    private VendaRN rn;
    private ClienteRN clienteRN;
    private Spinner spnClientes, spnProdutos;
    private Button btnAddCarrinho, btnComprar;
    private List<VendaVO> listaCarrinhoCompra;
    private List<Cliente> clientes;
    private List<Produto> produtos;
    private Produto codProduto;
    private Cliente codCliente;
    private EditText qtdItens;
    private TextView txtNumNotaFiscal;
    private Integer nf;

    public VendaFragment() {
    }

    public static VendaFragment newInstance(String param1, String param2) {
        VendaFragment fragment = new VendaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_venda, container, false);
        nf = CodUtil.gerarNimeroFiscal();
        listaCarrinhoCompra = new ArrayList<>();
        lvVenda = view.findViewById(R.id.lvVenda);
        qtdItens = view.findViewById(R.id.qtdItens);
        spnProdutos = view.findViewById(R.id.spnProdutos);
        spnClientes = view.findViewById(R.id.spnClientes);
        btnAddCarrinho = view.findViewById(R.id.btnAddCarrinho);
        btnComprar = view.findViewById(R.id.btnComprar);
        txtNumNotaFiscal = view.findViewById(R.id.txtNumNotaFiscal);

        txtNumNotaFiscal.setText(nf.toString());

        spnClientes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                codCliente = clientes.get(position);
                Toast.makeText(getContext(), "ID cliente: " + codCliente.getId(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spnProdutos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                codProduto = produtos.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnAddCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String asd = qtdItens.getText().toString();
                Integer asdf = Integer.parseInt(asd);
                if (asd == null || asd.isEmpty() || asd.trim().equals("") || asdf == 0) {
                    Toast.makeText(getContext(), "Informe a quantidade.", Toast.LENGTH_SHORT).show();
                } else {
                    adicionarAoCarrinho(codCliente, codProduto, asdf);
                    Toast.makeText(getContext(), codCliente + " " + codProduto, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarVenda(codCliente, nf);
                Toast.makeText(getContext(), "Compra concluída.", Toast.LENGTH_SHORT).show();
            }
        });

        listarClientes();
        listarProdutos();
        return view;
    }


    public void adicionarAoCarrinho(Cliente codCliente, Produto codProduto, int qtd) {
        Produto p = obterProduto(codProduto.getId());
        VendaVO v = new VendaVO();
        v.setIdProduto(p.getId());
        v.setDescricaoProd(p.getDescricao());
        v.setPrecoProd(p.getPreco());
        v.setIdCliente(codCliente.getId());
        v.setQuantidade(qtd);
        listaCarrinhoCompra.add(v);

        AdapterVenda adapter = new AdapterVenda(getActivity(), listaCarrinhoCompra);
        lvVenda.setAdapter(adapter);

    }

    private void salvarVenda(Cliente codCliente, Integer nf) {
        rn = new VendaRN(getContext());
        rn.salvarVendaRN(codCliente, listaCarrinhoCompra, nf);
    }

    private Produto obterProduto(Long codProduto) {
        ProdutoRN rn = new ProdutoRN(getContext());
        return rn.obterProdutoRN(codProduto);
    }

    private void listarClientes() {
        clienteRN = new ClienteRN(getContext());
        clientes = clienteRN.listarTodosRN();
        ArrayAdapter<Cliente> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_expandable_list_item_1, clientes);
        spnClientes.setAdapter(adapter);
    }

    private void listarProdutos() {
        ProdutoRN produtosRN = new ProdutoRN(getContext());
        produtos = produtosRN.listarProdutosRN();
        ArrayAdapter<Produto> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_expandable_list_item_1, produtos);
        spnProdutos.setAdapter(adapter);
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
