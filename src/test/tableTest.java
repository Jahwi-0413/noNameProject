package test;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tableClass.Tour;

import java.io.IOException;
import java.util.ArrayList;

public class tableTest extends Application
{
    @FXML
    private TableView tourTableView;
    @FXML
    private TableColumn<Tour,String> tourName;
    @FXML
    private TableColumn<?,?> tourPhoneNumber;




    public void start(Stage stage)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("delete_tour.fxml"));
            Parent root=(Parent)loader.load();  //FXML의 요소들을 가져온다
            Scene scene=new Scene(root);    //root의 장면을 Scene으로 옮김
            stage.setScene(scene);
            stage.show();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        launch();
    }
    public void clickDeleteSearch()
    {

        tourName.setCellValueFactory(new PropertyValueFactory<>("tourName"));
        tourPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("tourPhoneNumber"));
//        tourFullAddress.setCellValueFactory(new PropertyValueFactory<>("tourFullAddress"));
//        tourClassification.setCellValueFactory(new PropertyValueFactory<>("tourClassification"));

        ArrayList<Tour> arraylist = new ArrayList<Tour>();
        for(int i=0;i<20;i++)
        {
            arraylist.add(new Tour(i,"test","test","test"));
        }
        ObservableList<Tour> list = FXCollections.observableArrayList(arraylist);
        tourTableView.setItems(list);
    }

}
