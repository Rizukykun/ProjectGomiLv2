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
public class MotoristaDAO extends PadraoDAO<MotoristaViewModel> {

    @Override
    //Cria os parametros
    protected String[] CriaParametros(MotoristaViewModel model) {
        String[] parametros = new String[6];
        parametros[0] = String.valueOf(model.getIdMotorista());
        parametros[1] = String.valueOf(model.getTipoVeiculo());
        parametros[2] = model.getCnh();
        parametros[3] = String.valueOf(model.getDataExpiracao());
        parametros[4] = String.valueOf(model.getCnhCategoria());
        parametros[5] = String.valueOf(model.getCargaSuportada());
        return parametros;
    }

    @Override
    //Cria as models com os campos
    protected MotoristaViewModel MontaModel(HashMap<String, Object> registro) {
        MotoristaViewModel t = new MotoristaViewModel();
        t.setIdMotorista((Integer) registro.get("IdMotorista"));
        t.setTipoVeiculo((String) registro.get("TipoVeiculo"));
        t.setCnh((String) registro.get("CNH"));
        t.setDataExpiracao(LocalDate.parse(String.valueOf(registro.get("DataExpiracao"))));
        t.setCnhCategoria((Character) String.valueOf(registro.get("CategoriaCNH")).charAt(0));
        t.setCargaSuportada((Integer) registro.get("CargaSuportada"));
        return t;
    }

    @Override
    //Tabela utilizada no SQL
    protected void setTabela() {
        tabela = "Motorista";
    }

    @Override
    //Quantidade de parametros em Motorista
    protected void setQtdParametros() {
        qtdParametros = " ?, ?, ?, ?, ?, ?";
    }

    public MotoristaViewModel fullConsult(int idMotorista) throws SQLException {
        String[] parametros = {String.valueOf(idMotorista)};
        JDataTable tab = HelperDAO.executaProcSelect("spConsultMotorista ?", parametros);
        if (tab == null) {
            return null;
        } else {
            HashMap<String, Object> registro = tab.getLinha(1);
            MotoristaViewModel motorista = MontaModel(registro);
            motorista.setId((Integer) registro.get("IdUsuario"));
            if (String.valueOf(registro.get("IdNaoAdm")).equals("")) {
                motorista.setIdNaoAdm(null);
            } else {
                motorista.setIdNaoAdm((Integer) registro.get("IdNaoAdm"));
            }
            if (String.valueOf(registro.get("IdAdministrador")).equals("")) {
                motorista.setIdAdministrador(null);
            } else {
                motorista.setIdAdministrador((Integer) registro.get("IdAdministrador"));
            }
            motorista.setNome((String) registro.get("Nome"));
            motorista.setData(LocalDate.parse(String.valueOf(registro.get("DataNascimento"))));
            motorista.setCpf((String) registro.get("CPF"));
            if (String.valueOf(registro.get("IdCliente")).equals("")) {
                motorista.setIdCliente(null);
            } else {
                motorista.setIdCliente(Integer.parseInt(String.valueOf(registro.get("IdCliente"))));
            }
            motorista.setTelefoneddd((Integer) registro.get("TelefoneDDD"));
            motorista.setTelefone((Integer) registro.get("Telefone"));
            return motorista;
        }

    }
}
