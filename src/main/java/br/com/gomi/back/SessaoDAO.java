package br.com.gomi.back;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

import br.com.gomi.shared.SessaoViewModel;

public class SessaoDAO extends PadraoDAO<SessaoViewModel> {

	@Override
	protected String[] CriaParametros(SessaoViewModel model) {
		String[] parametros = new String[3];
		parametros[0] = model.getHashSessao();
		parametros[1] = String.valueOf(model.getTempoLimite());
		parametros[2] = String.valueOf(model.getIdCliente());
		return parametros;
	}

	@Override
	protected SessaoViewModel MontaModel(HashMap<String, Object> registro) {
		SessaoViewModel t = new SessaoViewModel();
		t.setHashSessao((String) registro.get("HashSessao"));
		t.setTempoLimite((LocalDateTime) registro.get("TempoLimite"));
		t.setIdCliente((int) registro.get("IdCliente"));
		return t;
	}

	@Override
	protected void setTabela() {
		tabela = "Sessao";
	}

	@Override
	protected void setQtdParametros() {
		qtdParametros = " ?, ?, ?";
	}

}
