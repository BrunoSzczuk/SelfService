package fag.com.br.selfservice;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fag.com.br.selfservice.Entity.Adicional;
import fag.com.br.selfservice.Entity.Produto;
import fag.com.br.selfservice.adapter.AdapterProduto;
import fag.com.br.selfservice.util.Mensagem;

public class AdicionalActivity extends AppCompatActivity {

    EditText etCodigo, etDescricao;
    Button btSalvar;
    ArrayAdapter arrayAdapter;
    ListView lvAdicional;
    Adicional adicional = new Adicional();
    List<Adicional> adicionalList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicional);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etCodigo = findViewById(R.id.etCodigo);
        etDescricao = findViewById(R.id.etDescricao);
        btSalvar = findViewById(R.id.btSalvar);
        lvAdicional = findViewById(R.id.lvAdicional);

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adicional == null) adicional = new Adicional();
                adicional.setCdTipo(etCodigo.getText().toString());
                adicional.setDsTipo(etDescricao.getText().toString());
                adicional.save();
                Mensagem.ExibirMensagem(AdicionalActivity.this,"Salvo com sucesso",1);
                adicional = new Adicional();
                carregaLista();
            }
        });

        lvAdicional.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adicional =(Adicional)lvAdicional.getItemAtPosition(position);
                exibeAdicional(adicional);
            }
        });
        carregaLista();
    }

    private void exibeAdicional(Adicional adicional) {
        etDescricao.setText(adicional.getDsTipo());
        etCodigo.setText(adicional.getCdTipo());
    }
    private void carregaLista() {
        adicionalList = Adicional.listAll(Adicional.class);//Lista com Ordenacao
        arrayAdapter = new ArrayAdapter(this,R.layout.item_exibicao, adicionalList);
        lvAdicional.setAdapter(arrayAdapter);//Amarro a ListView com o Adapter criado
        calculaTamanhoAdapater();
    }

    private void calculaTamanhoAdapater(){
        int totalHeight =0;
        ListAdapter adapter = lvAdicional.getAdapter();
        int length = adapter.getCount();
        for (int i =0; i< length; i++){
            View listView = adapter.getView(i, null, lvAdicional);
            listView.measure(0,0);
            totalHeight+= listView.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = lvAdicional.getLayoutParams();
        params.height = totalHeight + (lvAdicional.getDividerHeight() * (adapter.getCount() -1) +10);
        lvAdicional.setLayoutParams(params);
        lvAdicional.requestLayout();
    }
}
