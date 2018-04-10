package fag.com.br.selfservice;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import fag.com.br.selfservice.Entity.Adicional;
import fag.com.br.selfservice.Entity.Produto;
import fag.com.br.selfservice.Entity.ProdutoAdicional;
import fag.com.br.selfservice.adapter.AdapterProdutoAdicional;

public class ProdutoAdicionalActivity extends AppCompatActivity {

    Spinner spProduto, spAdicional;


    ToggleButton tgAtivo;
    Button btSalvar;

    ListView lvProdutoAdicional;
    ProdutoAdicional produtoAdicional = new ProdutoAdicional();
    List<ProdutoAdicional> produtoAdicionalList = new ArrayList<>();
    AdapterProdutoAdicional adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_adicional);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        spProduto = findViewById(R.id.spProduto);
        spAdicional = findViewById(R.id.spAdicional);
        lvProdutoAdicional = findViewById(R.id.lvProdutoAdicional);
        btSalvar = findViewById(R.id.btSalvar);

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                produtoAdicional.setAdicional((Adicional) spAdicional.getSelectedItem());
                produtoAdicional.setProduto((Produto) spProduto.getSelectedItem());
                produtoAdicional.save();
                produtoAdicional = new ProdutoAdicional();
            }
        });



    }

    private void exibeProdutoAdicional(ProdutoAdicional adicional) {

     //   etCodigo.setText(adicional.getCdTipo());
    }
    private void carregaLista() {
        produtoAdicionalList = ProdutoAdicional.listAll(ProdutoAdicional.class);//Lista com Ordenacao
        adapter = new AdapterProdutoAdicional(this, produtoAdicionalList);
        lvProdutoAdicional.setAdapter(adapter);//Amarro a ListView com o Adapter criado

    }
}
