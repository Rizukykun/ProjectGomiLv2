/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gomi.business;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.gomi.back.LoginDAO;
import br.com.gomi.back.NaoAdmDAO;
import br.com.gomi.back.SessaoDAO;
import br.com.gomi.back.UsuarioDAO;
import br.com.gomi.shared.NaoAdmViewModel;
import br.com.gomi.shared.SessaoViewModel;
import br.com.gomi.shared.UsuarioViewModel;

/**
 *
 * @author Administrador
 */
public class Validacao {

    //Validação dos campos, mensagens de erro caso vazio ou incorreto
    public static boolean validaLogin(String usuario, String senha) throws Exception {
        if (usuario.equals("") || senha.equals("")) {
            throw new Exception("Preencha os campos!");
        }
        UsuarioViewModel user = new LoginDAO().login(usuario, senha);
        if (user != null) {
            return true;
        } else {
            throw new Exception("Usuário ou senha incorretos");
        }
    }

    //Consulta caso usuario for Admin, Cliente, Motorista ou ambos Cliente e Motorista
    public static char validaTipoLogin(String usuario) throws SQLException, Exception {
        UsuarioViewModel user = new UsuarioDAO().consultaEmail(usuario);
        if (user.getIdNaoAdm() != null) {
            NaoAdmViewModel naoAdm = new NaoAdmDAO().consult(user.getIdNaoAdm());
            if (naoAdm.getIdCliente() != null && naoAdm.getIdMotorista() != null) {
                return 'X';
            }
            if (naoAdm.getIdCliente() != null) {
                return 'C';
            } else {
                return 'M';
            }
        } else {
            return 'A';
        }
    }

    //O usuario existe
    public static boolean usuarioExiste(String usuario) throws Exception {
        UsuarioViewModel user = new UsuarioDAO().consultaEmail(usuario);
        return user != null;
    }

    //Valida solicitação de coleta
    public static void validaSolicitacao(String descricao) throws Exception {
        if (descricao.trim().isEmpty()) {
            throw new Exception("Preencha a Descrição!");
        }
    }

    //Valida informações na tela de cadastro (se vazio, ou faltar @ em e-mail, exibir mensagem de erro)
    public static char validaCadastro(String email, String nome, String telefone,
            String cpf, String dataNascimento, String senha,
            String confirmacaoSenha, boolean ehCliente, String cep,
            String numero, String rua, String bairro, String cidade,
            String tipoVeiculo, String cnh, String dataExpiracao,
            String cnhCategoria, String cargaSuportada) throws Exception {
        char tipo = ' ';
        if (email.isEmpty()) {
            throw new Exception("Preencha o e-mail!");
        }
        if (nome.isEmpty()) {
            throw new Exception("Preencha o nome!");
        }
        if (telefone.isEmpty()) {
            throw new Exception("Preencha o telefone!");
        }
        if (cpf.isEmpty()) {
            throw new Exception("Preencha o cpf");
        }
        if (dataNascimento.isEmpty()) {
            throw new Exception("Preencha a data");
        }
        if (senha.isEmpty()) {
            throw new Exception("Digite sua senha");
        }
        if (confirmacaoSenha.isEmpty()) {
            throw new Exception("Digite a confirmação da senha");
        }

        if (email.indexOf("@") == -1 || email.lastIndexOf(".") < email.indexOf("@")) {
            throw new Exception("E-mail invalido");
        }
        if (usuarioExiste(email)) {
            char tipoExistente = validaTipoLogin(email);
            if ((tipoExistente == 'C' && !ehCliente) || (tipoExistente == 'M' && ehCliente)) {
                tipo = 'X';
            } else {
                throw new Exception("E-mail já cadastrado?");
            }
        }
        if (Integer.parseInt(telefone.substring(1, 3)) <= 10 || telefone.substring(1, 3).indexOf("0") != -1) {
            throw new Exception("DDD inválido!");
        }
        if (telefone.substring(4).length() > 9 || telefone.substring(4).length() < 8) {
            throw new Exception("Telefone inválido!");
        }
        if (cpf.length() != 11) {
            throw new Exception("CPF inválido!");
        }
        if (LocalDate.parse(dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy")).isAfter(LocalDate.now())) {
            throw new Exception("Data inválida!");
        }
        if (!senha.equals(confirmacaoSenha)) { //#Barros: Mexi aqui caso tenha dado erro. Apaga essa mensagem depois
            throw new Exception("Senha e confirmação não estão iguais!");
        }
        if (ehCliente) {
            if (tipo != 'X') {
                tipo = 'C';
            }
            if (cep.replace("-", "").length() != 8) {
                throw new Exception("CEP inválido!");
            }
            if (numero.isEmpty() || Integer.parseInt(numero) <= 0) {
                throw new Exception("Número Inválido!");
            }
            if (rua.isEmpty()) {
                throw new Exception("Preencha a rua!");
            }
            if (bairro.isEmpty()) {
                throw new Exception("Preencha o bairro!");
            }
            if (cidade.isEmpty()) {
                throw new Exception("Preencha a cidade!");
            }
            return tipo;
        } else {
            if (tipo != 'X') {
                tipo = 'M';
            }
            if (tipoVeiculo.isEmpty()) {
                throw new Exception("Preencha o tipo de veiculo!");
            }
            if (cnh.isEmpty()) {
                throw new Exception("Preencha a CNH!");
            }
            if (dataExpiracao.isEmpty()) {
                throw new Exception("Preencha a data de expiração!");
            }
            if (LocalDate.parse(dataExpiracao, DateTimeFormatter.ofPattern("dd/MM/yyyy")).isBefore(LocalDate.now())) {
                throw new Exception("Data de expiração inválida!");
            }
            if (cnhCategoria.length() != 1 || (cnhCategoria.equals("B") && cnhCategoria.equals("C") && cnhCategoria.equals("D") && cnhCategoria.equals("E"))) {
                throw new Exception("Categoria Inválida!");
            }
            if (cargaSuportada.isEmpty() || Integer.parseInt(cargaSuportada) <= 0) {
                throw new Exception("Preencha a carga suportada!");
            }
            return tipo;
        }
    }

    public static String getDistancia(String origem, String destino) {
        String distancia;
        String respostaAPI = ConsumidorAPI.getInstancia().getDirections(origem, destino);
        int instrucao = respostaAPI.indexOf("\"distance\" : {\n"
                + "                  \"text\" : \"") + 43;
        distancia = respostaAPI.substring(instrucao, instrucao + 15);
        return distancia.substring(0, distancia.indexOf("\""));
    }

    public static String getTempoChegada(String origem, String destino) {
        String tempo;
        String respostaAPI = ConsumidorAPI.getInstancia().getDirections(origem, destino);
        int instrucao = respostaAPI.indexOf("\"duration\" : {\n"
                + "                  \"text\" : \"") + 43;
        tempo = respostaAPI.substring(instrucao, instrucao + 15);
        return tempo.substring(0, tempo.indexOf("\""));
    }

	public static SessaoViewModel sessaoExiste(String sessao) throws Exception {
		SessaoViewModel sessaoView = new SessaoDAO().consultaSessao(sessao);
        return sessaoView;
	}
}
