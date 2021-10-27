package br.com.gomi.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Cliente")
public class ClienteServlet extends PadraoServlet {
	private static final long serialVersionUID = 1L;

	public ClienteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		int sc;
		String textResponse = "";

		try {
			CadastraUsuario(request, true);
			sc = HttpServletResponse.SC_OK;
		} catch (Exception e) {
			sc = HttpServletResponse.SC_BAD_REQUEST;
			response.getWriter().write(e.getMessage());
			response.getWriter().flush();
		}

		response.setStatus(sc);

		if (!textResponse.equals("")) {
			response.getWriter().write(textResponse);
			response.getWriter().flush();
		}
	}

}
