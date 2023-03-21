module mh.easyindy {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.dongliu.requests;
    requires org.json;

    opens mh.easyindy to javafx.fxml;
    exports mh.easyindy;
    exports mh.easyindy.gui.controller;
    opens mh.easyindy.gui.controller to javafx.fxml;
    exports mh.easyindy.gui.service;
    opens mh.easyindy.gui.service to javafx.fxml;
}