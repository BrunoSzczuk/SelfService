package fag.com.br.selfservice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import fag.com.br.selfservice.Entity.ItemPedido;
import fag.com.br.selfservice.Entity.Produto;
import fag.com.br.selfservice.MainActivity;
import fag.com.br.selfservice.R;

/**
 * Created by Bruno on 02/04/2018.
 */

public class AdapterListaItem extends BaseAdapter {
    List<Produto> produtoList;
    LayoutInflater inflater;

    public AdapterListaItem(Context context, List<Produto> produtoList) {
        this.produtoList = produtoList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return produtoList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final Produto p = produtoList.get(position);
        view =  inflater.inflate(R.layout.item_listaitem, null);

        ((TextView) view.findViewById(R.id.tvCdProduto)).setText(String.valueOf(p.getCdProduto()));
        ((TextView) view.findViewById(R.id.tvDsProduto)).setText(p.getDsProduto().toString());
        ((TextView) view.findViewById(R.id.etVlProduto)).setText(NumberFormat.getCurrencyInstance().format(p.getVlProduto()));
        ((TextView) view.findViewById(R.id.etPsBruto)).setText(new DecimalFormat("##,#00").format(p.getPsBruto()));
        ((Button) view.findViewById(R.id.btAdicionar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TO DO - Criar a tela na frente pra selecionar os adicionais na tela
                ItemPedido itemPedido = new ItemPedido();
                // itemPedido.setAdicional(); pega o que ele selecionou
                itemPedido.setPedidoVenda(MainActivity.pedidoVenda);
                itemPedido.setQtProduto(1);
                itemPedido.setVlProduto(p.getVlProduto());

            }
        });
        return view;
    }
}