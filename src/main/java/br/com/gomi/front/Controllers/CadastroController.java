/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gomi.front.Controllers;

import br.com.gomi.business.Dados;
import br.com.gomi.business.Validacao;
import br.com.gomi.shared.ClienteViewModel;
import br.com.gomi.shared.MotoristaViewModel;
import br.com.gomi.shared.NaoAdmViewModel;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class CadastroController extends PadraoController {

    //variaveis dos campos 
    private static boolean ehCliente;
    @FXML
    TextField emailTextField;
    @FXML
    TextField nomeTextField;
    @FXML
    TextField telefoneTextField;
    @FXML
    TextField cpfTextField;
    @FXML
    TextField dataNascimentoTextField;
    @FXML
    PasswordField senhaPasswordField;
    @FXML
    PasswordField confirmacaoSenhaPasswordField;
    @FXML
    TextField cepTextField;
    @FXML
    TextField numeroTextField;
    @FXML
    TextField ruaTextField;
    @FXML
    TextField complementoTextField;
    @FXML
    TextField bairroTextField;
    @FXML
    TextField cidadeTextField;
    @FXML
    TextField tipoVeiculoTextField;
    @FXML
    TextField cnhTextField;
    @FXML
    TextField dataExpiracaoTextField;
    @FXML
    TextField cnhCategoriaTextField;
    @FXML
    TextField cargaSuportadaTextField;
    @FXML
    RadioButton clienteRadioButton;
    @FXML
    RadioButton motoristaRadioButton;
    @FXML
    Label cepLabel;
    @FXML
    Label numeroLabel;
    @FXML
    Label ruaLabel;
    @FXML
    Label complementoLabel;
    @FXML
    Label bairroLabel;
    @FXML
    Label cidadeLabel;
    @FXML
    Label tipoVeiculoLabel;
    @FXML
    Label numCnhLabel;
    @FXML
    Label dataExpLabel;
    @FXML
    Label cnhCategoriaLabel;
    @FXML
    Label cargaLabel;
    @FXML
    Button fotoCnhButton;
    @FXML
    ImageView fotoImg;
    @FXML
    StackPane imgStackPane;

    //verificar como colocar foto
    //verificar como colocar método de pagamento
    //verificar mascara (abaixo)
    //método para cadastrar usando as informações fornecidas pelo usuario
    public void btnCadastrarOnClick(ActionEvent event) throws IOException, SQLException, Exception {
        try {
            if (JOptionPane.showConfirmDialog(null, "Deseja cadastrar esse usuário?", "Mensagem de Cadastro", 0) == 1) {
                return;
            }

            char tipo = Validacao.validaCadastro(emailTextField.getText().trim().toLowerCase(), nomeTextField.getText().trim(), telefoneTextField.getText().trim(), cpfTextField.getText().trim(), dataNascimentoTextField.getText().trim(),
                    senhaPasswordField.getText().trim(), confirmacaoSenhaPasswordField.getText().trim(), ehCliente, cepTextField.getText().trim(), numeroTextField.getText().trim(), ruaTextField.getText().trim(),
                    bairroTextField.getText().trim(), cidadeTextField.getText().trim(), tipoVeiculoTextField.getText().trim(), cnhTextField.getText().trim(), dataExpiracaoTextField.getText().trim(),
                    cnhCategoriaTextField.getText().trim(), cargaSuportadaTextField.getText().trim());

            if (tipo == 'C') {
                ClienteViewModel cliente = new ClienteViewModel();
                cliente.setEmail(emailTextField.getText().trim().toLowerCase());
                cliente.setNome(nomeTextField.getText().trim());
                cliente.setTelefoneddd(Integer.parseInt(telefoneTextField.getText().trim().substring(1, 3)));
                cliente.setTelefone(Integer.parseInt(telefoneTextField.getText().trim().substring(4)));
                cliente.setCpf(cpfTextField.getText().trim());
                cliente.setData(LocalDate.parse(dataNascimentoTextField.getText().trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                cliente.setSenha(senhaPasswordField.getText().trim());
                cliente.setCep(cepTextField.getText().trim());
                cliente.setNumero(Integer.parseInt(numeroTextField.getText().trim()));
                cliente.setRua(ruaTextField.getText().trim());
                cliente.setComplemento(complementoTextField.getText().trim());
                cliente.setBairro(bairroTextField.getText().trim());
                cliente.setCidade(cidadeTextField.getText().trim());
                cliente.setIdCliente(Dados.insereCliente(cliente));
                cliente.setIdNaoAdm(Dados.insereNaoAdm(cliente));
                Dados.insereUsuario(cliente);
            } else if (tipo == 'M') {
                MotoristaViewModel motorista = new MotoristaViewModel();
                motorista.setEmail(emailTextField.getText().trim().toLowerCase());
                motorista.setNome(nomeTextField.getText().trim());
                motorista.setTelefoneddd(Integer.parseInt(telefoneTextField.getText().trim().substring(1, 3)));
                motorista.setTelefone(Integer.parseInt(telefoneTextField.getText().trim().substring(4)));
                motorista.setCpf(cpfTextField.getText().trim());
                motorista.setData(LocalDate.parse(dataNascimentoTextField.getText().trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                motorista.setSenha(senhaPasswordField.getText().trim());
                motorista.setTipoVeiculo(tipoVeiculoTextField.getText().trim());
                motorista.setCnh(cnhTextField.getText().trim());
                motorista.setDataExpiracao(LocalDate.parse(dataExpiracaoTextField.getText().trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                motorista.setCnhCategoria(cnhCategoriaTextField.getText().trim().charAt(0));
                motorista.setCargaSuportada(Integer.parseInt(cargaSuportadaTextField.getText().trim()));
                motorista.setIdMotorista(Dados.insereMotorista(motorista));
                motorista.setIdNaoAdm(Dados.insereNaoAdm(motorista));
                Dados.insereUsuario(motorista);
            } else if (tipo == 'X') {
                if (ehCliente) {
                    if (JOptionPane.showConfirmDialog(null, "Esse usuário Já está cadastrado como Motorista. Deseja Cadastrar-se como Cliente?", "Mensagem de Cadastro", 0) == 1) {
                        return;
                    }
                    ClienteViewModel cliente = new ClienteViewModel();
                    cliente.setCep(cepTextField.getText().trim());
                    cliente.setNumero(Integer.parseInt(numeroTextField.getText().trim()));
                    cliente.setRua(ruaTextField.getText().trim());
                    cliente.setComplemento(complementoTextField.getText().trim());
                    cliente.setBairro(bairroTextField.getText().trim());
                    cliente.setCidade(cidadeTextField.getText().trim());
                    cliente.setIdCliente(Dados.insereCliente(cliente));
                    NaoAdmViewModel na = Dados.recuperaNaoAdm(emailTextField.getText().trim().toLowerCase());
                    cliente.setIdNaoAdm(na.getIdNaoAdm());
                    cliente.setTelefone(na.getTelefone());
                    cliente.setTelefoneddd(na.getTelefoneddd());
                    cliente.setIdMotorista(na.getIdMotorista());
                    Dados.atualizaNaoAdm(cliente);

                } else {
                    if (JOptionPane.showConfirmDialog(null, "Esse usuário Já está cadastrado como Cliente. Deseja Cadastrar-se como Motorista?", "Mensagem de Cadastro", 0) == 1) {
                        return;
                    }
                    MotoristaViewModel motorista = new MotoristaViewModel();
                    motorista.setTipoVeiculo(tipoVeiculoTextField.getText().trim());
                    motorista.setCnh(cnhTextField.getText().trim());
                    motorista.setDataExpiracao(LocalDate.parse(dataExpiracaoTextField.getText().trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    motorista.setCnhCategoria(cnhCategoriaTextField.getText().trim().charAt(0));
                    motorista.setCargaSuportada(Integer.parseInt(cargaSuportadaTextField.getText().trim()));
                    motorista.setIdMotorista(Dados.insereMotorista(motorista));
                    NaoAdmViewModel na = Dados.recuperaNaoAdm(emailTextField.getText().trim().toLowerCase());
                    motorista.setIdNaoAdm(na.getIdNaoAdm());
                    motorista.setTelefone(na.getTelefone());
                    motorista.setTelefoneddd(na.getTelefoneddd());
                    motorista.setIdCliente(na.getIdCliente());
                    Dados.atualizaNaoAdm(motorista);
                }
            }
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!", "Mensagem de Cadastro", 1);
            btnVoltarOnClick(event);
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Voltar a tela de login
    public void btnVoltarOnClick(ActionEvent event) throws IOException, Exception {
        LoginController login = new LoginController();
        login.start((Stage) ((Button) event.getSource()).getScene().getWindow());
    }

    //Mudar para preencher dados exclusivos do cliente
    public void rdbClienteOnToggle(ActionEvent event) throws IOException {
        cepTextField.setVisible(true);
        numeroTextField.setVisible(true);
        ruaTextField.setVisible(true);
        complementoTextField.setVisible(true);
        bairroTextField.setVisible(true);
        cidadeTextField.setVisible(true);
        tipoVeiculoTextField.setVisible(false);
        cnhTextField.setVisible(false);
        dataExpiracaoTextField.setVisible(false);
        cnhCategoriaTextField.setVisible(false);
        cargaSuportadaTextField.setVisible(false);
        cepLabel.setVisible(true);
        numeroLabel.setVisible(true);
        ruaLabel.setVisible(true);
        complementoLabel.setVisible(true);
        bairroLabel.setVisible(true);
        cidadeLabel.setVisible(true);
        tipoVeiculoLabel.setVisible(false);
        numCnhLabel.setVisible(false);
        dataExpLabel.setVisible(false);
        cnhCategoriaLabel.setVisible(false);
        cargaLabel.setVisible(false);
        fotoCnhButton.setVisible(false);
        fotoImg.setVisible(false);
        imgStackPane.setVisible(false);
        ehCliente = true;
    }

    //mudar para preencher dados exclusivos do motorista
    public void rdbMotoristaOnToggle(ActionEvent event) throws IOException {
        cepTextField.setVisible(false);
        numeroTextField.setVisible(false);
        ruaTextField.setVisible(false);
        complementoTextField.setVisible(false);
        bairroTextField.setVisible(false);
        cidadeTextField.setVisible(false);
        tipoVeiculoTextField.setVisible(true);
        cnhTextField.setVisible(true);
        dataExpiracaoTextField.setVisible(true);
        cnhCategoriaTextField.setVisible(true);
        cargaSuportadaTextField.setVisible(true);
        cepLabel.setVisible(false);
        numeroLabel.setVisible(false);
        ruaLabel.setVisible(false);
        complementoLabel.setVisible(false);
        bairroLabel.setVisible(false);
        cidadeLabel.setVisible(false);
        tipoVeiculoLabel.setVisible(true);
        numCnhLabel.setVisible(true);
        dataExpLabel.setVisible(true);
        cnhCategoriaLabel.setVisible(true);
        cargaLabel.setVisible(true);
        fotoCnhButton.setVisible(true);
        fotoImg.setVisible(true);
        imgStackPane.setVisible(true);
        ehCliente = false;
    }

    //Carrega foto fornecida pelo usuario
    public void btnCarregaFotoUserOnClick(ActionEvent event) throws IOException {

    }

    public void emailTextKeyReleased(ActionEvent event) throws IOException {

    }

    @Override
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        cepTextField.setVisible(false);
        numeroTextField.setVisible(false);
        ruaTextField.setVisible(false);
        complementoTextField.setVisible(false);
        bairroTextField.setVisible(false);
        cidadeTextField.setVisible(false);
        tipoVeiculoTextField.setVisible(false);
        cnhTextField.setVisible(false);
        dataExpiracaoTextField.setVisible(false);
        cnhCategoriaTextField.setVisible(false);
        cargaSuportadaTextField.setVisible(false);
        cepLabel.setVisible(false);
        numeroLabel.setVisible(false);
        ruaLabel.setVisible(false);
        complementoLabel.setVisible(false);
        bairroLabel.setVisible(false);
        cidadeLabel.setVisible(false);
        tipoVeiculoLabel.setVisible(false);
        numCnhLabel.setVisible(false);
        dataExpLabel.setVisible(false);
        cnhCategoriaLabel.setVisible(false);
        cargaLabel.setVisible(false);
        fotoCnhButton.setVisible(false);
        fotoImg.setVisible(false);
        imgStackPane.setVisible(false);
    }

    //Tela onde a controller ira agir
    @Override
    public void start(Stage stage) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/br/com/gomi/front/Cadastro.fxml"));
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
