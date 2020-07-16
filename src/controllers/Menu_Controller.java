package controllers;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class Menu_Controller {

    private Main_Controller main_controller;

    @FXML
    private ListView listView;

    @FXML
    private Label errorStatus;

    @FXML
    private TextField firstByteSeries;

    @FXML
    private TextField secondByteSeries;

    @FXML
    private TextField fileExtension;


    @FXML
    public void button_chooseFile(ActionEvent actionEvent) throws Exception{
        System.out.println("Wybiera pliki");
        errorStatus.setText("Wybiera pliki");
        FileChooser fileChooser = new FileChooser();
        if (fileExtension.getText().isEmpty()) {
            fileChooser.getExtensionFilters().setAll();
        }
        else
        {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(fileExtension.getText() + " (*." + fileExtension.getText() + ")", "*." + fileExtension.getText()));

        }
        List<File> f = fileChooser.showOpenMultipleDialog(null);

        main_controller.uploadList(f);

        if (f != null){
            for (int i=0; i< f.size(); i++)
            {
                listView.getItems().add(f.get(i).getName());
                errorStatus.setText("Wybrano pliki");
            }
        }
        else{
            System.out.println("Nie wybrano pliku");
            errorStatus.setText("Nie wybrano pliku");
        }
    }

    @FXML
    public void button_replace (ActionEvent actionEvent) throws Exception{

        String clientFirstByteSeries = firstByteSeries.getText();
        byte [] readFirst = clientFirstByteSeries.getBytes();
        String clientSecondByteSeries = secondByteSeries.getText();
        byte [] readSecond = clientSecondByteSeries.getBytes();
        main_controller.change(readFirst, readSecond);
        errorStatus.setText(main_controller.getStatus());
    }

    @FXML
    public void button_delete (ActionEvent actionEvent) throws Exception{
        listView.getItems().clear();
        errorStatus.setText("Odznaczyłeś wybrane pliki");
    }


    @FXML
    public void button_exit (ActionEvent actionEvent) throws Exception{
        System.exit(0);
    }

    public void setMain_Controller(Main_Controller main_controller) {
        this.main_controller = main_controller;
        this.errorStatus.setText(main_controller.getStatus());
    }
}
