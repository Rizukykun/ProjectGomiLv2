package br.com.gomi.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.gomi.business.Validacao;

@WebServlet({ "/Login" })
public class LoginServlet extends PadraoServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");

		try {
			Validacao.validaLogin(login, senha);
			char tipo = Validacao.validaTipoLogin(senha); // Combinar ambos em um único método

			// Criar sessão no banco. Retornar código de sessão (Maybe fazer-lo ser o hash
			// do id da sessão

			resp.setStatus(HttpServletResponse.SC_ACCEPTED);
			// Enviar no body o código de sessão e, maybe, o tipo de usuário

		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
			resp.getWriter().write(e.getMessage());
			resp.getWriter().flush();
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
