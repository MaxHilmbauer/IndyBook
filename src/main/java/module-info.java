module mh.easyindy {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.dongliu.requests;
    requires org.json;

    opens mh.indybook to javafx.fxml;
    exports mh.indybook;
    exports mh.indybook.gui.controller;
    opens mh.indybook.gui.controller to javafx.fxml;
    exports mh.indybook.gui.service;
    opens mh.indybook.gui.service to javafx.fxml;
}