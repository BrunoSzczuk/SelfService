package fag.com.br.selfservice;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import fag.com.br.selfservice.Entity.Adicional;
import fag.com.br.selfservice.Entity.ItemPedido;
import fag.com.br.selfservice.Entity.Produto;
import fag.com.br.selfservice.Entity.ProdutoAdicional;

public class ListaItemActivity extends AppCompatActivity {

    ListView listaAdicionais;
    ArrayAdapter arrayAdapter;
    Button btAdicionar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listaAdicionais = findViewById(R.id.lvItens);
        btAdicionar = findViewById(R.id.btAdicionar);
        Produto produto = (Produto)getIntent().getSerializableExtra("produto");
        final ItemPedido itemPedido = (ItemPedido) getIntent().getSerializableExtra("itempedido");

        Produto p = Produto.find(Produto.class, " cd_produto = ? ", new String[]{produto.getCdProduto().toString()}).get(0);
        List<ProdutoAdicional> adicionais =
                ProdutoAdicional.find(ProdutoAdicional.class," produto = ? ", new String[]{p.getId().toString()});

        List<Adicional> adds = new ArrayList<>();
        for (ProdutoAdicional pa : adicionais){
            adds.add(pa.getAdicional());
        }
        arrayAdapter = new ArrayAdapter(this,R.layout.item_exibicao, adds);
        listaAdicionais.setAdapter(arrayAdapter);//Amarro a ListView com o Adapter criado

        btAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemPedido.setAdicional((Adicional)listaAdicionais.getSelectedItem());
                itemPedido.setQtProduto(itemPedido.getQtProduto() + 1);
                MainActivity.itensPedido.add(itemPedido);
                ListaItemActivity.super.onBackPressed();
            }
        });

    }
}
