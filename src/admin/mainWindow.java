package admin;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class mainWindow extends Application
{
    @FXML
    public Stage primaryStage, addWindow, deleteWindow;
    @Override
    public void start(Stage stage) throws IOException
    {
        primaryStage=stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root=(Parent)loader.load();  //FXML의 요소들을 가져온다
        Scene scene=new Scene(root);    //root의 장면을 Scene으로 옮김
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void clickAdd(ActionEvent event)throws IOException
    {
        Stage stage = new Stage();
        addWindow=stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("add.fxml"));
        Parent root = (Parent)loader.load();
        Scene scene=new Scene(root);
        addWindow.setScene(scene);
        addWindow.show();
    }
    public void clickDelete(ActionEvent event) throws IOException
    {
        Stage stage = new Stage();
        deleteWindow = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("delete.fxml"));
        Parent root = (Parent)loader.load();
        Scene scene = new Scene(root);
        deleteWindow.setScene(scene);
        deleteWindow.show();
    }
    public static void main(String[] args)
    {
        launch();
    }

    public void closeStage()
    {
        addWindow.close();
    }
}
