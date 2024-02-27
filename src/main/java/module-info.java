module pl.gornik.guessnumber {
    requires javafx.controls;
    requires javafx.fxml;


    opens pl.gornik.guessnumber to javafx.fxml;
    exports pl.gornik.guessnumber;
}