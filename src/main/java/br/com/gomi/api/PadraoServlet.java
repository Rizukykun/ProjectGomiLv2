package br.com.gomi.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.gomi.business.Dados;
import br.com.gomi.shared.SolicitacaoViewModel;

public abstract class PadraoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected String methods = "";

	public PadraoServlet() {
		super();
	}

	protected void doOptions(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", methods);

		super.doOptions(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int sc;
		String textResponse = "";

		sc = metodoGet(req, resp, textResponse);

		resp.setStatus(sc);

		if (!textResponse.equals("")) {
			resp.getWriter().write(textResponse);
			resp.getWriter().flush();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.addHeader("Access-Control-Allow-Origin", "*");
		int sc;
		String textResponse = "";

		sc = metodoPost(req, resp, textResponse);

		resp.setStatus(sc);

		if (!textResponse.equals("")) {
			resp.getWriter().write(textResponse);
			resp.getWriter().flush();
		}
	}

	protected Integer metodoGet(HttpServletRequest req, HttpServletResponse resp, String textResponse)
			throws IOException {
		return 501;

	}

	protected Integer metodoPost(HttpServletRequest req, HttpServletResponse resp, String textResponse)
			throws IOException {
				return 501;

	}

	protected abstract void setMethods();
}
