package br.com.gomi.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.gomi.business.Dados;

@WebServlet("/Motorista")
public class MotoristaServlet extends CadastroServlet {
	private static final long serialVersionUID = 1L;

	public MotoristaServlet() {
		super();
	}

	@Override
	protected Integer metodoGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json");

		int idMotorista = Integer.valueOf(req.getParameter("idMotorista"));

		try {
			resp.getWriter().append(new Gson().toJson(Dados.recuperaMotorista(idMotorista)));
			return HttpServletResponse.SC_ACCEPTED;
		} catch (Exception e) {
			e.printStackTrace(resp.getWriter());
			return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		isUsuario = false;
		super.doPost(req, resp);
	}

	@Override
	protected void setMethods() {
		methods = "GET, POST";
	}

}
