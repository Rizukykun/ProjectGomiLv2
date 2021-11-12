package br.com.gomi.back;

import java.sql.Timestamp;
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
		Timestamp timeStamp = (Timestamp) registro.get("TempoLimite");
		t.setTempoLimite(timeStamp.toLocalDateTime());
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

	public SessaoViewModel consultaSessao(String sessao) throws Exception {
		String[] parametros = {sessao};
        JDataTable tab = HelperDAO.executaProcSelect("spConsultSessao ?", parametros);
        if (tab == null) {
            return null;
        } else {
            return MontaModel(tab.getLinha(1));
        }
	}

}
