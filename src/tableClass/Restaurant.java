package tableClass;

public class Restaurant
{
    private static final long serialVersionUID = 2L;
    private int restaurantId;          //관광지 id
    private String restaurantName;        //관광지 이름
    private String classification;  //분류
    private String fullAddress; //소재지
    private String city;        //지역
    private String townShip;    //읍면동
    private String phoneNumber; //전화번호

    Restaurant(int id, String name, String classification, String address, String city, String townShip, String PhoneNumber)
    {
        restaurantId=id; restaurantName = name;
        this.classification = classification;
        fullAddress=address; this.city = city; this.townShip = townShip;
        this.phoneNumber = PhoneNumber;
    }
    Restaurant(int id, String name, String classification, String address, String city, String townShip)
    {
        restaurantId=id; restaurantName = name;
        this.classification = classification;
        fullAddress=address; this.city = city; this.townShip = townShip;
    }

    public int getRestaurantId(){return restaurantId;}
    public String getRestaurantName() {return restaurantName;}
    public String getClassification() {return classification;}
    public String getFullAddress() {return fullAddress;}
    public String getCity() {return city;}
    public String getTownShip() {return townShip;}
    public String getPhoneNumber() {return phoneNumber;}

    public void setTourId(int id) {restaurantId=id;}
    public void setTourName(String name) {restaurantName=name;}
    public void setClassification(String classification) {this.classification=classification;}
    public void setFullAddress(String address) {fullAddress=address;}
    public void setCity(String city) {this.city=city;}
    public void setTownShip(String townShip) {this.townShip = townShip;}
    public void setPhoneNumber(String phoneNum) {phoneNumber=phoneNum;}
}
