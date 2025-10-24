package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.scene.layout.Priority;
import javafx.geometry.Insets;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ControllerTelaAlunos {

    @FXML
    private Button ButtonPesquisar;

    @FXML
    private HBox HboxAluno; // modelo (template presente no FXML)

    @FXML
    private TextField TextFieldNomePesquisa;

    @FXML
    private ToggleButton ToggleButonTodosAlunos;

    @FXML
    private ToggleButton ToggleButtonCorrigidos;

    @FXML
    private ToggleButton ToggleButtonNaoCorrigidos;

    @FXML
    private VBox VBoxListaAlunos;

    @FXML
    void PassarTela(MouseEvent event) {
        // ainda não usado
    }

    @FXML
    public void initialize() {
        AlunoDAO dao = new AlunoDAO();
        // Esta lista agora contém Alunos com (id, ra, idade, etc.)
        List<Aluno> alunos = dao.listarAlunos();

        // Limpa o VBox
        VBoxListaAlunos.getChildren().clear();

        for (Aluno aluno : alunos) {
            HBox novo = criarHBoxAluno(aluno);
            VBoxListaAlunos.getChildren().add(novo);
        }
    }

    /**
     * Cria programaticamente um HBox para o aluno, copiando apenas estilos/medidas do HboxAluno modelo.
     */
    private HBox criarHBoxAluno(Aluno aluno) {
        HBox modelo = HboxAluno;

        // Novo HBox
        HBox hbox = new HBox();
        hbox.setStyle(modelo.getStyle());
        hbox.setAlignment(modelo.getAlignment() == null ? Pos.CENTER_LEFT : modelo.getAlignment());
        hbox.setSpacing(modelo.getSpacing());
        hbox.setPadding(modelo.getPadding() == null ? new Insets(10, 20, 10, 20) : modelo.getPadding());
        hbox.setPrefHeight(modelo.getPrefHeight());
        hbox.setPrefWidth(modelo.getPrefWidth());

        // Nome (novo Label) - copia fonte e prefWidth do label modelo (índice 0)
        Label modeloNome = (Label) modelo.getChildren().get(0);

        // --- MUDANÇA 1: Usando getRa() ao invés de getNome() ---
        Label lblNome = new Label(aluno.getRa());

        Font fonteNome = modeloNome.getFont();
        if (fonteNome != null) lblNome.setFont(fonteNome);
        lblNome.setPrefWidth(modeloNome.getPrefWidth());

        // Espaço flexível
        Region espaco = new Region();
        HBox.setHgrow(espaco, Priority.ALWAYS);

        // --- MUDANÇA 2: REMOÇÃO DA LÓGICA DE TEMPO E IMAGEM ---
        // As colunas 'tempo_envio' e 'imagem' não existem mais na classe Aluno.
        // O código que criava 'lblTempo' e 'img' foi removido.

        // Monta HBox (agora só com o RA e o espaço)
        hbox.getChildren().addAll(lblNome, espaco);

        hbox.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaAlunoEspecifico.fxml"));
                Parent root = loader.load();

                // pega o controller da nova tela
                ControllerTelaAlunoEspecificos controllerTG = loader.getController();

                // --- MUDANÇA 3: Passando getRa() ao invés de getNome() ---
                // passa id e RA do aluno para carregar TGs
                controllerTG.carregarTGs(aluno.getId(), aluno.getRa());

                // abre a nova janela
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("TGs do Aluno: " + aluno.getRa()); // Título atualizado
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return hbox;
    }
}