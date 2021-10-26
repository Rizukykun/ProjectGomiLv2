/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gomi.back;

import java.util.HashMap;
import br.com.gomi.shared.*;

/**
 *
 * @author Administrador
 */
public class EcopontoDAO extends PadraoDAO<EcopontoViewModel> {

    @Override
    //Cria os parametros
    protected String[] CriaParametros(EcopontoViewModel model) {
        String[] parametros = new String[3];
        parametros[0] = String.valueOf(model.getId());
        parametros[1] = model.getCep();
        parametros[2] = String.valueOf(model.getNumero());
        return parametros;
    }

    @Override
    //Cria as models com os campos
    protected EcopontoViewModel MontaModel(HashMap<String, Object> registro) {
        EcopontoViewModel t = new EcopontoViewModel();
        t.setId((Integer) registro.get("IdEcoponto"));
        t.setCep((String) registro.get("cep"));
        t.setNumero((Integer) registro.get("numero"));
        return t;
    }

    @Override
    //Tabela utilizada no SQL
    protected void setTabela() {
        tabela = "Ecoponto";
    }

    @Override
    //Parametros em Ecoponto
    protected void setQtdParametros() {
        qtdParametros = " ?, ?, ?";
    }
}
