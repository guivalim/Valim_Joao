package org.example;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.awt.*;
import java.util.List;

public class ControllerTelaAlunoEspecificos {

    @FXML
    private MenuButton MenuButtonSemestre;

    @FXML
    private ToggleButton ToggleButonTodosTg;

    @FXML
    private VBox VBoxListaVersoes;

    @FXML
    private HBox HboxVersao; // modelo da TG (template invisível no FXML)

    @FXML
    private Label lblNomeAluno; // vamos adicionar esse no topo do FXML (onde aparece "Guilherme Valim...")

    // método para carregar as TGs de um aluno
    public void carregarTGs(int idAluno, String nomeAluno) {
        TGDAO dao = new TGDAO();
        List<TG> tgs = dao.listarTGsPorAluno(idAluno);

        // --- LOGS DE TESTE ---
        System.out.println("Aluno: " + nomeAluno);
        System.out.println("TGs encontradas: " + tgs.size());
        for (TG tg : tgs) {
            System.out.println(tg.getNomeArquivo() + " - " + tg.getDataEnvio() + " - " + tg.getImagemStatus());
        }

        // exibe o nome do aluno no topo
        if (lblNomeAluno != null) {
            lblNomeAluno.setText(nomeAluno);
        }

        VBoxListaVersoes.getChildren().clear();

        for (TG tg : tgs) {
            HBox novo = criarHBoxTG(tg);
            VBoxListaVersoes.getChildren().add(novo);
        }
    }

    // cria dinamicamente um HBox com os dados da TG
    private HBox criarHBoxTG(TG tg) {
        HBox modelo = HboxVersao;

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
        Label lblNome = new Label(tg.getNomeArquivo());
        javafx.scene.text.Font fonteNome = modeloNome.getFont();
        if (fonteNome != null) lblNome.setFont(fonteNome);
        lblNome.setPrefWidth(modeloNome.getPrefWidth());

        // Espaço flexível
        javafx.scene.layout.Region espaco = new Region();
        HBox.setHgrow(espaco, Priority.ALWAYS);

        // Tempo (novo Label) - cópia de fonte e prefWidth do label modelo (índice 2), mas pode ficar oculto
        Label modeloTempo = (Label) modelo.getChildren().get(2);
        Label lblTempo = new Label();
        Font fonteTempo = modeloTempo.getFont();
        if (fonteTempo != null) lblTempo.setFont(fonteTempo);
        lblTempo.setPrefWidth(modeloTempo.getPrefWidth());

        if (tg.getDataEnvio() != null && !tg.getDataEnvio().isEmpty()) {
            lblTempo.setText(tg.getDataEnvio());
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
            img.setImage(new Image(getClass().getResourceAsStream(tg.getImagemStatus())));
        } catch (Exception e) {
            // se não encontrar a imagem, deixa em branco (ou trate aqui)
        }

        // Monta HBox
        hbox.getChildren().addAll(lblNome, espaco, lblTempo, img);

        return hbox;
    }
}


