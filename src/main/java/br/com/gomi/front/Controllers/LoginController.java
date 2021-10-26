/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gomi.front.Controllers;

import br.com.gomi.business.*;
import br.com.gomi.shared.UsuarioAtual;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javafx.scene.control.Hyperlink;

/**
 *
 * @author Administrador
 */
public class LoginController extends PadraoController {

    @FXML
    private TextField loginTextField;
    @FXML
    private PasswordField senhaPasswordField;

    //Login do cliente conforme as iinformações inseridas corretamente
    public void btnLoginOnClick(ActionEvent event) throws Exception {
        try {
            if (Validacao.validaLogin(loginTextField.getText().trim().toLowerCase(), senhaPasswordField.getText().trim())) {
                char tipo = Validacao.validaTipoLogin(loginTextField.getText().trim().toLowerCase());
                if (tipo == 'X') {
                    String[] escolhas
                            = {
                                "Cliente", "Motorista"
                            };
                    int indice = JOptionPane.showOptionDialog(null, "Com qual tipo de usuário deseja fazer login?", "Usuário Cruzado", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, escolhas, null);
                    switch (indice) {
                        case 0:
                            tipo = 'C';
                            break;
                        case 1:
                            tipo = 'M';
                            break;
                        default:
                            return;
                    }

                }
                switch (tipo) {
                    case 'C': {
                        UsuarioAtual.getInstancia().setUsuario(Dados.recuperaCliente(loginTextField.getText().trim().toLowerCase()));
                        PrincipalCController principal = new PrincipalCController();
                        if(event.getSource() instanceof Button)
                            principal.start((Stage) ((Button) event.getSource()).getScene().getWindow());
                        else if (event.getSource() instanceof PasswordField)
                            principal.start((Stage) ((PasswordField) event.getSource()).getScene().getWindow());
                        else
                            principal.start((Stage) ((TextField) event.getSource()).getScene().getWindow());
                        break;
                    }
                    case 'M': {
                        UsuarioAtual.getInstancia().setUsuario(Dados.recuperaMotorista(loginTextField.getText().trim().toLowerCase()));
                        PrincipalMController principal = new PrincipalMController();
                        if(event.getSource() instanceof Button)
                            principal.start((Stage) ((Button) event.getSource()).getScene().getWindow());
                        else if (event.getSource() instanceof PasswordField)
                            principal.start((Stage) ((PasswordField) event.getSource()).getScene().getWindow());
                        else
                            principal.start((Stage) ((TextField) event.getSource()).getScene().getWindow());
                        break;
                    }
                    //Tela de administrador
                    default:
                        break;
                }
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro de Login", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //Se não possuir usuario, o botão leva para a tela de cadastro
    public void btnCadastrarOnClick(ActionEvent event) throws IOException {
        CadastroController cadastrar = new CadastroController();
        cadastrar.start((Stage) ((Button) event.getSource()).getScene().getWindow());
    }

    //Direcionado a tela caso o usuario esquecer a senha
    public void lnkEsqueceuSenhaOnClick(ActionEvent event) throws IOException {
        RedefinicaoSenhaController redefinicao = new RedefinicaoSenhaController();
        redefinicao.start((Stage) ((Hyperlink) event.getSource()).getScene().getWindow());
    }

    //Pagina sobre o programa e os integrantes do grupo
    public void imgSobreOnClick(ActionEvent event) throws IOException {

        SobreController sobre = new SobreController();
        sobre.start((Stage) ((Button) event.getSource()).getScene().getWindow());
    }

    //Tela onde a controller ira agir
    @Override
    public void start(Stage stage) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/br/com/gomi/front/Login.fxml"));
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
