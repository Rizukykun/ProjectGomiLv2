package br.com.gomi.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.gomi.business.Dados;
import br.com.gomi.business.Validacao;
import br.com.gomi.shared.SessaoViewModel;
import br.com.gomi.shared.SolicitacaoViewModel;

@WebServlet("/Solicitacao/Motorista")
public class SolicitacaoMotoristaServlet extends PadraoServlet {
	private static final long serialVersionUID = 1L;

	public SolicitacaoMotoristaServlet() {
		super();
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");

		String sessaoHash = req.getHeader("sessao");
		int solicitacaoId = Integer.valueOf(req.getParameter("idSolicitacao"));

		try {
			SessaoViewModel sessaoModel = Validacao.sessaoExiste(sessaoHash);
			int idNaoAdm = Dados.recuperaUsuario(sessaoModel.getidUsuario()).getIdNaoAdm();
			int idMotorista = Dados.recuperaNaoAdm(idNaoAdm).getIdMotorista();

			SolicitacaoViewModel solicitacao = Dados.recuperaSolicitacao(solicitacaoId);
			solicitacao.setIdMotorista(idMotorista);

			Dados.atualizaSolicitacao(solicitacao);

			resp.setStatus(HttpServletResponse.SC_OK);
			resp.getWriter().append("Solicitação Aceita");

		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace(resp.getWriter());
		}
	}

	@Override
	protected Integer metodoGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
