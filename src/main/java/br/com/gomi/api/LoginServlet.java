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
			char tipo = Validacao.validaTipoLogin(senha); // Combinar ambos em um �nico m�todo

			// Criar sess�o no banco. Retornar c�digo de sess�o (Maybe fazer-lo ser o hash
			// do id da sess�o

			resp.setStatus(HttpServletResponse.SC_ACCEPTED);
			// Enviar no body o c�digo de sess�o e, maybe, o tipo de usu�rio

		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
			resp.getWriter().write(e.getMessage());
			resp.getWriter().flush();
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);

		// Valida se a sess�o existe
		// Remove a sess�o (Ou Desativa)
	}

	@Override
	protected void setMethods() {
		methods = "POST, DELETE";
	}

}
