/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gomi.front.Controllers;

import br.com.gomi.shared.UsuarioAtual;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author jonyg
 */
public class PrincipalCController extends PadraoController {

    //O cliente solicita a coleta com esse método
    public void btnSolicitarColetaOnClick(ActionEvent event) throws IOException {
        NovaColetaController novaColeta = new NovaColetaController();
        novaColeta.start((Stage) ((Button) event.getSource()).getScene().getWindow());
    }

    //O cliente altera seus dados cadastrais com esse método
    public void btnAlterarDadosOnClick(ActionEvent event) throws IOException {
        //fazer
    }

    //O cliente visualiza o histórico de colétas 
    public void btnHistoricoOnClick(ActionEvent event) throws IOException {
        // fazer
    }

    //O cliente sai de seu usuario
    public void btnSairOnClick(ActionEvent event) throws IOException {
        UsuarioAtual.getInstancia().logoff();
        LoginController login = new LoginController();
        login.start((Stage) ((Button) event.getSource()).getScene().getWindow());
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/br/com/gomi/front/PrincipalC.fxml"));
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
