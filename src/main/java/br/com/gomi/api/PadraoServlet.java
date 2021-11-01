package br.com.gomi.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	protected abstract void setMethods();
}
