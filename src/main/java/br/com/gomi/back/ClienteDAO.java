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
public class ClienteDAO extends PadraoDAO<ClienteViewModel> {

    @Override
    //Cria os parametros
    protected String[] CriaParametros(ClienteViewModel model) {
        String[] parametros = new String[7];
        parametros[0] = String.valueOf(model.getIdCliente());
        parametros[1] = model.getRua();
        parametros[2] = String.valueOf(model.getNumero());
        parametros[3] = model.getComplemento();
        parametros[4] = model.getBairro();
        parametros[5] = model.getCidade();
        parametros[6] = model.getCep();
        return parametros;
    }

    @Override
    //Cria as models com os campos
    protected ClienteViewModel MontaModel(HashMap<String, Object> registro) {
        ClienteViewModel t = new ClienteViewModel();
        t.setIdCliente((Integer) registro.get("IdCliente"));
        t.setRua((String) registro.get("Rua"));
        t.setNumero((Integer) registro.get("Numero"));
        t.setComplemento((String) registro.get("Complemento"));
        t.setBairro((String) registro.get("Bairro"));
        t.setCidade((String) registro.get("Cidade"));
        t.setCep((String) registro.get("CEP"));
        return t;
    }

    @Override
    //Tabela utilizada no SQL
    protected void setTabela() {
        tabela = "Cliente";
    }

    @Override
    //Parametros em Cliente
    protected void setQtdParametros() {
        qtdParametros = " ?, ?, ?, ?, ?, ?, ?";
    }
}
