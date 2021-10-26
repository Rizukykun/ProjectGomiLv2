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
public class NaoAdmDAO extends PadraoDAO<NaoAdmViewModel> {

    @Override
    //Cria os parametros
    protected String[] CriaParametros(NaoAdmViewModel model) {
        String[] parametros = new String[5];
        parametros[0] = String.valueOf(model.getIdNaoAdm());
        parametros[1] = String.valueOf(model.getIdCliente());
        parametros[2] = String.valueOf(model.getIdMotorista());
        parametros[3] = String.valueOf(model.getTelefoneddd());
        parametros[4] = String.valueOf(model.getTelefone());

        return parametros;
    }

    @Override
    //Cria as models com os campos
    protected NaoAdmViewModel MontaModel(HashMap<String, Object> registro) {
        NaoAdmViewModel t = new NaoAdmViewModel();
        t.setIdNaoAdm((Integer) registro.get("IdNaoAdm"));
        if (String.valueOf(registro.get("IdCliente")).equals("")) {
            t.setIdCliente(null);
        } else {
            t.setIdCliente(Integer.parseInt(String.valueOf(registro.get("IdCliente"))));
        }
        if (String.valueOf(registro.get("IdMotorista")).equals("")) {
            t.setIdMotorista(null);
        } else {
            t.setIdMotorista(Integer.parseInt(String.valueOf(registro.get("IdMotorista"))));
        }
        t.setTelefoneddd((Integer) registro.get("TelefoneDDD"));
        t.setTelefone((Integer) registro.get("Telefone"));
        return t;
    }

    @Override
    //Tabela utilizada no SQL
    protected void setTabela() {
        tabela = "NaoAdm";
    }

    @Override
    //Quantidade de parametros em NaoAdm
    protected void setQtdParametros() {
        qtdParametros = " ?, ?, ?, ?, ?";
    }
}
