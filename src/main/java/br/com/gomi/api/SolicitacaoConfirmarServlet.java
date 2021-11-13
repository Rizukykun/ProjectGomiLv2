package br.com.gomi.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.gomi.business.Dados;
import br.com.gomi.business.Validacao;
import br.com.gomi.shared.SessaoViewModel;
import br.com.gomi.shared.SolicitacaoViewModel;

@WebServlet("/Solicitacao/Confirmar")
public class SolicitacaoConfirmarServlet extends PadraoServlet {
	private static final long serialVersionUID = 1L;

	public SolicitacaoConfirmarServlet() {
		super();
	}

	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");

		String sessaoHash = req.getHeader("sessao");
		int solicitacaoId = Integer.valueOf(req.getParameter("idSolicitacao"));

		try {
			SessaoViewModel sessaoModel = Validacao.sessaoExiste(sessaoHash);

			if (sessaoModel == null) {
				resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				resp.getWriter().append("Sessão não existe");
			} else {

				SolicitacaoViewModel model = Dados.recuperaSolicitacao(solicitacaoId);

				String resposta = "";

				if (!model.isAberto()) {
					resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
					resp.getWriter().append("Solicitação já foi concluída");
				} else {
					if (model.isColetado()) {
						model.setAberto(false);
						resposta = "Coleta confirmada. Solicitação concluída";
					} else {
						model.setColetado(true);
						resposta = "Coleta confirmada. Aguardando confirmação";
					}

					Dados.atualizaSolicitacao(model);
					resp.setStatus(HttpServletResponse.SC_OK);
					resp.getWriter().append(resposta);
				}
			}

		} catch (Exception e) {
			e.printStackTrace(resp.getWriter());
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	protected Integer metodoGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json");

		int solicitacaoId = Integer.valueOf(req.getParameter("idSolicitacao"));

		try {
			if (Dados.recuperaSolicitacao(solicitacaoId).isAberto()) {
				resp.getWriter().append(" \"status\" : \"aberto\" ");
				return HttpServletResponse.SC_OK;
			} else {
				resp.getWriter().append("{ \"status\" : \"fechado\" }");
				return HttpServletResponse.SC_OK;
			}
		} catch (Exception e) {
			e.printStackTrace(resp.getWriter());
			return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		}
	}

	@Override
	protected void setMethods() {
		this.methods = "PUT";
	}

}
