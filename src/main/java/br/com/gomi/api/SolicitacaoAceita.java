package br.com.gomi.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.gomi.business.Dados;
import br.com.gomi.shared.SolicitacaoViewModel;

@WebServlet("/Solicitacao/Motorista")
public class SolicitacaoAceita extends PadraoServlet {
	private static final long serialVersionUID = 1L;

	public SolicitacaoAceita() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int sc;
		String textResponse = "";
		Gson gson = new Gson();
		
		int idSolicitacao = Integer.parseInt(req.getParameter("idSolicitacao"));
		
		try {
			SolicitacaoViewModel model = Dados.recuperaSolicitacao(idSolicitacao);
			if (model.getIdMotorista() != null) {		
				textResponse = gson.toJson(Dados.recuperaMotorista(model.getIdMotorista()));
			}
			else {
				textResponse = "{ null }";
			}
			sc = HttpServletResponse.SC_OK;
		} catch (Exception e) {
			sc = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		}
		
		resp.setStatus(sc);

		if (!textResponse.equals("")) {
			resp.getWriter().write(textResponse);
			resp.getWriter().flush();
		}
	}

	@Override
	protected void setMethods() {
		methods = "GET";
	}

}
