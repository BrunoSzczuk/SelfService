package fag.com.br.selfservice.Entity;

import com.orm.SugarRecord;
import com.orm.dsl.Table;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Bruno on 09/04/2018.
 */

@Table(name = "pedidovenda")
public class PedidoVenda extends SugarRecord implements Serializable{
    @Unique
    private int nrPedido;
    private Date dtEmissao;
    private double vlPedido;
    private double psPedido;
    private boolean stCancelado;
    private List<ItemPedido> itens;

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "PedidoVenda{" +
                "nrPedido=" + nrPedido +
                ", dtEmissao=" + dtEmissao +
                ", vlPedido=" + vlPedido +
                ", psPedido=" + psPedido +
                ", stCancelado=" + stCancelado +
                '}';
    }

    public int getNrPedido() {
        return nrPedido;
    }

    public void setNrPedido(int nrPedido) {
        this.nrPedido = nrPedido;
    }

    public Date getDtEmissao() {
        return dtEmissao;
    }

    public void setDtEmissao(Date dtEmissao) {
        this.dtEmissao = dtEmissao;
    }

    public double getVlPedido() {
        return vlPedido;
    }

    public void setVlPedido(double vlPedido) {
        this.vlPedido = vlPedido;
    }

    public double getPsPedido() {
        return psPedido;
    }

    public void setPsPedido(double psPedido) {
        this.psPedido = psPedido;
    }

    public boolean isStCancelado() {
        return stCancelado;
    }

    public void setStCancelado(boolean stCancelado) {
        this.stCancelado = stCancelado;
    }
}
