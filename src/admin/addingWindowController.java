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
import tableClass.Restaurant;

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

    @FXML       //음식점 임력 fxml 파일의 textField들
    private TextField restauranttName,restaurantMenu,restaurantFullAddress, restaurantCity, restaurantTownShip,restaurantPhoneNumber;

    @FXML       //숙박정보 임력 fxml 파일의 textField들
    private TextField lodgmentName,lodgmentClassification,lodgmentFullAddress, lodgmentCity, lodgmentTownShip,lodgmentPhoneNumber;



    private Stage thisStage;        //현재 윈도우를 저장하는 객체
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
        Restaurant restaurant;
        if(tourClicked==1)
        {
        }
        if(lodgmentClicked==1)
        {
            lodgment = makeLodgmentObject();
        }
        if(restaurantClicked==1)
        {
            restaurant = makeRestaurant();
        }
        //패킷 전송
    }

    public Restaurant makeRestaurant()
    {
        String name =restauranttName.getText();
        String menu= restaurantMenu.getText();
        String address = restaurantFullAddress.getText();
        String city = restaurantCity.getText();
        String townShip = restaurantTownShip.getText();
        String phoneNum = restaurantPhoneNumber.getText();

        Restaurant restaurant;

        if(phoneNum.equals(null))       //전화번호를 입력하지 않은 경우우
            restaurant = new Restaurant(name, menu, address, city, townShip);
        else
        {
            restaurant=new Restaurant(name,menu, address, city, townShip);
            restaurant.setPhoneNumber(phoneNum);
        }
        return restaurant;
    }
    public Lodgment makeLodgmentObject()
    {
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
