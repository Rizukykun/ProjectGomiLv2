/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gomi.back;

import java.util.HashMap;
import br.com.gomi.shared.*;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author Administrador
 */
public class UsuarioDAO extends PadraoDAO<UsuarioViewModel>
{
      
    //Parametros do usuario
    @Override
    protected String[] CriaParametros(UsuarioViewModel model)
    {           
        String[] parametros = new String[8];
        parametros[0] = String.valueOf(model.getId());
        parametros[1] = String.valueOf(model.getIdNaoAdm());
        parametros[2] = String.valueOf(model.getIdAdministrador());        
        parametros[3] = model.getEmail();
        parametros[4] = model.getSenha();
        parametros[5] = model.getNome();
        parametros[6] = String.valueOf(model.getData());
        parametros[7] = model.getCpf();
        return parametros;
    }

    @Override
    //Model com os parametros do usuario
    protected UsuarioViewModel MontaModel(HashMap<String, Object> registro)
    {
        UsuarioViewModel t = new UsuarioViewModel();
        t.setId((Integer)registro.get("IdUsuario"));
        if(String.valueOf(registro.get("IdNaoAdm")).equals(""))
            t.setIdNaoAdm(null);
        else
            t.setIdNaoAdm((Integer)registro.get("IdNaoAdm"));
        if(String.valueOf(registro.get("IdAdministrador")).equals(""))
            t.setIdAdministrador(null);
        else
            t.setIdAdministrador((Integer)registro.get("IdAdministrador"));
        t.setEmail((String)registro.get("Email"));
        t.setSenha((String)registro.get("Senha"));
        t.setNome((String)registro.get("Nome"));
        t.setData(LocalDate.parse(String.valueOf(registro.get("DataNascimento"))));
        t.setCpf((String)registro.get("CPF"));
        return t;
    }

    // Tabela utilizada no SQL
    @Override
    protected void setTabela()
    {
       tabela = "Usuario";
    }    
    
    //Quantidade de parametros em Usuario
    @Override
    protected void setQtdParametros(){
        qtdParametros = " ?, ?, ?, ?, ?, ?, ?, ?";
    }
    
    public UsuarioViewModel consultaEmail(String email) throws SQLException{
        String[] parametros = {email};
        JDataTable tab = HelperDAO.executaProcSelect("spUsuario ?", parametros);
        if (tab == null)
            return null;
        else
            return MontaModel(tab.getLinha(1));
    }
}
