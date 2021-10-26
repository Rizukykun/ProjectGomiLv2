/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gomi.front.Controllers;

import br.com.gomi.business.Dados;
import br.com.gomi.shared.SolicitacaoViewModel;
import br.com.gomi.shared.UsuarioAtual;
import java.io.IOException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class PrincipalMController extends PadraoController {

    @FXML
    CheckBox disponivelRadioButton;

    public void disponivelRadioButtonOnToggle(ActionEvent event) throws Exception {
        if (disponivelRadioButton.isSelected()) {
            List<SolicitacaoViewModel> solicitacoes = Dados.recuperaSolicitacoes();
            if (solicitacoes.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Não há coletas disponiveis", "Erro de Busca", JOptionPane.INFORMATION_MESSAGE);
                disponivelRadioButton.setSelected(false);
            } else {
                SolicitacaoViewModel solicitacao = solicitacoes.get(0);
                solicitacao.setOrigem(JOptionPane.showInputDialog("Digite seu endereço atual:", "Endereço").replace(' ', '+'));
                Global.obtemInstancia().solicitacao = solicitacao;
                DetalhesPedidoController detalhes = new DetalhesPedidoController();
                detalhes.start((Stage) ((CheckBox) event.getSource()).getScene().getWindow());
            }
        } else {
            disponivelRadioButton.setSelected(false);
        }
    }

    //Tela para alterar dados cadastrais do motorista
    public void btnAlterarDadosOnClick(ActionEvent event) {

    }

    //Tela para mostrar o histórico de coletas feitas pelo motorista
    public void btnHistoricoOnClick(ActionEvent event) {

    }

    //Botão para sair do usuario
    public void btnSairOnClick(ActionEvent event) throws IOException {
        UsuarioAtual.getInstancia().logoff();
        LoginController login = new LoginController();
        login.start((Stage) ((Button) event.getSource()).getScene().getWindow());
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/br/com/gomi/front/PrincipalM.fxml"));
        Scene scene = new Scene(parent);

        parent.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });

        stage.setScene(scene);
        stage.show();
    }
}
