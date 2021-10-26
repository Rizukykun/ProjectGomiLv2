/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gomi.shared;

/**
 *
 * @author Administrador
 */
public class UsuarioAtual {

    //Implementação de Singleton
    private static UsuarioAtual _instancia;

    private UsuarioAtual() {

    }

    public static UsuarioAtual getInstancia() {
        if (_instancia == null) {
            _instancia = new UsuarioAtual();
        }
        return _instancia;
    }
    //Implementação de Singleton
    private AdministradorViewModel avm;
    private ClienteViewModel cvm;
    private MotoristaViewModel mvm;

    //Instancias diferentes de usuarios
    public void setUsuario(UsuarioViewModel uvm) {
        if (uvm instanceof AdministradorViewModel) {
            avm = (AdministradorViewModel) uvm;
        } else if (uvm instanceof ClienteViewModel) {
            cvm = (ClienteViewModel) uvm;
        } else {
            mvm = (MotoristaViewModel) uvm;
        }
    }

    public UsuarioViewModel getUsuario() {
        if (avm != null) {
            return avm; 
        } else if (cvm != null) {
            return cvm;
        } else {
            return mvm;
        }
    }

    //logoff de usuarios
    public void logoff() {
        avm = null;
        cvm = null;
        mvm = null;
    }
}
