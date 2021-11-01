package br.com.gomi.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Motorista")
public class MotoristaServlet extends CadastroServlet {
	private static final long serialVersionUID = 1L;

	public MotoristaServlet() {
		super();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		isUsuario = false;
		super.doPost(req, resp);
	}

	@Override
	protected void setMethods() {
		methods = "POST";
	}

}
