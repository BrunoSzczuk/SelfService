package fag.com.br.selfservice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import fag.com.br.selfservice.Entity.Produto;
import fag.com.br.selfservice.R;

/**
 * Created by Bruno on 02/04/2018.
 */

public class AdapterProduto extends BaseAdapter {
    List<Produto> produtoList;
    LayoutInflater inflater;

    public AdapterProduto(Context context, List<Produto> produtoList) {
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
        Produto p = produtoList.get(position);
        view =  inflater.inflate(R.layout.item_produto, null);

        ((TextView) view.findViewById(R.id.tvCdProduto)).setText(String.valueOf(p.getCdProduto()));
        ((TextView) view.findViewById(R.id.tvDsProduto)).setText(p.getDsProduto().toString());
        ((TextView) view.findViewById(R.id.etVlProduto)).setText(NumberFormat.getCurrencyInstance().format(p.getVlProduto()));
        ((TextView) view.findViewById(R.id.etPsBruto)).setText(new DecimalFormat("##,#00").format(p.getPsBruto()));
        return view;
    }
}
