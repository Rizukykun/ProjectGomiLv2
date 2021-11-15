package br.com.gomi.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.gomi.business.Dados;
import br.com.gomi.business.Validacao;
import br.com.gomi.shared.ClienteViewModel;
import br.com.gomi.shared.MotoristaViewModel;
import br.com.gomi.shared.NaoAdmViewModel;
import br.com.gomi.shared.SessaoViewModel;
import br.com.gomi.shared.UsuarioViewModel;

@WebServlet("/Usuario")
public class UsuarioServlet extends PadraoServlet {
	private static final long serialVersionUID = 1L;

	public UsuarioServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int sc;
		String textResponse = "";

		resp.setContentType("application/json");

		try {
			String sessaoHash = req.getHeader("sessao");
			SessaoViewModel sessao = Validacao.sessaoExiste(sessaoHash);

			int idUsuario = sessao.getidUsuario();

			UsuarioViewModel uModel = Dados.recuperaUsuario(idUsuario);
			int idNaoAdm = uModel.getIdNaoAdm();
			NaoAdmViewModel naModel = Dados.recuperaNaoAdm(idNaoAdm);

			if (naModel.getIdCliente() != null) {
				int idCliente = naModel.getIdCliente();
				ClienteViewModel model = Dados.recuperaClienteImcompleto(idCliente);

				model.setCpf(uModel.getCpf());
				model.setData(uModel.getData());
				model.setEmail(uModel.getEmail());
				model.setNome(uModel.getNome());
				model.setTelefone(naModel.getTelefone());
				model.setTelefoneddd(naModel.getTelefoneddd());

				textResponse = new Gson().toJson(model);
			} else {
				int idMotorista = naModel.getIdMotorista();
				MotoristaViewModel model = Dados.recuperaMotorista(idMotorista);

				textResponse = new Gson().toJson(model);
			}
			sc = HttpServletResponse.SC_ACCEPTED;
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
	protected void setMethods() {
		methods = "Get";

	}

}
