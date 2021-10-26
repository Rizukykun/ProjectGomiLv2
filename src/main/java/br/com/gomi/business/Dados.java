/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gomi.business;

import br.com.gomi.back.*;
import br.com.gomi.shared.*;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class Dados {

    //Create
    public static int insereCliente(ClienteViewModel cliente) throws Exception {
        ClienteDAO dao = new ClienteDAO();
        int retorno = dao.insert(cliente);
        Auditoria.obtemInstancia().salvarLog("Cliente Id:" + retorno + " foi adicionado.");
        return retorno;
    }

    public static int insereNaoAdm(NaoAdmViewModel naoAdm) throws Exception {
        NaoAdmDAO dao = new NaoAdmDAO();
        int retorno = dao.insert(naoAdm);
        Auditoria.obtemInstancia().salvarLog("NaoAdm Id:" + retorno + " foi adicionado.");
        return retorno;
    }

    public static int insereUsuario(UsuarioViewModel usuario) throws Exception {
        UsuarioDAO dao = new UsuarioDAO();
        int retorno = dao.insert(usuario);
        Auditoria.obtemInstancia().salvarLog("Usuário Id:" + retorno + " foi adicionado.");
        return retorno;
    }

    public static int insereMotorista(MotoristaViewModel motorista) throws Exception {
        MotoristaDAO dao = new MotoristaDAO();
        int retorno = dao.insert(motorista);
        Auditoria.obtemInstancia().salvarLog("Motorista Id:" + retorno + " foi adicionado.");
        return retorno;
    }

    public static int insereSolicitacao(SolicitacaoViewModel solicitacao) throws Exception {
        SolicitacaoDAO dao = new SolicitacaoDAO();
        int retorno = dao.insert(solicitacao);
        Auditoria.obtemInstancia().salvarLog("Solicitação Id:" + retorno + " foi adicionada.");
        return retorno;
    }

    //Read
    public static ClienteViewModel recuperaCliente(String login) throws Exception {
        UsuarioViewModel user = new UsuarioDAO().consultaEmail(login);
        NaoAdmViewModel na = new NaoAdmDAO().consult(user.getIdNaoAdm());
        ClienteViewModel cli = new ClienteDAO().consult(na.getIdCliente());
        cli.setIdNaoAdm(na.getIdNaoAdm());
        cli.setIdMotorista(na.getIdMotorista());
        cli.setTelefoneddd(na.getTelefoneddd());
        cli.setTelefone(na.getTelefone());
        cli.setId(user.getId());
        cli.setEmail(user.getEmail());
        cli.setSenha(user.getSenha());
        cli.setNome(user.getNome());
        cli.setData(user.getData());
        cli.setCpf(user.getCpf());
        return cli;
    }

    public static MotoristaViewModel recuperaMotorista(String login) throws SQLException, Exception {
        UsuarioViewModel user = new UsuarioDAO().consultaEmail(login);
        NaoAdmViewModel na = new NaoAdmDAO().consult(user.getIdNaoAdm());
        MotoristaViewModel mot = new MotoristaDAO().consult(na.getIdMotorista());
        mot.setIdNaoAdm(na.getIdNaoAdm());
        mot.setIdCliente(na.getIdCliente());
        mot.setTelefoneddd(na.getTelefoneddd());
        mot.setTelefone(na.getTelefone());
        mot.setId(user.getId());
        mot.setEmail(user.getEmail());
        mot.setSenha(user.getSenha());
        mot.setNome(user.getNome());
        mot.setData(user.getData());
        mot.setCpf(user.getCpf());
        return mot;
    }

    public static MotoristaViewModel recuperaMotorista(int idMotorista) throws SQLException, Exception {
        MotoristaViewModel motorista = new MotoristaDAO().fullConsult(idMotorista);
        return motorista;
    }

    public static NaoAdmViewModel recuperaNaoAdm(String login) throws SQLException, Exception {
        UsuarioViewModel user = new UsuarioDAO().consultaEmail(login);
        NaoAdmViewModel na = new NaoAdmDAO().consult(user.getIdNaoAdm());
        return na;
    }

    public static SolicitacaoViewModel recuperaSolicitacao(int id) throws Exception {
        SolicitacaoViewModel model = new SolicitacaoDAO().consult(id);
        return model;
    }

    //Update    
    public static void atualizaNaoAdm(NaoAdmViewModel na) throws Exception {
        NaoAdmDAO dao = new NaoAdmDAO();
        dao.update(na);
        Auditoria.obtemInstancia().salvarLog("NaoAdm Id:" + na.getIdNaoAdm() + " foi atualizado.");
    }

    //Atualizar senha do usuario
    public static String atualizaSenhaUsuario(String email, String senha) throws Exception {
        UsuarioViewModel user = new UsuarioDAO().consultaEmail(email);
        user.setSenha(senha);
        new UsuarioDAO().update(user);
        Auditoria.obtemInstancia().salvarLog("Senha do Usuário Id:" + user.getId() + " foi atualizada.");
        return user.getNome();
    }

    public static void atualizaSolicitacao(SolicitacaoViewModel solicitacao) throws Exception {
        SolicitacaoDAO dao = new SolicitacaoDAO();
        dao.update(solicitacao);
        Auditoria.obtemInstancia().salvarLog("Solicitação Id:" + solicitacao.getId() + " foi atualizada.");
    }

    //recupera todas as solicitações abertas
    public static List<SolicitacaoViewModel> recuperaSolicitacoes() throws Exception {
        SolicitacaoDAO dao = new SolicitacaoDAO();
        return dao.listarAbertas();
    }
}
