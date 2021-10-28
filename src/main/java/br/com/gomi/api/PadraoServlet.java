package br.com.gomi.api;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.gomi.business.Dados;
import br.com.gomi.business.Validacao;
import br.com.gomi.shared.ClienteViewModel;
import br.com.gomi.shared.MotoristaViewModel;
import br.com.gomi.shared.NaoAdmViewModel;

public class PadraoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PadraoServlet() {
		super();
	}

	protected void doOptions(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST");

		super.doOptions(request, response);
	}

	protected void CadastraUsuario(HttpServletRequest request, boolean ehCliente) throws Exception {
		String email = request.getParameter("email").trim();
		String nome = request.getParameter("nome").trim();
		String telefone = request.getParameter("telefone").trim();
		String cpf = request.getParameter("cpf").trim();
		String dataNascimento = request.getParameter("dataNascimento").trim();
		String senha = request.getParameter("senha").trim();
		String confirmacaoSenha = request.getParameter("confirmacaoSenha").trim();
		String cep = "", numero = "", rua = "", complemento = "", bairro = "", cidade = "", tipoVeiculo = "", cnh = "",
				dataExpiracao = "", cnhCategoria = "", cargaSuportada = "";

		if (ehCliente) {
			cep = request.getParameter("cep").trim();
			numero = request.getParameter("numero").trim();
			rua = request.getParameter("rua").trim();
			complemento = request.getParameter("complemento");
			bairro = request.getParameter("bairro").trim();
			cidade = request.getParameter("cidade").trim();
		} else {
			tipoVeiculo = request.getParameter("tipoVeiculo").trim();
			cnh = request.getParameter("cnh").trim();
			dataExpiracao = request.getParameter("dataExpiracao").trim();
			cnhCategoria = request.getParameter("cnhCategoria").trim();
			cargaSuportada = request.getParameter("cargaSuportada").trim();
		}

		try {
			Validacao.validaCadastro(email, nome, telefone, cpf, dataNascimento, senha, confirmacaoSenha, ehCliente,
					cep, numero, rua, bairro, cidade, tipoVeiculo, cnh, dataExpiracao, cnhCategoria, cargaSuportada);
		} catch (Exception e) {
			throw new Exception(e);
		}

		NaoAdmViewModel model = new NaoAdmViewModel();

		if (ehCliente) {
			model = new ClienteViewModel();
		} else {
			model = new MotoristaViewModel();
		}

		model.setEmail(email);
		model.setNome(nome);
		model.setTelefoneddd(Integer.parseInt(telefone.substring(1, 3)));
		model.setTelefone(Integer.parseInt(telefone.substring(4)));
		model.setCpf(cpf.trim());
		model.setData(LocalDate.parse(dataNascimento, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		model.setSenha(senha);

		if (ehCliente) {
			ClienteViewModel cliente = (ClienteViewModel) model;

			cliente.setCep(cep);
			cliente.setNumero(Integer.parseInt(numero));
			cliente.setRua(rua);
			cliente.setComplemento(complemento);
			cliente.setBairro(bairro);
			cliente.setCidade(cidade);
			cliente.setIdCliente(Dados.insereCliente(cliente));
			cliente.setIdNaoAdm(Dados.insereNaoAdm(cliente));
			Dados.insereUsuario(cliente);
		} else {
			MotoristaViewModel motorista = (MotoristaViewModel) model;
			
			motorista.setTipoVeiculo(tipoVeiculo);
            motorista.setCnh(cnh);
            motorista.setDataExpiracao(LocalDate.parse(dataExpiracao, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            motorista.setCnhCategoria(cnhCategoria.charAt(0));
            motorista.setCargaSuportada(Integer.parseInt(cargaSuportada));
            motorista.setIdMotorista(Dados.insereMotorista(motorista));
            motorista.setIdNaoAdm(Dados.insereNaoAdm(motorista));
            Dados.insereUsuario(motorista);
		}
	}

}
