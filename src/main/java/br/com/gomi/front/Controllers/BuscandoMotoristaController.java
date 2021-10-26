/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gomi.front.Controllers;

import br.com.gomi.business.Dados;
import br.com.gomi.shared.SolicitacaoViewModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Administrador
 */
public class BuscandoMotoristaController extends PadraoController {

    @FXML
    Label labelBuscaMotorista;
    @FXML
    ImageView loadingGIF;
    @FXML
    Button btnBuscar;

    //Tela carregando enquanto motorista é procurado
    public void btnBuscarOnClick(ActionEvent event) throws IOException {
        btnBuscar.setVisible(false);
        labelBuscaMotorista.setVisible(true);
        loadingGIF.setVisible(true);
        int idSolicitacao = Global.obtemInstancia().solicitacao.getId();
        MotoristaEncontradoInfoController encontrado = new MotoristaEncontradoInfoController();

        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                boolean ativo = true;
                while (ativo) {
                    SolicitacaoViewModel model = Dados.recuperaSolicitacao(idSolicitacao);
                    if (model.getIdMotorista() != null) {
                        Global.obtemInstancia().motorista = Dados.recuperaMotorista(model.getIdMotorista());
                        Global.obtemInstancia().solicitacao = model;
                        ativo = false;
                    }
                    Thread.sleep(2000);
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ChangeScene(event, encontrado);
                    }
                });
                return null;
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }

    //Botão voltar
    public void btnVoltarOnClick(ActionEvent event) throws Exception {
        NovaColetaController novaColeta = new NovaColetaController();
        novaColeta.start((Stage) ((Button) event.getSource()).getScene().getWindow());
        SolicitacaoViewModel solicitacao = Global.obtemInstancia().solicitacao;
        Global.obtemInstancia().solicitacao = null;
        solicitacao.setAberto(false);
        Dados.atualizaSolicitacao(solicitacao);
    }

    //Apos o carregamento terminar, mudar tela.
    public void ChangeScene(ActionEvent event, MotoristaEncontradoInfoController encontrado) {
        encontrado.start((Stage) ((Button) event.getSource()).getScene().getWindow());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelBuscaMotorista.setVisible(false);
        loadingGIF.setVisible(false);
    }

    //Tela onde a controller ira agir
    @Override
    public void start(Stage stage) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/br/com/gomi/front/BuscandoMotorista.fxml"));
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
