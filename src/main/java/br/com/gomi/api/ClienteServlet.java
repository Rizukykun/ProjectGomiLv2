package br.com.gomi.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Cliente")
public class ClienteServlet extends CadastroServlet {
	private static final long serialVersionUID = 1L;

	public ClienteServlet() {
		super();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		isUsuario = true;
		super.doPost(req, resp);
	}

	@Override
	protected void setMethods() {
		methods = "POST";
	}

}
