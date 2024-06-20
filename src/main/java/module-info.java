open module fr.esgi.javalousvegous {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires annotations;

    //opens fr.esgi.lousvegous to javafx.fxml;
    exports fr.esgi.lousvegous;
    exports fr.esgi.lousvegous.symbol;
    //opens fr.esgi.lousvegous.symbol to javafx.fxml;
    //opens assets.textures;
}