package br.com.gomi.api;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

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
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.addHeader("Access-Control-Allow-Headers", "*");
		
		int sc;
		String textResponse = "";

		resp.setContentType("application/json");

		try {
			if (req.getParameter("idSolicitacao").equals("all")) {

				List<SolicitacaoViewModel> lista = Dados.recuperaSolicitacoes();
				textResponse = new Gson().toJson(lista);
				sc = HttpServletResponse.SC_ACCEPTED;

			} else {

				int idSolicitacao = Integer.valueOf(req.getParameter("idSolicitacao"));

				SolicitacaoViewModel model = Dados.recuperaSolicitacao(idSolicitacao);
				textResponse = new Gson().toJson(model);
				sc = HttpServletResponse.SC_ACCEPTED;
			}
		} catch (Exception e) {
			e.printStackTrace(resp.getWriter());
			sc = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		}

		resp.setStatus(sc);

		if (!textResponse.equals("")) {
			resp.getWriter().write(textResponse);
			resp.getWriter().flush();
		}
	}

	@Override
	protected Integer metodoPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		resp.setContentType("application/json");

		String sessaoHash = req.getHeader("sessao");
		String descricao = req.getParameter("descricao");

		try {
			int idUsuario = Validacao.sessaoExiste(sessaoHash).getidUsuario();

			Validacao.validaSolicitacao(descricao);

			SolicitacaoViewModel solicitacao = new SolicitacaoViewModel();

			int idNaoAdm = Dados.recuperaUsuario(idUsuario).getIdNaoAdm();
			int idCliente = Dados.recuperaNaoAdm(idNaoAdm).getIdCliente();

			ClienteViewModel cliente = Dados.recuperaClienteImcompleto(idCliente);// Cliente da sess?o
			solicitacao.setIdCliente(cliente.getIdCliente());
			solicitacao.setCep(cliente.getCep());
			solicitacao.setNumero(cliente.getNumero());
			solicitacao.setDescricao(descricao);
			solicitacao.setDataSolicitacao(LocalDateTime.now());
			solicitacao.setAberto(true);
			// adicionar categorias na lista
			solicitacao.setId(Dados.insereSolicitacao(solicitacao));

			resp.getWriter().append("{\"id\":\"" + solicitacao.getId() + "\"}");
			return HttpServletResponse.SC_CREATED;
		} catch (Exception e) {
			e.printStackTrace(resp.getWriter());
			return HttpServletResponse.SC_BAD_REQUEST;
		}
	}

	@Override
	protected void setMethods() {
		methods = "GET, POST";
	}

}
