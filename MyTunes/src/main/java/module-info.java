module dk.easv.mytunes {
    requires javafx.controls;
    requires javafx.fxml;

    exports dk.easv.mytunes.PL;
    opens dk.easv.mytunes.PL to javafx.fxml;
    exports dk.easv.mytunes;
    opens dk.easv.mytunes to javafx.fxml;
}