package admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import tableClass.Lodgment;

import java.io.IOException;

public class addingWindowController
{
    @FXML
    private Button addLodgmentBtn,addTourBtn,addRestrantBtn;  //숙박, 관광지, 음식점 버튼
    @FXML
    private Button addInsertBtn;            //정보 입력
    @FXML
    private BorderPane addContentPane;
    @FXML
    private GridPane lodgmentGrid;

    @FXML       //숙박정보 임력 fxml 파일의 textField들
    private TextField lodgmentName,lodgmentClassification,lodgmentFullAddress, lodgmentCity, lodgmentTownShip,lodgmentPhoneNumber;

    private Stage thisStage;        //현재 윈도우를 저장하는 객체
    private TextField nameField, oneMoreField, fullAddressField, cityField, townShipField, phoneNumberField;      //oneMore는 각각 공통되지않는 1개를 뜻하고 FXML에서 요소를 안가져온 이유는 버튼 클릭시마다 화면이 새로 생성되기 때문
    private static int tourClicked, lodgmentClicked, restaurantClicked;    //어떤 버튼이 눌렸는지 확인, 0이면 안눌림 1이면 눌림 -> insert Btn 클릭시 값을 저장하려고

    public void clickAddTour() throws IOException    //관광지 버튼을 눌렀을때 새로운 창이 열리고 이전 창은 닫힘, pane 내용 바꾸는건 포기함 ㅋㅋ
    {
        tourClicked=1; lodgmentClicked=0; restaurantClicked=0;

        Stage stage=(Stage)addLodgmentBtn.getScene().getWindow();   //addLodgmentBtn을 포함한 stage
        thisStage=stage;
        stage.close();      //쭈모 샤따 닫아ㅏㅏㅏ

        Parent second=FXMLLoader.load(getClass().getResource("add_tour.fxml"));
        Scene scene=new Scene(second);
        thisStage.setScene(scene);
        thisStage.show();
    }
    public void clickAddRestrant() throws IOException //음식점 버튼을 눌렀을때
    {
        tourClicked=0; lodgmentClicked=0; restaurantClicked=1;

        Stage stage=(Stage)addLodgmentBtn.getScene().getWindow();   //addLodgmentBtn을 포함한 stage
        thisStage=stage;
        stage.close();

        Parent second=FXMLLoader.load(getClass().getResource("add_restaurant.fxml"));
        Scene scene=new Scene(second);
        thisStage.setScene(scene);
        thisStage.show();
    }
    public void clickAddLodg() throws IOException     // 숙박 버튼을 눌렀을 때
    {
        tourClicked=0;
        lodgmentClicked=1;
        restaurantClicked=0;

        Stage stage=(Stage)addLodgmentBtn.getScene().getWindow();   //addLodgmentBtn을 포함한 stage
        thisStage=stage;
        stage.close();

        Parent second=FXMLLoader.load(getClass().getResource("add_lodgment.fxml"));
        Scene scene=new Scene(second);
        thisStage.setScene(scene);
        thisStage.show();
    }
    /**/
    public void clickAddInsert()        //입력 완료 버튼을 눌렀을때
    {
        Lodgment lodgment;
        if(tourClicked==1)
        {
        }
        if(lodgmentClicked==1)
        {
            lodgment = makeLodgmentObject();
        }
        if(restaurantClicked==1)
        {
        }
        //패킷 전송
    }

    public  Lodgment makeLodgmentObject()
    {
        GridPane grid = lodgmentGrid;       //현재 화면의 View 객체와 연동이 됨!
        String name =lodgmentName.getText();
        String classification = lodgmentClassification.getText();
        String address = lodgmentFullAddress.getText();
        String city = lodgmentCity.getText();
        String townShip = lodgmentTownShip.getText();
        String phoneNum = lodgmentPhoneNumber.getText();

        Lodgment lodgment;

        if(phoneNum.equals(null))       //전화번호를 입력하지 않은 경우우
           lodgment = new Lodgment(name, classification, address, city, townShip);
        else
        {
            lodgment =new Lodgment(name,classification, address, city, townShip);
            lodgment.setPhoneNumber(phoneNum);
        }
        return lodgment;
    }
}
