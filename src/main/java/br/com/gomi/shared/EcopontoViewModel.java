/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gomi.shared;

/**
 *
 * @author Fábio
 */
//
public class EcopontoViewModel extends PadraoViewModel {

    private String cep; //CEP do ecoponto
    private int numero; //Numero do ecoponto

    //metodos get e set para escrever nos campos vendo se condiz com string ou int
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
