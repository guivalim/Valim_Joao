
package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
        List<Aluno> alunos = dao.listarAlunos();

        // Limpa o VBox (mantemos o HboxAluno somente como modelo em memória)
        VBoxListaAlunos.getChildren().clear();

        for (Aluno aluno : alunos) {
            HBox novo = criarHBoxAluno(aluno);
            VBoxListaAlunos.getChildren().add(novo);
        }
    }

    /**
     * Cria programaticamente um HBox para o aluno, copiando apenas estilos/medidas do HboxAluno modelo.
     * Isso evita reaproveitar os mesmos nós (labels/imageviews) e evita recarregar FXML várias vezes.
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
        Label lblNome = new Label(aluno.getNome());
        Font fonteNome = modeloNome.getFont();
        if (fonteNome != null) lblNome.setFont(fonteNome);
        lblNome.setPrefWidth(modeloNome.getPrefWidth());

        // Espaço flexível
        Region espaco = new Region();
        HBox.setHgrow(espaco, Priority.ALWAYS);

        // Tempo (novo Label) - cópia de fonte e prefWidth do label modelo (índice 2), mas pode ficar oculto
        Label modeloTempo = (Label) modelo.getChildren().get(2);
        Label lblTempo = new Label();
        Font fonteTempo = modeloTempo.getFont();
        if (fonteTempo != null) lblTempo.setFont(fonteTempo);
        lblTempo.setPrefWidth(modeloTempo.getPrefWidth());

        if (aluno.getTempoEnvio() != null && !aluno.getTempoEnvio().isEmpty()) {
            lblTempo.setText(aluno.getTempoEnvio());
            lblTempo.setVisible(true);
            lblTempo.setManaged(true);
        } else {
            lblTempo.setText("");
            lblTempo.setVisible(false);
            lblTempo.setManaged(false); // não ocupa espaço
        }

        // Imagem de status (nova ImageView) - cópia de dimensões aproximadas do modelo (índice 3)
        ImageView modeloImg = (ImageView) modelo.getChildren().get(3);
        ImageView img = new ImageView();
        img.setFitHeight(modeloImg.getFitHeight() > 0 ? modeloImg.getFitHeight() : 61);
        img.setFitWidth(modeloImg.getFitWidth() > 0 ? modeloImg.getFitWidth() : 76);
        img.setPreserveRatio(true);
        // Carrega imagem (coloque os arquivos em resources no mesmo pacote)
        try {
            img.setImage(new Image(getClass().getResourceAsStream(aluno.getImagem())));
        } catch (Exception e) {
            // se não encontrar a imagem, deixa em branco (ou trate aqui)
        }

        // Monta HBox
        hbox.getChildren().addAll(lblNome, espaco, lblTempo, img);

        return hbox;
    }

}
