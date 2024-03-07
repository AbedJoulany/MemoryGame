module com.example.memorycardgameood {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens ood.memorycardgame to javafx.fxml;
    exports ood.memorycardgame;
    exports ood.memorycardgame.PlayerPack;
    opens ood.memorycardgame.PlayerPack to javafx.fxml;
}