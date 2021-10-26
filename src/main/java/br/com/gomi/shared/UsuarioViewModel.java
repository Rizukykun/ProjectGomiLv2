/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gomi.shared;

import java.time.LocalDate;

/**
 *
 * @author Fábio
 */
public class UsuarioViewModel extends PadraoViewModel {

    private Integer idNaoAdm; //ID de um usuario não administrativo
    private Integer idAdministrador; //ID de um usuario administrador
    private String email; //emal do usuario
    private String senha; //senha do usuario
    private String nome; // nome do usuario
    private LocalDate dataNascimento; //data de nascimento usuario
    private String cpf; //cpf usuario

    public Integer getIdNaoAdm() {
        return idNaoAdm;
    }

    public void setIdNaoAdm(Integer idCliente) {
        this.idNaoAdm = idCliente;
    }

    public Integer getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(Integer idMotorista) {
        this.idAdministrador = idMotorista;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getData() {
        return dataNascimento;
    }

    public void setData(LocalDate data) {
        this.dataNascimento = data;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
