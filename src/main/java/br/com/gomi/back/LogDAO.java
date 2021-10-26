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
public class LogDAO extends PadraoDAO<LogViewModel> {

    @Override
    //Cria os parametros
    protected String[] CriaParametros(LogViewModel model) {
        String[] parametros = new String[2];
        parametros[0] = String.valueOf(model.getId());
        parametros[1] = model.getDescricao();
        return parametros;
    }

    @Override
    //Cria as models com os campos
    protected LogViewModel MontaModel(HashMap<String, Object> registro) {
        LogViewModel t = new LogViewModel();
        t.setId((Integer) registro.get("id"));
        t.setDescricao((String) registro.get("nome"));
        return t;
    }

    @Override
    //Tabela utilizada no SQL
    protected void setTabela() {
        tabela = "Log";
    }

    @Override
    //Quantidade de parametros em Log
    protected void setQtdParametros() {
        qtdParametros = " ?, ?";
    }
}
