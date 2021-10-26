/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gomi.front.Controllers;

import br.com.gomi.business.Dados;
import br.com.gomi.business.Validacao;
import br.com.gomi.shared.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class NovaColetaController extends PadraoController {

    @FXML
    TextArea descricaoTextArea; //Descrição
    @FXML
    CheckBox checkLixoReciclaveis; //Checkbox lixo reciclavel
    @FXML
    CheckBox checkEletrodomesticos; //Checkbox Eletrodomésticos
    @FXML
    CheckBox checkMoveis; //Checkbox móveis
    @FXML
    CheckBox checkEntulho; //Checkbox entulho
    @FXML
    CheckBox checkLixoEletronico; //Checkbox lixo eletronico

    //Cada checkbox identifica o lixo que vai ser recolhido
    public void btnContinuarOnClick(ActionEvent event) throws IOException, SQLException {
        try {
            Validacao.validaSolicitacao(descricaoTextArea.getText());

            SolicitacaoViewModel solicitacao = new SolicitacaoViewModel();
            ClienteViewModel cliente = (ClienteViewModel) UsuarioAtual.getInstancia().getUsuario();
            solicitacao.setIdCliente(cliente.getIdCliente());
            solicitacao.setCep(cliente.getCep());
            solicitacao.setNumero(cliente.getNumero());
            solicitacao.setDescricao(descricaoTextArea.getText());
            solicitacao.setDataSolicitacao(LocalDateTime.now());
            solicitacao.setAberto(true);
            //adicionar categorias na lista
            solicitacao.setId(Dados.insereSolicitacao(solicitacao));
            BuscandoMotoristaController buscandoMotorista = new BuscandoMotoristaController();
            Global.obtemInstancia().solicitacao = solicitacao;
            buscandoMotorista.start((Stage) ((Button) event.getSource()).getScene().getWindow());
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Voltar
    public void btnVoltarOnClick(ActionEvent event) throws IOException {
        PrincipalCController principal = new PrincipalCController();
        principal.start((Stage) ((Button) event.getSource()).getScene().getWindow());
    }

    //Foto coleta
    public void btnFotoColetaOnClick(ActionEvent event) throws IOException {

    }

    //Tela onde a controller vai agir
    @Override
    public void start(Stage stage) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/br/com/gomi/front/NovaColeta.fxml"));
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
