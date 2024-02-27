package pl.gornik.guessnumber;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button ExitBtn;

    @FXML
    private TextField numberArea;

    @FXML
    private Label infoLabel;

    @FXML
    private Button reloadBtn;

    @FXML
    private Label numberOfTries;

    public int randomNumber;
    int i = 1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        randomNumber = (int) (Math.random() * (100 - 1 + 1) + 1);
        reloadBtn.setDisable(true);
        ExitBtn.setOnAction(event -> Platform.exit());
        reloadBtn.setOnAction(event -> {
            randomNumber = (int) (Math.random() * (100 - 1 + 1) + 1);
            infoLabel.setText("");
            numberArea.clear();
            reloadBtn.setDisable(true);
            numberArea.setEditable(true);
            numberOfTries.setText("");
            numberArea.requestFocus();
        });
    }

    public void checkNumber() {
        boolean isNumber = isNumeric(numberArea.getText());

        if(isNumber) {
            if(Integer.parseInt(numberArea.getText()) < 1 || Integer.parseInt(numberArea.getText()) > 100 ) {
                infoLabel.setText("Liczba jest poza zakresem 1-100");
                infoLabel.setLayoutX(40);
                numberArea.clear();
            } else {
                if(Integer.parseInt(numberArea.getText()) == randomNumber) {
                    infoLabel.setText("Wygrałeś, zgadłeś liczbę");
                    infoLabel.setLayoutX(83);
                    numberOfTries.setText("Zgadłeś tą liczbę za: " + i);
                    reloadBtn.setDisable(false);
                    numberArea.setEditable(false);
                } else if(Integer.parseInt(numberArea.getText()) < randomNumber) {
                    infoLabel.setText("Liczba szukana jest większa od twojej");
                    infoLabel.setLayoutX(40);
                    numberOfTries.setLayoutX(40);
                    numberOfTries.setText("Jest to twoja  " + i + " próba.");
                    numberArea.clear();
                    i++;
                } else if(Integer.parseInt(numberArea.getText()) > randomNumber) {
                    infoLabel.setText("Liczba szukana jest mniejsza od twojej");
                    infoLabel.setLayoutX(40);
                    numberOfTries.setText("Jest to twoja  " + i + " próba.");
                    numberOfTries.setLayoutX(40);
                    numberArea.clear();
                    i++;
                }
            }
        } else {
            infoLabel.setText("Możesz wpisywać wyłącznie liczby!!");
            infoLabel.setLayoutX(50);
            numberArea.clear();
        }

    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
