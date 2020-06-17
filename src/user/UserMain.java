package user;

import admin.AdminServerConnector;
import communicate.PacketManager;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class UserMain extends Application
{
    @FXML
    public Stage primaryStage, addWindow, deleteWindow;
    private static PacketManager packetMng;
    private static String host;
    @Override
    public void start(Stage stage)
    {
        try
        {
            getConnectionServer();
            primaryStage=stage;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserMain.fxml"));
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

}
