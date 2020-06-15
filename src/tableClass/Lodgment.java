package tableClass;

import java.io.*;

public class Lodgment implements Serializable    //숙박시설
{
    private static final long serialVersionUID = 3L;
    private int lodgmentId;      //ai id
    private String lodgmentName;    //숙박시설 이름
    private String classification;  //분류
    private String fullAddress; //소재지
    private String city;        //지역
    private String townShip;    //읍면동
    private String phoneNumber; //전화번호

    public Lodgment(int id, String name, String classification, String address, String city, String townShip)
    {
        lodgmentId=id; lodgmentName = name;
        this.classification = classification;
        fullAddress=address; this.city = city; this.townShip = townShip;
    }
    public Lodgment(String name, String classification, String address, String city, String townShip)
    {
        lodgmentName = name;
        this.classification = classification;
        fullAddress=address; this.city = city; this.townShip = townShip;
    }


    public int getLodgmentId(){return lodgmentId;}
    public String getLodgmentName() {return lodgmentName;}
    public String getClassification() {return classification;}
    public String getFullAddress() {return fullAddress;}
    public String getCity() {return city;}
    public String getTownShip() {return townShip;}
    public String getPhoneNumber() {return phoneNumber;}

    public void setLodgmentId(int id) {lodgmentId=id;}
    public void setLdogmentName(String name) {lodgmentName=name;}
    public void setClassification(String classification) {this.classification=classification;}
    public void setFullAddress(String address) {fullAddress=address;}
    public void setCity(String city) {this.city=city;}
    public void setTownShip(String townShip) {this.townShip = townShip;}
    public void setPhoneNumber(String phoneNum) {phoneNumber=phoneNum;}

    // Object to Byte[]
    public byte[] toByteArray()
    {
        Lodgment obj = this;
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
    public static Lodgment toObject(byte[] data)
    {
        Lodgment obj = null;
        try
        {
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(bais);
            obj = (Lodgment) ois.readObject();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return obj;
    }
}
