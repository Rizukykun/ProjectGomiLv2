package br.com.gomi.shared;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SessaoViewModel extends PadraoViewModel {
	String hashSessao;
	int idCliente;
	LocalDateTime tempoLimite;
	
	public String getHashSessao() {
		return hashSessao;
	}
	public void setHashSessao(String hashSessao) {
		this.hashSessao = hashSessao;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public LocalDateTime getTempoLimite() {
		return tempoLimite;
	}
	public void setTempoLimite(LocalDateTime localDateTime) {
		this.tempoLimite = localDateTime;
	}
}
