package fag.com.br.selfservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ToggleButton;

import com.orm.SugarContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fag.com.br.selfservice.Entity.ItemPedido;
import fag.com.br.selfservice.Entity.PedidoVenda;
import fag.com.br.selfservice.Entity.Produto;
import fag.com.br.selfservice.adapter.AdapterListaItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView lvItemProduto;
    AdapterListaItem adapter;
    public static PedidoVenda pedidoVenda;
    public static List<ItemPedido> itens = new ArrayList<>();
    List<Produto> produtos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SugarContext.init(this);//Instancia o Sugar

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        pedidoVenda = new PedidoVenda();
        PedidoVenda outropedido;
        try{
            outropedido = PedidoVenda.last(PedidoVenda.class);
        }catch(android.database.sqlite.SQLiteException s){
            outropedido = new PedidoVenda();
            outropedido.setNrPedido(0);
        }

        if (outropedido == null){
            outropedido = new PedidoVenda();
            outropedido.setNrPedido(0);
        }
        pedidoVenda.setDtEmissao(new Date());
        pedidoVenda.setNrPedido(outropedido.getNrPedido() +1);
        pedidoVenda.setStCancelado(false);
        pedidoVenda.setVlPedido(0);
        pedidoVenda.setPsPedido(0);
        pedidoVenda.setItens(itens);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        carregaLista();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_produto) {
            Intent intent = new Intent(this, ProdutoActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_adicional) {
            Intent intent = new Intent(this, AdicionalActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_produto_adicional) {
            Intent intent = new Intent(this, ProdutoAdicionalActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void carregaLista() {
        produtos = Produto.listAll(Produto.class);//Lista com Ordenacao
        adapter = new AdapterListaItem(this,produtos);
        lvItemProduto.setAdapter(adapter);//Amarro a ListView com o Adapter criado
    }

}
