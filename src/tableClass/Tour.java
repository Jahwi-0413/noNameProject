package tableClass;

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

    public Tour(int id, String name, String classification, String address, String city, String townShip)
    {
        tourId=id; tourName = name;
        this.classification = classification;
        fullAddress=address; this.city = city; this.townShip = townShip;
    }
    public Tour(String name, String classification, String address, String city, String townShip)
    {
        tourName = name;
        this.classification = classification;
        fullAddress=address; this.city = city; this.townShip = townShip;
    }

    public int getTourId(){return tourId;}
    public String getTourName() {return tourName;}
    public String getClassification() {return classification;}
    public String getFullAddress() {return fullAddress;}
    public String getCity() {return city;}
    public String getTownShip() {return townShip;}
    public String getPhoneNumber() {return phoneNumber;}

    public void setTourId(int id) {tourId=id;}
    public void setTourName(String name) {tourName=name;}
    public void setClassification(String classification) {this.classification=classification;}
    public void setFullAddress(String address) {fullAddress=address;}
    public void setCity(String city) {this.city=city;}
    public void setTownShip(String townShip) {this.townShip = townShip;}
    public void setPhoneNumber(String phoneNum) {phoneNumber=phoneNum;}

    // Object to Byte[]
    public byte[] toByteArray()
    {
        Tour obj = this;
        byte[] result = null;

        try
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            result = baos.toByteArray();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return result;
    }

    // Byte[] to Object
    public static Tour toObject(byte[] data)
    {
        Tour obj = null;
        try
        {
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(bais);
            obj = (Tour) ois.readObject();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return obj;
    }
}
