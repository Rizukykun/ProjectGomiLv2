/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gomi.front.Controllers;

import br.com.gomi.business.Dados;
import br.com.gomi.business.Validacao;
import br.com.gomi.shared.SolicitacaoViewModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
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
 * @author Fábio
 */
public class MotoristaEncontradoInfoController extends PadraoController {
    //Variaveis em campo de texto referentes ao nome do motorista, o carro e tempo de chegada

    @FXML
    TextField txtNomeMotorista;
    @FXML
    TextField txtCarroMotorista;
    @FXML
    TextField txtTempoChegadaMotorista;
    @FXML
    Button btnConfirmar;

    public void btnCancelarOnClick(ActionEvent event) {
        //Cancelar
    }
    
    public void btnConfirmarOnClick(ActionEvent event) throws Exception{
            
        int idSolicitacao = Global.obtemInstancia().solicitacao.getId();
        SolicitacaoViewModel model = Dados.recuperaSolicitacao(idSolicitacao);
        if (!model.isColetado()){
            model.setColetado(true);
        }
        else{
            model.setAberto(false);
        }
        btnConfirmar.setDisable(true);
        Dados.atualizaSolicitacao(model);
        PrincipalCController coletado = new PrincipalCController();

        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                boolean ativo = true;
                while (ativo) {
                    SolicitacaoViewModel model = Dados.recuperaSolicitacao(idSolicitacao);
                    if (model.isColetado() && !model.isAberto()) {
                        ativo = false;
                    }
                    Thread.sleep(2000);
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try
                        {
                            ChangeScene(event, coletado);
                        } catch (IOException ex)
                        {
                            Logger.getLogger(AtenderSolicitacaoController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                return null;
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }
    
    //Apos o carregamento terminar, mudar tela.
    public void ChangeScene(ActionEvent event, PrincipalCController encontrado) throws IOException {
        encontrado.start((Stage) ((Button) event.getSource()).getScene().getWindow());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtNomeMotorista.setText(Global.obtemInstancia().motorista.getNome());
        txtCarroMotorista.setText(Global.obtemInstancia().motorista.getTipoVeiculo());
        txtTempoChegadaMotorista.setText(Validacao.getTempoChegada(Global.obtemInstancia().solicitacao.getOrigem(), Global.obtemInstancia().solicitacao.getCep()));
    }

    //Tela onde a controller ira agir
    @Override
    public void start(Stage stage) {
        try {
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("/br/com/gomi/front/MotoristaEncontradoInfo.fxml"));
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
        } catch (IOException ex) {
            Logger.getLogger(MotoristaEncontradoInfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
