package br.com.gomi.shared;

import java.time.LocalDateTime;

public class SessaoViewModel extends PadraoViewModel {
	String hashSessao;
	int idUsuario;
	LocalDateTime tempoLimite;

	public String getHashSessao() {
		return hashSessao;
	}

	public void setHashSessao(String hashSessao) {
		this.hashSessao = hashSessao;
	}

	public int getidUsuario() {
		return idUsuario;
	}

	public void setidUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public LocalDateTime getTempoLimite() {
		return tempoLimite;
	}

	public void setTempoLimite(LocalDateTime localDateTime) {
		this.tempoLimite = localDateTime;
	}
}
