package admin;

import communicate.Protocol;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tableClass.Lodgment;
import tableClass.Restaurant;
import tableClass.Tour;
import java.io.IOException;

import static communicate.Protocol.*;

public class addingController
{
    @FXML
    private Button addLodgmentBtn,addTourBtn,addRestrantBtn;  //숙박, 관광지, 음식점 버튼
    @FXML   //관광지 임력 fxml 파일의 textField들
    private TextField tourName, tourClassification, tourFullAddress, tourCity, tourTownShip,tourPhoneNumber;
    @FXML       //음식점 임력 fxml 파일의 textField들
    private TextField restaurantName,restaurantMenu,restaurantFullAddress, restaurantCity, restaurantTownShip,restaurantPhoneNumber;
    @FXML       //숙박정보 임력 fxml 파일의 textField들
    private TextField lodgmentName,lodgmentClassification,lodgmentFullAddress, lodgmentCity, lodgmentTownShip,lodgmentPhoneNumber;
    private Stage thisStage;        //현재 윈도우를 저장하는 객체
    private static int tourClicked, lodgmentClicked, restaurantClicked;    //어떤 버튼이 눌렸는지 확인, 0이면 안눌림 1이면 눌림 -> insert Btn 클릭시 값을 저장하려고

    public void clickAddTour() throws IOException    //관광지 버튼을 눌렀을때 새로운 창이 열리고 이전 창은 닫힘
    {
        tourClicked=1; lodgmentClicked=0; restaurantClicked=0;

        Stage stage=(Stage)addLodgmentBtn.getScene().getWindow();   //addLodgmentBtn을 포함한 stage
        thisStage=stage;
        stage.close();

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
        tourClicked=0; lodgmentClicked=1; restaurantClicked=0;

        Stage stage=(Stage)addLodgmentBtn.getScene().getWindow();   //addLodgmentBtn을 포함한 stage
        thisStage=stage;
        stage.close();
        Parent second=FXMLLoader.load(getClass().getResource("add_lodgment.fxml"));
        Scene scene=new Scene(second);
        thisStage.setScene(scene);
        thisStage.show();
    }

    public void clickAddInsert()        //입력 완료 버튼을 눌렀을때
    {
        Lodgment lodgment;
        Restaurant restaurant;
        Tour tour;
        if(tourClicked==1)
        {
            tour = makeTour();
            AdminServerConnector.getPacketManager().insertRequest(TAG_TRAVEL, tour); //요청 보냄
        }
        if(lodgmentClicked==1)
        {
            lodgment = makeLodgmentObject();
            AdminServerConnector.getPacketManager().insertRequest(TAG_HOTEL, lodgment);
        }
        if(restaurantClicked==1)
        {
            restaurant = makeRestaurant();
            AdminServerConnector.getPacketManager().insertRequest(TAG_FOOD, restaurant);
        }

        AdminServerConnector.getPacketManager().packetRead(); //요청에 대한 응답을 받음
        if(AdminServerConnector.getPacketManager().packetTranslate())   //응답이 성공이면
        {
            alertMessage("성공","정보 입력 성공");
        }
        else
            alertMessage("실패", "정보 입력 실패");
    }

    public void alertMessage(String result, String msg) //패킷 내용에 따른 경고?창 생성
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("result alert");
        if(result.equals("성공"))
        {
            alert.setHeaderText("결과");
            alert.setContentText(msg);
        }
        else
        {
            alert.setHeaderText("실패");
            alert.setContentText("요청 처리 실패");
        }
        alert.showAndWait();

    }
    public Tour makeTour()
    {
        String name =tourName.getText();
        String classification = tourClassification.getText();
        String address = tourFullAddress.getText();
        String phoneNumber = tourPhoneNumber.getText();

        Tour tour;
        if(phoneNumber.equals(""))       //전화번호를 입력하지 않은 경우
        {
            tour = new Tour(name, classification, address);
        }
        else
            {
                tour = new Tour(name, classification, address);
                tour.setPhoneNumber(phoneNumber);
            }
        return tour;
    }
    public Restaurant makeRestaurant()
    {
        String name =restaurantName.getText();
        String menu= restaurantMenu.getText();
        String address = restaurantFullAddress.getText();
        String phoneNum = restaurantPhoneNumber.getText();

        Restaurant restaurant;

        if(phoneNum.equals(""))       //전화번호를 입력하지 않은 경우
            restaurant = new Restaurant(name, menu, address);
        else
        {
            restaurant=new Restaurant(name,menu, address);
            restaurant.setPhoneNumber(phoneNum);
        }
        return restaurant;
    }
    public Lodgment makeLodgmentObject()
    {
        String name =lodgmentName.getText();
        String classification = lodgmentClassification.getText();
        String address = lodgmentFullAddress.getText();
        String phoneNum = lodgmentPhoneNumber.getText();

        Lodgment lodgment;

        if(phoneNum.equals(""))       //전화번호를 입력하지 않은 경우
           lodgment = new Lodgment(name, classification, address);
        else
        {
            lodgment =new Lodgment(name,classification, address);
            lodgment.setPhoneNumber(phoneNum);
        }
        return lodgment;
    }
}
