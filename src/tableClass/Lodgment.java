package tableClass;

import javafx.collections.ObservableArray;

import java.io.*;

public class Lodgment implements Serializable//, ObservableArray<Lodgment>    //숙박시설
{
    private static final long serialVersionUID = 3L;
    private int lodgmentId;      //ai id
    private String lodgmentName;    //숙박시설 이름
    private String classification;  //분류
    private String fullAddress; //소재지
    private String city;        //지역
    private String townShip;    //읍면동
    private String phoneNumber; //전화번호

    public Lodgment(int id, String name, String classification, String address)
    {
        lodgmentId=id; lodgmentName = name;
        this.classification = classification;
        fullAddress=address;
    }
    public Lodgment(String name, String classification, String address)
    {
        lodgmentName = name;
        this.classification = classification;
        fullAddress=address;
    }


    public int getLodgmentId(){return lodgmentId;}
    public String getLodgmentName() {return lodgmentName;}
    public String getClassification() {return classification;}
    public String getFullAddress() {return fullAddress;}
    public String getPhoneNumber() {return phoneNumber;}

    public void setLodgmentId(int id) {lodgmentId=id;}
    public void setLdogmentName(String name) {lodgmentName=name;}
    public void setClassification(String classification) {this.classification=classification;}
    public void setFullAddress(String address) {fullAddress=address;}
    public void setPhoneNumber(String phoneNum) {phoneNumber=phoneNum;}
}
