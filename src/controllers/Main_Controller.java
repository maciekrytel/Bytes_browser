package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.util.List;

import aplikacja.App;

public class Main_Controller {

    private App app;

    @FXML
    private StackPane mainStackPane;

    @FXML
    public void initialize() throws Exception{
        this.app = new App();
        loadMenuScreen();
    }


    public void loadMenuScreen() throws Exception{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/menu.fxml"));
        AnchorPane anchorpane = loader.load();

        Menu_Controller menu_controller = loader.getController();
        menu_controller.setMain_Controller(this);
        setScreen(anchorpane);
    }

    public void setScreen(AnchorPane anchorpane) {
        this.mainStackPane.getChildren().clear();
        this.mainStackPane.getChildren().add(anchorpane);
    }

    public void change(byte [] data2, byte [] data3) throws Exception{
        this.app.compareFiles(data2, data3);
    }

    public List<File> uploadList(List<File> f) {
        return this.app.fileList = f;
    }

    public String getStatus() {
        return this.app.getStat();
    }
}
