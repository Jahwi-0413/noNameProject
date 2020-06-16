package admin;

import communicate.Protocol;
import communicate.SerialManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tableClass.Lodgment;
import tableClass.Restaurant;
import tableClass.Tour;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static communicate.Protocol.*;


public class deletingController implements Initializable
{
    @FXML
    private Button deleteTourBtn, deleteRestrantBtn, deleteLodgmentBtn;     //관광지, 음식점, 숙박 버튼

    @FXML
    private TextField deleteTextField;  //검색할 text field
    //---------------------------------------------------------------------------------
    @FXML
    private TableView<Tour> tourTableView ;      //관광지 정보를 담는 테이블뷰
    @FXML
    private TableColumn<Tour,String> tourName;
    @FXML
    private TableColumn<Tour,String> tourClassification;
    @FXML
    private TableColumn<Tour,String> tourFullAddress;
    @FXML
    private TableColumn<Tour,String> tourPhoneNumber;
    //---------------------------------------------------------------------------------
    @FXML
    private TableView<Restaurant> restaurantTableView;  // 음식점 정보를 담는 테이블뷰
    @FXML
    private TableColumn<Restaurant,String> restaurantName;
    @FXML
    private TableColumn<Restaurant,String> mainMenu;
    @FXML
    private TableColumn<Restaurant,String> restaurantFullAddress;
    @FXML
    private TableColumn<Restaurant,String> restaurantPhoneNumber;
    //---------------------------------------------------------------------------------
    @FXML
    private TableView<Lodgment> lodgmentTableView;  //숙박 정보를 담는 테이블
    @FXML
    private TableColumn<Lodgment,String> lodgmentName;
    @FXML
    private TableColumn<Lodgment,String> lodgmentClassification;
    @FXML
    private TableColumn<Lodgment,String> lodgmentFullAddress;
    @FXML
    private TableColumn<Lodgment,String> lodgmentPhoneNumber;

    private Stage thisStage;
    private static int tourClicked=0, restaurantClicked=0,lodgmentClicked=0;  //어떤 버튼이 눌렸는지 확인하는 값 0이면 안눌림, 1이면 눌림

    @Override
    public void initialize(URL location, ResourceBundle bundle)
    {

    }
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
        if(keyWord.equals("")) //키워드를 입력한 경우
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
        Protocol pt = AdminServerConnector.getPacketManager().packetRead();
        if(AdminServerConnector.getPacketManager().packetTranslate())   //요청 성공인 경우
        {
            Object[] objList = SerialManager.toObjectArr(pt.getBody());
            if(tourClicked==1)
            {
                tourName.setCellValueFactory(new PropertyValueFactory<>("tourName"));       //사용 클래스의 멤버변수명이랑 같아야됨
                tourClassification.setCellValueFactory(new PropertyValueFactory<>("classification"));
                tourFullAddress.setCellValueFactory(new PropertyValueFactory<>("fullAddress"));
                tourPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
                Tour[] tourArray = (Tour[])objList;
                ArrayList<Tour> list = new ArrayList();
                for(int i = 0;i<tourArray.length; i++)
                {
                    list.add(tourArray[i]);
                }
                ObservableList<Tour> arraylist = FXCollections.observableArrayList(list);
                tourTableView.setItems(arraylist);
            }
            else if(restaurantClicked==1)
            {
                restaurantName.setCellValueFactory(new PropertyValueFactory<>("restaurantName"));
                mainMenu.setCellValueFactory(new PropertyValueFactory<>("menu"));
                restaurantFullAddress.setCellValueFactory(new PropertyValueFactory<>("fullAddress"));
                restaurantPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
                Restaurant[] restaurantArray = (Restaurant[])objList;
                ArrayList<Restaurant> reslist = new ArrayList();
                for(int i=0;i<restaurantArray.length;i++)
                {
                    reslist.add(restaurantArray[i]);

                }
                ObservableList<Restaurant> arraylist = FXCollections.observableArrayList(reslist);
                restaurantTableView.setItems(arraylist);
            }
            else if(lodgmentClicked==1)
            {
                lodgmentName.setCellValueFactory(new PropertyValueFactory<>("lodgmentName"));
                lodgmentClassification.setCellValueFactory(new PropertyValueFactory<>("classification"));
                lodgmentFullAddress.setCellValueFactory(new PropertyValueFactory<>("fullAddress"));
                lodgmentPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
                Lodgment[] lodgArray = (Lodgment[])objList;
                ArrayList<Lodgment> list = new ArrayList();
                for(int i=0;i<lodgArray.length;i++)
                {
                    list.add(lodgArray[i]);
                }
                ObservableList<Lodgment> arraylist = FXCollections.observableArrayList(list);
                lodgmentTableView.setItems(arraylist);
            }
        }
        else        //요청 실패인 경우
        {
            alertMessage("실패", "정보를 불러오지 못했습니다");
        }
    }
    //--------------------------------------------------------------------------------------------------------------------------
    //삭제 요청 관련련

    public void clickDelete() throws IOException
    {
        if(tourClicked==1)  //관광지 정보 삭제시
        {
            Tour tour = tourTableView.getSelectionModel().getSelectedItem();
            AdminServerConnector.getPacketManager().deleteRequest(TAG_TRAVEL, tour);
        }
        else if(restaurantClicked==1)   //음식점 정보 삭제시
        {
            Restaurant restaurant = restaurantTableView.getSelectionModel().getSelectedItem();
            AdminServerConnector.getPacketManager().deleteRequest(TAG_FOOD,restaurant);
        }
        else if(lodgmentClicked==1) // 숙박업소 정보 삭제시
        {
            Lodgment lodgment = lodgmentTableView.getSelectionModel().getSelectedItem();
            AdminServerConnector.getPacketManager().deleteRequest(TAG_HOTEL,lodgment);
        }
        AdminServerConnector.getPacketManager().packetRead(); //요청에 대한 응답을 받음
        if(AdminServerConnector.getPacketManager().packetTranslate())   //응답이 성공이면
        {
            alertMessage("성공","삭제 성공");
            if(tourClicked==1)
                settingWindow("tour");
            else if(restaurantClicked==1)
                settingWindow("restaurant");
            else if(lodgmentClicked==1)
                settingWindow("lodgment");

        }
        else
            alertMessage("실패", "삭제 실패");
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
}
