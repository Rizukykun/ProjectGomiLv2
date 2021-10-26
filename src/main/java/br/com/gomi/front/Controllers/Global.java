/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gomi.front.Controllers;

import br.com.gomi.shared.MotoristaViewModel;
import br.com.gomi.shared.SolicitacaoViewModel;

/**
 *
 * @author Fábio
 */
//Armazenar informações e motorista para ser passado entre as duas instancias de usuarios atuando (cliente e motorista)
public class Global {

    /*Singleton*/
    static private Global _instancia;

    private Global() {
    }

    public static Global obtemInstancia() {
        if (_instancia == null) {
            _instancia = new Global();
        }
        return _instancia;
    }
    /*Singleton*/

    SolicitacaoViewModel solicitacao;
    MotoristaViewModel motorista;
}
