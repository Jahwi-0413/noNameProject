package tableClass;

import javafx.collections.ObservableArray;

import java.io.*;

public class Tour implements Serializable
{
    private static final long serialVersionUID = 1L;
    private int tourId;          //관광지 id
    private String tourName;        //관광지 이름
    private String classification;  //분류
    private String fullAddress; //소재지
    private String city;        //지역
    private String townShip;    //읍면동
    private String phoneNumber; //전화번호

    public Tour(int id, String name, String classification, String address)
    {
        tourId=id; tourName = name;
        this.classification = classification;
        fullAddress=address;
    }
    public Tour(String name, String classification, String address)
    {
        tourName = name;
        this.classification = classification;
        fullAddress=address;
    }

    public int getTourId(){return tourId;}
    public String getTourName() {return tourName;}
    public String getClassification() {return classification;}
    public String getFullAddress() {return fullAddress;}
    public String getPhoneNumber() {return phoneNumber;}

    public void setTourId(int id) {tourId=id;}
    public void setTourName(String name) {tourName=name;}
    public void setClassification(String classification) {this.classification=classification;}
    public void setFullAddress(String address) {fullAddress=address;}
    public void setPhoneNumber(String phoneNum) {phoneNumber=phoneNum;}
}
