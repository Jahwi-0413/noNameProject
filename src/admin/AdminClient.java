package admin;

import communicate.PacketManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class AdminClient extends Application
{
    @FXML
    public Stage primaryStage, addWindow, deleteWindow;
    private static PacketManager packetMng;
    private static String host;

    @Override
    public void start(Stage stage) throws IOException
    {
        try
        {
            getConnectionServer();
            primaryStage=stage;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminMain.fxml"));
            Parent root=(Parent)loader.load();  //FXML의 요소들을 가져온다
            Scene scene=new Scene(root);    //root의 장면을 Scene으로 옮김
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void getConnectionServer() throws IOException, UnknownHostException
    {
        host= "localhost";      //local에서 test 해보기 위해서
        Socket socket = new Socket(host,10000);    //서버 접속
        PacketManager manager= new PacketManager(socket);
        AdminServerConnector.setPacketManager(manager);

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
