package tableClass;

import javafx.collections.ObservableArray;

import java.io.*;

public class Restaurant implements Serializable
{
    private static final long serialVersionUID = 2L;
    private int restaurantId;          //음식점 id
    private String restaurantName;        //음식점 이름
    private String menu;        //주메뉴
    private String fullAddress; //소재지
    private String phoneNumber; //전화번호

    public Restaurant(int id, String name, String menu, String address)
    {
        restaurantId=id; restaurantName = name;
        this.menu=menu;
        fullAddress=address;
    }

    public Restaurant(String name, String menu, String address)
    {
        restaurantName = name;
        this.menu=menu;
        fullAddress=address;
    }

    public int getRestaurantId(){return restaurantId;}
    public String getRestaurantName() {return restaurantName;}
    public String getMenu() {return menu;}
    public String getFullAddress() {return fullAddress;}
    public String getPhoneNumber() {return phoneNumber;}

    public void setTourId(int id) {restaurantId=id;}
    public void setTourName(String name) {restaurantName=name;}
    public void setMenu(String menu) {this.menu=menu;}
    public void setFullAddress(String address) {fullAddress=address;}
    public void setPhoneNumber(String phoneNum) {phoneNumber=phoneNum;}
}
