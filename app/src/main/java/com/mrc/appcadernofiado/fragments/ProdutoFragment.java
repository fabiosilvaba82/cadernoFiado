package com.mrc.appcadernofiado.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.mrc.appcadernofiado.adapter.AdapterProduto;
import com.mrc.appcadernofiado.model.Produto;
import com.mrc.appcadernofiado.rn.ProdutoRN;

import java.math.BigDecimal;
import java.util.List;


public class ProdutoFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private EditText edtDescricao, edtPreco;
    private Button btnSalvar;
    private ListView lvProduto;
    private Produto produto;
    private ProdutoRN rn;

    public ProdutoFragment() {
    }

    public static ProdutoFragment newInstance(String param1, String param2) {
        ProdutoFragment fragment = new ProdutoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_produto, container, false);

        edtDescricao = view.findViewById(R.id.edtDescricaoProduto);
        edtPreco = view.findViewById(R.id.edtPrecoProduto);
        lvProduto = view.findViewById(R.id.listViewProdutos);

        btnSalvar = view.findViewById(R.id.btnSalvarProduto);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper();
            }
        });
        registerForContextMenu(lvProduto);
        listarProdutos();
        return view;
    }

    private void helper() {
        Produto p = new Produto();
        p.setDescricao(edtDescricao.getText().toString());
        p.setPreco(new BigDecimal(edtPreco.getText().toString()));

        salvarProduto(p);
    }

    public void salvarProduto(Produto p) {
        rn = new ProdutoRN(getContext());
        rn.salvarProdutoRN(p);
        listarProdutos();
    }

    public void listarProdutos() {
        rn = new ProdutoRN(getContext());
        List<Produto> li = rn.listarProdutosRN();
        AdapterProduto adapter = new AdapterProduto(getActivity(), li);
        lvProduto.setAdapter(adapter);
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Produto produtoSelecionado = (Produto) lvProduto.getAdapter().getItem(info.position);
        menu.setHeaderTitle("Produto: " + produtoSelecionado.getDescricao());
        MenuItem itemAtualizar = menu.add("Atualizar Produto");
        MenuItem itemRemover = menu.add("Remover Produto ");
        itemRemover.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                new AlertDialog.Builder(getContext()).setIcon(R.drawable.ic_menu_camera).setTitle("Apagar produto?")
                        .setMessage("Deseja realente apagar esse produto?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                removerProduto(produtoSelecionado);
                            }
                        })
                        .setNegativeButton("Não", null)
                        .show();
                return false;
            }
        });
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    private void removerProduto(Produto produtoSelecionado) {
        rn = new ProdutoRN(getContext());
        String msg = rn.removerProdutoRN(produtoSelecionado);
        mostrarToast(msg);
    }

    private void mostrarToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
