package br.com.gomi.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.entity.ContentType;

import br.com.gomi.business.Dados;
import br.com.gomi.business.Validacao;

@WebServlet({ "/Login" })
public class LoginServlet extends PadraoServlet {
	private static final long serialVersionUID = 1L;
	
	public LoginServlet() {
		super();
	}

	@Override
	protected Integer metodoPost(HttpServletRequest req, HttpServletResponse resp, String textResponse)
			throws IOException {
		resp.setContentType("application/json");
		
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");

		try {
			Validacao.validaLogin(login, senha);
			char tipo = Validacao.validaTipoLogin(login); // Combinar ambos em um único método

			int usuarioId; //Testar Hashes repetidos
			
			if(tipo == 'M')
				usuarioId = Dados.recuperaMotorista(login).getId();
			else
				usuarioId = Dados.recuperaCliente(login).getId();
			
			String sessaoHash = Dados.CriarSessão(usuarioId);

			resp.getWriter().append("{ \"sessao\" : \"" + sessaoHash + "\" }");
			return HttpServletResponse.SC_CREATED;

		} catch (Exception e) {
			e.printStackTrace(resp.getWriter());
			return HttpServletResponse.SC_FORBIDDEN;
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);

		// Valida se a sessão existe
		// Remove a sessão (Ou Desativa)
	}

	@Override
	protected void setMethods() {
		methods = "POST, DELETE";
	}

}
