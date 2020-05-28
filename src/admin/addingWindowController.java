package admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class addingWindowController
{
    @FXML
    private Button addLodgmentBtn,addTourBtn,addRestrantBtn;  //숙박, 관광지, 음식점 버튼
    @FXML
    private Button addInsertBtn;            //정보 입력
    @FXML
    private BorderPane addContentPane;

    private int tourClicked=0, lodgmentClicked=0, restaurantClicked=0;    //어떤 버튼이 눌렸는지 확인, 0이면 안눌림 1이면 눌림 -> insert Btn 클릭시 값을 저장하려고

    public void clickAddTour() throws IOException    //관광지 버튼을 눌렀을때 새로운 창이 열리고 이전 창은 닫힘, pane 내용 바꾸는건 포기함 ㅋㅋ
    {
        tourClicked=1; lodgmentClicked=0; restaurantClicked=0;

        Stage newStage= new Stage();
        Stage stage=(Stage)addLodgmentBtn.getScene().getWindow();   //addLodgmentBtn을 포함한 stage
        stage.close();      //쭈모 샤따 닫아ㅏㅏㅏ

        Parent second=FXMLLoader.load(getClass().getResource("add_tour.fxml"));
        Scene scene=new Scene(second);
        stage.setScene(scene);
        stage.show();
    }
    public void clickAddRestrant() throws IOException //음식점 버튼을 눌렀을때
    {
        tourClicked=0; lodgmentClicked=0; restaurantClicked=1;
        Stage newStage= new Stage();
        Stage stage=(Stage)addLodgmentBtn.getScene().getWindow();
        stage.close();

        Parent second=FXMLLoader.load(getClass().getResource("add_restaurant.fxml"));
        Scene scene=new Scene(second);
        stage.setScene(scene);
        stage.show();
    }
    public void clickAddLodg() throws IOException     // 숙박 버튼을 눌렀을 때
    {
        tourClicked=1; lodgmentClicked=1; restaurantClicked=0;

        Stage newStage= new Stage();
        Stage stage=(Stage)addLodgmentBtn.getScene().getWindow();
        stage.close();

        Parent second=FXMLLoader.load(getClass().getResource("add_lodgment.fxml"));
        Scene scene=new Scene(second);
        stage.setScene(scene);
        stage.show();
    }
    public void clickAddInsert()        //입력 완료 버튼을 눌렀을때
    {
        if(tourClicked==1)
        {}
        else if(lodgmentClicked==1)
        {}
        else if(restaurantClicked==1)
        {


        }

    }
}
