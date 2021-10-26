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
public class NaoAdmViewModel extends UsuarioViewModel {

    private Integer idCliente;//ID do cliente
    private Integer idMotorista;//ID do motorista
    private int telefoneddd;///DDD do telefone
    private int telefone;//número de telefone
    //Informações de pagamento
    //Armazenamento de foto

    //metodos get e set para escrever nos campos vendo se condiz com string ou int
    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdMotorista() {
        return idMotorista;
    }

    public void setIdMotorista(Integer idMotorista) {
        this.idMotorista = idMotorista;
    }

    public int getTelefoneddd() {
        return telefoneddd;
    }

    public void setTelefoneddd(int telefoneddd) {
        this.telefoneddd = telefoneddd;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

}
