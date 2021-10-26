/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gomi.back;

import br.com.gomi.shared.UsuarioViewModel;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

/**
 *
 * @author Administrador
 */
public class LoginDAO {

    public UsuarioViewModel login(String usuario, String senha) throws SQLException {
        String[] parametros = {usuario, senha};
        JDataTable tabela = HelperDAO.executaProcSelect("spLogin ?, ?", parametros);
        if (tabela == null) {
            return null;
        } else {
            return MontaModel(tabela.getLinha(1));
        }
    }

    //ViewModel identificadora do usuario, se ele é usuario ou se é administrador.
    protected UsuarioViewModel MontaModel(HashMap<String, Object> registro) {
        UsuarioViewModel t = new UsuarioViewModel();
        t.setId((Integer) registro.get("IdUsuario"));
        if (String.valueOf(registro.get("IdNaoAdm")).equals("")) {
            t.setIdNaoAdm(null);
        } else {
            t.setIdNaoAdm((Integer) registro.get("IdNaoAdm"));
        }
        if (String.valueOf(registro.get("IdAdministrador")).equals("")) {
            t.setIdAdministrador(null);
        } else {
            t.setIdAdministrador((Integer) registro.get("IdAdministrador"));
        }
        t.setEmail((String) registro.get("Email"));
        t.setSenha((String) registro.get("Senha"));
        t.setNome((String) registro.get("Nome"));
        t.setData(LocalDate.parse(String.valueOf(registro.get("DataNascimento"))));
        t.setCpf((String) registro.get("CPF"));
        return t;
    }
}
