package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import tableClass.*;
import java.util.*;

public class DBManager
{
    // DB 통신 객체는 하나로 유지하기 위해 싱글톤 패턴 적용
    private static DBManager db = new DBManager();
    private Connection conn;

    private final String ID = "user";
    private final String PASSWORD = "12345";

    private DBManager()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://" + "116.127.156.46" + ":3306" + "/창의프로젝트" + "?characterEncoding=UTF-8&serverTimezone=UTC";
            conn = DriverManager.getConnection(url, ID, PASSWORD);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static DBManager getInstance() {return db;}

    public ArrayList<Lodgment> getLodgmentList()
    {
        ArrayList<Lodgment> list = new ArrayList<Lodgment>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Lodgment l = null;
        
        try
        {
            pstmt = conn.prepareStatement("select * from 창의프로젝트.lodgment");
            rs = pstmt.executeQuery();

            while(rs.next())
            {
                // DB에는 데이터가 ID, 이름, 등급, 주소, 전화번호 순서로 들어감
                l = new Lodgment(
                    rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)
                );

                if(rs.getString(5) != null)
                    l.setPhoneNumber(rs.getString(5));

                list.add(l);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                pstmt.close();
                rs.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        return list;
    }

    public ArrayList<Restaurant> getRestaurantList()
    {
        ArrayList<Restaurant> list = new ArrayList<Restaurant>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Restaurant r = null;

        try
        {
            pstmt = conn.prepareStatement("select  * from 창의프로젝트.restaurant");
            rs = pstmt.executeQuery();

            while(rs.next())
            {
                // DB에는 데이터가 ID, 음식점 이름, 주메뉴, 주소, 전화번호 순서로 들어감
                r = new Restaurant(
                    rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)
                );

                if(rs.getString(5) != null)
                    r.setPhoneNumber(rs.getString(5));

                list.add(r);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                pstmt.close();
                rs.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        return list;
    }

    public ArrayList<Tour> getTourList()
    {
        ArrayList<Tour> list = new ArrayList<Tour>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Tour t = null;

        try
        {
            pstmt = conn.prepareStatement("select * from 창의프로젝트.tour");
            rs = pstmt.executeQuery();

            while(rs.next())
            {
                // DB에는 데이터가 ID, 이름, 분류, 주소, 전화번호 순서로 들어감
                t = new Tour(
                    rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)
                );
                if(rs.getString(5) != null)
                    t.setPhoneNumber(rs.getString(5));
                list.add(t);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                pstmt.close();
                rs.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        return list;
    }

    // 키워드 검색용 함수 오버로딩
    public ArrayList<Lodgment> getLodgmentList(String keyword)
    {
        ArrayList<Lodgment> list = new ArrayList<Lodgment>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Lodgment l = null;

        try
        {
            pstmt = conn.prepareStatement("call keyword_search('lodgment', ?)");
            pstmt.setString(1, keyword);
            rs = pstmt.executeQuery();

            while(rs.next())
            {
                // DB에는 데이터가 ID, 이름, 등급, 주소, 전화번호 순서로 들어감
                l = new Lodgment(
                        rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)
                );

                if(rs.getString(5) != null)
                    l.setPhoneNumber(rs.getString(5));

                list.add(l);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                pstmt.close();
                rs.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        return list;
    }

    // 키워드 검색용 함수 오버로딩
    public ArrayList<Restaurant> getRestaurantList(String keyword)
    {
        ArrayList<Restaurant> list = new ArrayList<Restaurant>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Restaurant r = null;

        try
        {
            pstmt = conn.prepareStatement("call keyword_search('restaurant', ?)");
            pstmt.setString(1, keyword);
            rs = pstmt.executeQuery();

            while(rs.next())
            {
                // DB에는 데이터가 ID, 음식점 이름, 주메뉴, 주소, 전화번호 순서로 들어감
                r = new Restaurant(
                        rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)
                );

                if(rs.getString(5) != null)
                    r.setPhoneNumber(rs.getString(5));

                list.add(r);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                pstmt.close();
                rs.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        return list;
    }

    // 키워드 검색용 함수 오버로딩
    public ArrayList<Tour> getTourList(String keyword)
    {
        ArrayList<Tour> list = new ArrayList<Tour>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Tour t = null;

        try
        {
            pstmt = conn.prepareStatement("call keyword_search('tour', ?)");
            pstmt.setString(1, keyword);
            rs = pstmt.executeQuery();

            while(rs.next())
            {
                // DB에는 데이터가 ID, 이름, 분류, 주소, 전화번호 순서로 들어감
                t = new Tour(
                        rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)
                );
                if(rs.getString(5) != null)
                    t.setPhoneNumber(rs.getString(5));
                list.add(t);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                pstmt.close();
                rs.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        return list;
    }

    public boolean addTour(Tour tour)
    {
        PreparedStatement pstmt = null;

        try
        {
            pstmt = conn.prepareStatement("insert into 창의프로젝트.tour " +
                    "(`tourName`, `classification`, `fullAddress`, `phoneNumber`) " +
                    "values (?, ?, ?, ?)");
            pstmt.setString(1, tour.getTourName());
            pstmt.setString(2, tour.getClassification());
            pstmt.setString(3, tour.getFullAddress());
            pstmt.setString(4, tour.getPhoneNumber());

            pstmt.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            try
            {
                pstmt.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return true;
    }

    public boolean addLodgment(Lodgment lodg)
    {
        PreparedStatement pstmt = null;

        try
        {
            pstmt = conn.prepareStatement("insert into 창의프로젝트.tour " +
                    "(`lodgmentName`, `classification`, `fullAddress`, `phoneNumber`) " +
                    "values (?, ?, ?, ?)");
            pstmt.setString(1, lodg.getLodgmentName());
            pstmt.setString(2, lodg.getClassification());
            pstmt.setString(3, lodg.getFullAddress());
            pstmt.setString(4, lodg.getPhoneNumber());

            pstmt.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            try
            {
                pstmt.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean addRestaurant(Restaurant res)
    {
        PreparedStatement pstmt = null;

        try
        {
            pstmt = conn.prepareStatement("insert into 창의프로젝트.restaurant " +
                    "(`restaurantID`, `restaurantName`, `mainMenu`, `fullAddress`, `phoneNumber`) " +
                    "values (?, ?, ?, ?)");
            pstmt.setString(1, res.getRestaurantName());
            pstmt.setString(2, res.getMenu());
            pstmt.setString(3, res.getFullAddress());
            pstmt.setString(4, res.getPhoneNumber());

            pstmt.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            try
            {
                pstmt.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean deleteTour(int id)
    {
        PreparedStatement pstmt = null;

        try
        {
            pstmt = conn.prepareStatement("delete from 창의프로젝트.tour where tourID = ?");
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            try
            {
                pstmt.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return true;
    }

    public boolean deleteLodgment(int id)
    {
        PreparedStatement pstmt = null;

        try
        {
            pstmt = conn.prepareStatement("delete from 창의프로젝트.lodgment where lodgmentID = ?");
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            try
            {
                pstmt.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return true;
    }

    public boolean deleteRestaurant(int id)
    {
        PreparedStatement pstmt = null;

        try
        {
            pstmt = conn.prepareStatement("delete from 창의프로젝트.restaurant where restaurantID = ?");
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            try
            {
                pstmt.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return true;
    }
}