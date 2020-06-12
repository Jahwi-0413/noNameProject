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
    private TableView<Lodgment> lodgmentTableView;  //숙박 정보를 담는 테이블뷰

    private Stage thisStage;
    private int tourClicked=0, restaurantClicked=0,lodgmentClicked=0;  //어떤 버튼이 눌렸는지 확인하는 값 0이면 안눌림, 1이면 눌림

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
    public void clickDeleteSearch()     //검색 버튼을 눌렀을 때
    {
        String keyWord = deleteTextField.getText(); //검색어를 들고온다
        if(deleteGumiCheck.isSelected() && deleteDaeguCheck.isSelected()==false)    //구미에 대한 정보를 검색하는 경우
        {
            //db매니저에 (아직 구현은 안했지만) 일을 맡긴다
            //db 매니저로부터 테이블에 넣을 값들을 가져온다
        }
        else if(deleteDaeguCheck.isSelected() && deleteGumiCheck.isSelected()==false)
        {
            //db매니저에 (아직 구현은 안했지만) 일을 맡긴다
            //db 매니저로부터 테이블에 넣을 값들을 가져온다

        }
        else if(deleteGumiCheck.isSelected()==false && deleteDaeguCheck.isSelected()==false)        //둘다 선택 안된 경우
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("error");
            alert.setHeaderText("경고");
            alert.setContentText("체크 박스를 선택해 주세요");
            alert.showAndWait();
        }
        else if(deleteGumiCheck.isSelected() && deleteDaeguCheck.isSelected())        //둘다 선택된 경우
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("error");
            alert.setHeaderText("경고");
            alert.setContentText("체크 박스를 하나만 선택해 주세요");
            alert.showAndWait();
        }
    }
    public void clickDelete()
    {

    }

}
