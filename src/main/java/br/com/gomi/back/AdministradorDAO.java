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
public class AdministradorDAO extends PadraoDAO<AdministradorViewModel> {

    @Override
    //Cria os parametros
    protected String[] CriaParametros(AdministradorViewModel model) {
        String[] parametros = new String[1];
        parametros[0] = String.valueOf(model.getId());
        return parametros;
    }

    @Override
    //Cria a ViewModel
    protected AdministradorViewModel MontaModel(HashMap<String, Object> registro) {
        AdministradorViewModel t = new AdministradorViewModel();
        t.setIdAdministrador((Integer) registro.get("IdAdministrador"));
        return t;
    }

    @Override
    //Tabela utilizada no SQL
    protected void setTabela() {
        tabela = "Administrador";
    }

    @Override
    //Quantidade de parametros utilizados
    protected void setQtdParametros() {
        qtdParametros = " ?";
    }
}
