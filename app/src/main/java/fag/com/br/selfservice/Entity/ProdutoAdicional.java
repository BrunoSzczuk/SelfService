package fag.com.br.selfservice.Entity;

import com.orm.SugarRecord;
import com.orm.dsl.MultiUnique;
import com.orm.dsl.Table;

import java.io.Serializable;

/**
 * Created by Bruno on 09/04/2018.
 */

@MultiUnique("produto, adicional")
@Table(name = "produtoadicional")
public class ProdutoAdicional extends SugarRecord implements Serializable {
    private Produto produto;
    private Adicional adicional;
    private boolean stDefault;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Adicional getAdicional() {
        return adicional;
    }

    public void setAdicional(Adicional adicional) {
        this.adicional = adicional;
    }

    public boolean isStDefault() {
        return stDefault;
    }

    public void setStDefault(boolean stDefault) {
        this.stDefault = stDefault;
    }

    @Override
    public String toString() {
        return "ProdutoAdicional{" +
                "produto=" + produto +
                ", adicional=" + adicional +
                ", stDefault=" + stDefault +
                '}';
    }
}
