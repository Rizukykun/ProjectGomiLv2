/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gomi.front.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.gomi.business.Dados;
import br.com.gomi.business.Validacao;
import br.com.gomi.business.WebPageOpener;
import br.com.gomi.shared.MotoristaViewModel;
import br.com.gomi.shared.SolicitacaoViewModel;
import br.com.gomi.shared.UsuarioAtual;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author jonyg
 */
public class DetalhesPedidoController extends PadraoController {

	// Variaveis do endereço, descrição e distancia
	@FXML
	TextField txtEndereco;
	@FXML
	TextField txtDescricao;
	@FXML
	TextField txtDistancia;

	// Aceita o pedido
	public void btnAceitarOnClick(ActionEvent event) throws IOException, Exception {
		Global.obtemInstancia().solicitacao
				.setIdMotorista(((MotoristaViewModel) UsuarioAtual.getInstancia().getUsuario()).getIdMotorista());
		Dados.atualizaSolicitacao(Global.obtemInstancia().solicitacao);
		WebPageOpener.openWebpage(
				"https://google.com/maps/dir/" + Global.obtemInstancia().solicitacao.getOrigem().replace(' ', '+') + "/"
						+ Global.obtemInstancia().solicitacao.getCep() + "/ecoponto");
		AtenderSolicitacaoController atender = new AtenderSolicitacaoController();
		atender.start((Stage) ((Button) event.getSource()).getScene().getWindow());
	}

	// Nega o pedido
	public void btnRecusarOnClick(ActionEvent event) throws IOException {
		PrincipalMController principal = new PrincipalMController();
		Global.obtemInstancia().solicitacao = null;
		principal.start((Stage) ((Button) event.getSource()).getScene().getWindow());
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		SolicitacaoViewModel solicitacao = Global.obtemInstancia().solicitacao;
		txtEndereco.setText(solicitacao.getCep() + ", Nº" + solicitacao.getNumero());
		txtDescricao.setText(solicitacao.getDescricao());
		txtDistancia.setText(Validacao.getDistancia(Global.obtemInstancia().solicitacao.getOrigem(),
				Global.obtemInstancia().solicitacao.getCep()));
	}

	// Inicia a tela
	@Override
	public void start(Stage stage) throws IOException {
		Parent home_page_parent = FXMLLoader.load(getClass().getResource("/br/com/gomi/front/DetalhesPedido.fxml"));
		Scene home_page_scene = new Scene(home_page_parent);

		home_page_parent.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});

		home_page_parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				stage.setX(event.getScreenX() - xOffset);
				stage.setY(event.getScreenY() - yOffset);
			}
		});

		stage.setScene(home_page_scene);
		stage.show();
	}
}
