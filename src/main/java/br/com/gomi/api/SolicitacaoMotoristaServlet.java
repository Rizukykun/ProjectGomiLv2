package br.com.gomi.api;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.gomi.business.Dados;
import br.com.gomi.shared.SolicitacaoViewModel;

@WebServlet("/Solicitacao/Motorista")
public class SolicitacaoMotoristaServlet extends PadraoServlet {
	private static final long serialVersionUID = 1L;

	public SolicitacaoMotoristaServlet() {
		super();
	}

	@Override
	protected Integer metodoGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("application/json");
		
		int idSolicitacao = Integer.valueOf(req.getParameter("idSolicitacao"));

		try {
			SolicitacaoViewModel model = Dados.recuperaSolicitacao(idSolicitacao);
			if (model.getIdMotorista() != null) {
				resp.getWriter().append(new Gson().toJson(Dados.recuperaMotorista(model.getIdMotorista())));
			} else {
				resp.getWriter().append("{ null }");
			}
			return HttpServletResponse.SC_OK;
		} catch (Exception e) {
			e.printStackTrace(resp.getWriter());
			return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		}
	}

	@Override
	protected void setMethods() {
		methods = "GET";
	}

}
