package fag.com.br.selfservice.Entity;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;

/**
 * Created by Bruno on 04/04/2018.
 */

public class Adicional extends SugarRecord implements Serializable {

    @Unique
    private String cdTipo;

    private String dsTipo;

    public Adicional(String cdTipo, String dsTipo) {
        this.cdTipo = cdTipo;
        this.dsTipo = dsTipo;
    }


    public Adicional() {
    }

    @Override
    public String toString() {
        return "Codigo - " + cdTipo + " Descrição - " + dsTipo;
    }

    public String getCdTipo() {
        return cdTipo;
    }

    public void setCdTipo(String cdTipo) {
        this.cdTipo = cdTipo;
    }

    public String getDsTipo() {
        return dsTipo;
    }

    public void setDsTipo(String dsTipo) {
        this.dsTipo = dsTipo;
    }
}
