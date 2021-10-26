/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gomi.front.Controllers;

import br.com.gomi.business.Dados;
import br.com.gomi.business.RandomPasswordGenerator;
import br.com.gomi.business.Validacao;
import java.io.IOException;
import java.sql.SQLException;
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
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class RedefinicaoSenhaController extends PadraoController {

    @FXML
    private TextField emailTextField;

    //Após clicar no botão, o método envia um email automatico com a solicitação de recuperação de senha
    public void btnRecuperarOnClick(ActionEvent event) throws IOException, SQLException, Exception {
        //Chamar o banco para verificar existência de E-mail
        if (Validacao.usuarioExiste(emailTextField.getText())) {
            String senha = RandomPasswordGenerator.geraSenha();
            String nome = Dados.atualizaSenhaUsuario(emailTextField.getText(), senha);
            RandomPasswordGenerator.enviarEmail(emailTextField.getText(), senha, nome);
        }
        JOptionPane.showMessageDialog(null, "Um email de solicitação foi enviado", "Troca de Senha", JOptionPane.INFORMATION_MESSAGE);
        LoginController login = new LoginController();
        login.start((Stage) ((Button) event.getSource()).getScene().getWindow());
    }

    //Voltar a tela login
    public void btnVoltarOnClick(ActionEvent event) throws IOException {
        LoginController login = new LoginController();
        login.start((Stage) ((Button) event.getSource()).getScene().getWindow());
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/br/com/gomi/front/RedefinicaoSenha.fxml"));
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
