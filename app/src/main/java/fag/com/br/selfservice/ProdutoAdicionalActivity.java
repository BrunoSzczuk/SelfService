package fag.com.br.selfservice;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import fag.com.br.selfservice.Entity.Adicional;
import fag.com.br.selfservice.Entity.Produto;
import fag.com.br.selfservice.Entity.ProdutoAdicional;
import fag.com.br.selfservice.adapter.AdapterProduto;
import fag.com.br.selfservice.adapter.AdapterProdutoAdicional;
import fag.com.br.selfservice.util.Mensagem;

public class ProdutoAdicionalActivity extends AppCompatActivity {

    Spinner spProduto, spAdicional;


    ToggleButton tgAtivo;
    Button btSalvar;

    AdapterProduto adapterProduto;
    List<Produto> produtoList = Produto.listAll(Produto.class);

    ArrayAdapter adapterAdicional;
    List<Adicional> adicionalList = Adicional.listAll(Adicional.class);

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
        tgAtivo = findViewById(R.id.tgAtivo);

        lvProdutoAdicional = findViewById(R.id.lvProdutoAdicional);
        btSalvar = findViewById(R.id.btSalvar);

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spAdicional.getSelectedItem() == null){
                    Mensagem.ExibirMensagem(ProdutoAdicionalActivity.this,"É necessário ter um adicional selecionado",1);
                }else if (spProduto.getSelectedItem() == null){
                    Mensagem.ExibirMensagem(ProdutoAdicionalActivity.this,"É necessário ter um produto selecionado",1);
                }else {
                    produtoAdicional.setAdicional((Adicional) spAdicional.getSelectedItem());
                    produtoAdicional.setProduto((Produto) spProduto.getSelectedItem());
                    produtoAdicional.setStDefault(tgAtivo.isChecked());
                    produtoAdicional.save();
                    produtoAdicional = new ProdutoAdicional();
                    Mensagem.ExibirMensagem(ProdutoAdicionalActivity.this,"Salvo com sucesso",1);
                    carregaLista();

                }
            }
        });
        lvProdutoAdicional.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                produtoAdicional = (ProdutoAdicional) lvProdutoAdicional.getSelectedItem();
                exibeProdutoAdicional(produtoAdicional );
            }
        });
        carregaLista();
    }

    private void exibeProdutoAdicional(ProdutoAdicional adicional) {
        spAdicional.setSelection(adapterAdicional.getPosition(spAdicional.getSelectedItem()));
        spProduto.setSelection(adapterProduto.getPosition((Produto)spProduto.getSelectedItem()));

    }
    private void carregaLista() {
        produtoAdicionalList = ProdutoAdicional.listAll(ProdutoAdicional.class);//Lista com Ordenacao
        adapter = new AdapterProdutoAdicional(this, produtoAdicionalList);
        lvProdutoAdicional.setAdapter(adapter);//Amarro a ListView com o Adapter criado

        adapterProduto = new AdapterProduto(this,produtoList);
        spProduto.setAdapter(adapterProduto);

        adapterAdicional = new ArrayAdapter(this, R.layout.item_exibicao, adicionalList);
        spAdicional.setAdapter(adapterAdicional);
    }
}
