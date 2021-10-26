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
public class ClienteViewModel extends NaoAdmViewModel {

    private String rua; //Rua do cliente
    private int numero; //Numero da casa do cliente
    private String complemento; //Complemento do cliente
    private String bairro; //Bairro do cliente
    private String cidade; //Cidade do cliente
    private String cep; //CEP do cliente

    //metodos get e set para escrever nos campos vendo se condiz com string ou int
    public String getRua() {
        return rua; //Rua do cliente
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero; //Número do cliente
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento; //Complemento do cliente
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro; //Bairro do cliente
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;//Cidade do cliente
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep; //CEP do cliente
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
