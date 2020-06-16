package admin;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tableClass.Lodgment;
import tableClass.Restaurant;
import tableClass.Tour;

import java.io.IOException;

import static communicate.Protocol.*;


public class deletingController
{
    @FXML
    private Button deleteTourBtn, deleteRestrantBtn, deleteLodgmentBtn;     //관광지, 음식점, 숙박 버튼
    @FXML
    private Button deleteSearchBtn; //검색 버튼
    @FXML
    private Button deleteBtn;       //삭제 버튼
    @FXML
    private TextField deleteTextField;  //검색할 text field
    @FXML
    private CheckBox deleteGumiCheck, deleteDaeguCheck; //구미, 대구 체크박스
    @FXML
    private TableView<Tour> tourTableView;      //관광지 정보를 담는 테이블뷰
    @FXML
    private TableView<Restaurant> restaurantTableView;  // 음식점 정보를 담는 테이블뷰
    @FXML
    private TableView<Lodgment> lodgmentTableView;  //숙박 정보를 담는 테이블


    private Stage thisStage;
    private int tourClicked=0, restaurantClicked=0,lodgmentClicked=0;  //어떤 버튼이 눌렸는지 확인하는 값 0이면 안눌림, 1이면 눌림

    //--------------------------------------------------------------------------------------------------------------------------
    //화면 셋팅 관련

    public void clickDeleteTour() throws IOException       //관광점 버튼을 눌렀을 때 화면 나오게 하기
    {
       settingWindow("tour");
    }
    public void clickDeleteRestrant() throws IOException      //음식점 버튼을 눌렀을 때 화면 내오게 하기
    {
        settingWindow("restaurant");
    }
    public void clickDeleteLodg() throws IOException        //숙박점 버튼을 눌렀을 때 화면 나오게 하기
    {
        settingWindow("lodgment");
    }

    public void settingWindow(String clickedButton) throws IOException      //버튼 눌렀을 때 화면 설정하는거 함수로 따로 뺌
    {
        Parent second;

        switch(clickedButton)       //버튼 클릭의 경우에 따라 달라지는 설정을 뺌
        {
            case "tour":
                tourClicked=1; restaurantClicked=0;lodgmentClicked=0;
                second=FXMLLoader.load(getClass().getResource("delete_tour.fxml"));
                break;
            case "restaurant":
                tourClicked=0; restaurantClicked=1; lodgmentClicked=0;
                second=FXMLLoader.load(getClass().getResource("delete_restaurant.fxml"));
                break;
            case "lodgment":
                tourClicked=0; restaurantClicked=0; lodgmentClicked=1;
                second=FXMLLoader.load(getClass().getResource("delete_lodgment.fxml"));
                break;
            default:
                second=null;
        }
        Stage stage = (Stage)deleteTourBtn.getScene().getWindow();
        thisStage = stage;
        stage.close();
        Scene scene = new Scene(second);
        thisStage.setScene(scene);
        thisStage.show();
    }

    //--------------------------------------------------------------------------------------------------------------------------
    //검색 관련

    public void clickDeleteSearch()     //검색 버튼을 눌렀을 때
    {
        String keyWord = deleteTextField.getText(); //검색어를 들고온다
        if(keyWord.equals(null)==false) //키워드를 입력한 경우
        {
            if(tourClicked==1)
            {
                AdminServerConnector.getPacketManager().searchRequest(PW_ADMIN , TAG_TRAVEL,keyWord);
            }
            else if(restaurantClicked==1)
            {
                AdminServerConnector.getPacketManager().searchRequest(PW_ADMIN ,TAG_FOOD , keyWord);
            }
            else if(lodgmentClicked==1)
            {
                AdminServerConnector.getPacketManager().searchRequest(PW_ADMIN ,TAG_HOTEL,keyWord);
            }
        }
        else
        {
            if(tourClicked==1)
            {
                AdminServerConnector.getPacketManager().searchRequest(PW_ADMIN, TAG_TRAVEL);
            }
            else if(restaurantClicked==1)
            {
                AdminServerConnector.getPacketManager().searchRequest(PW_ADMIN , TAG_FOOD);
            }
            else if(lodgmentClicked==1)
            {
                AdminServerConnector.getPacketManager().searchRequest(PW_ADMIN ,TAG_HOTEL);
            }
        }
    }

    //--------------------------------------------------------------------------------------------------------------------------
    //삭제 요청 관련련

    public void clickDelete()
    {
        if(tourClicked==1)  //관광지 정보 삭제시
        {
            //선택된거 들고와서
            //서버로 삭제 요청을 보낸다

        }
        else if(restaurantClicked==1)   //음식점 정보 삭제시
        {

        }
        else if(lodgmentClicked==1) // 숙박업소 정보 삭제시
        {
        }
    }

}
