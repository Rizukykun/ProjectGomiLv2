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
public class MotoristaViewModel extends NaoAdmViewModel {

    private String tipoVeiculo; //Tipo do veiculo
    private String cnh; //CNH do motorista
    private LocalDate dataExpiracao; //Data de expiração
    private char cnhCategoria; //Categoria da CNH (a, b, c, d, etc)
    private int cargaSuportada; //Carga maxima suportada do veiculo
    

    //metodos get e set para escrever nos campos vendo se condiz com string ou int
    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public LocalDate getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(LocalDate dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public char getCnhCategoria() {
        return cnhCategoria;
    }

    public void setCnhCategoria(char cnhCategoria) {
        this.cnhCategoria = Character.toUpperCase(cnhCategoria);
    }

    public int getCargaSuportada() {
        return cargaSuportada;
    }

    public void setCargaSuportada(int cargaSuportada) {

        this.cargaSuportada = cargaSuportada;
    }
}
