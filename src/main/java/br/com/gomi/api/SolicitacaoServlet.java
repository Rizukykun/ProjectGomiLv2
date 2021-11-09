package br.com.gomi.api;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.gomi.business.Dados;
import br.com.gomi.business.Validacao;
import br.com.gomi.shared.ClienteViewModel;
import br.com.gomi.shared.SolicitacaoViewModel;

@WebServlet("/Solicitacao")
public class SolicitacaoServlet extends PadraoServlet {
	private static final long serialVersionUID = 1L;

	public SolicitacaoServlet() {
		super();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int sc;
		String textResponse = "";

		// Validar sessão
		String descricao = req.getParameter("descricao");

		try {
			Validacao.validaSolicitacao(descricao);

			SolicitacaoViewModel solicitacao = new SolicitacaoViewModel();
			ClienteViewModel cliente = null;// Cliente da sessão
			solicitacao.setIdCliente(cliente.getIdCliente());
			solicitacao.setCep(cliente.getCep());
			solicitacao.setNumero(cliente.getNumero());
			solicitacao.setDescricao(descricao);
			solicitacao.setDataSolicitacao(LocalDateTime.now());
			solicitacao.setAberto(true);
			// adicionar categorias na lista
			solicitacao.setId(Dados.insereSolicitacao(solicitacao));

			textResponse = "{\"id\":\"" + solicitacao.getId() + "\"}";
			sc = HttpServletResponse.SC_ACCEPTED;
		} catch (Exception e) {
			sc = HttpServletResponse.SC_BAD_REQUEST;
		}

		resp.setStatus(sc);

		if (!textResponse.equals("")) {
			resp.getWriter().write(textResponse);
			resp.getWriter().flush();
		}
	}

	@Override
	protected void setMethods() {
		methods = "POST";
	}

}
