package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class ControllerFeedback {
    @FXML
    private TextArea ta_professorTG_Feedback;

    @FXML
    private Button btn_professorTG_finalizar;


    @FXML
    public void initialize() {
        System.out.println("Controlador da Tela de Feedback inicializado.");
        ta_professorTG_Feedback.setPromptText("Por favor, insira seu feedback aqui...");
    }


    @FXML
    void finalizar(ActionEvent event) {
        String feedbackTexto = ta_professorTG_Feedback.getText();

        if (feedbackTexto.isEmpty()) {
            System.out.println("O feedback está vazio.");
        } else {
            System.out.println("Feedback submetido:");
            System.out.println(feedbackTexto);

            ta_professorTG_Feedback.clear();
        }
    }
}