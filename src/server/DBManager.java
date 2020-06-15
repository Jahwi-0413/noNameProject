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
            String url = "jdbc:mysql://" + "192.168.25.43" + ":3306" + "/창의프로젝트" + "?characterEncoding=UTF-8&serverTimezone=UTC";
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
        Lodgment r = null;
        
        try
        {
            //TODO: DB 구축 이후에 적절한 SQL문 삽입
            pstmt = conn.prepareStatement("");
            rs = pstmt.executeQuery();

            while(rs.next())
            {
                // DB에는 데이터가 ID, 이름, 분류, 소재지, 지역, 읍면동 순서로 들어가있다고 가정
                r = new Lodgment(
                    rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)
                );
            }

            list.add(r);
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
            //TODO: DB 구축 이후에 적절한 SQL문 삽입
            pstmt = conn.prepareStatement("");
            rs = pstmt.executeQuery();

            while(rs.next())
            {
                // DB에는 데이터가 ID, 음식점 이름, 주메뉴, 소재지, 지역, 읍면동 순서로 들어가있다고 가정
                r = new Restaurant(
                    rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)
                );
            }

            list.add(r);
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
            //TODO: DB 구축 이후에 적절한 SQL문 삽입
            pstmt = conn.prepareStatement("");
            rs = pstmt.executeQuery();

            while(rs.next())
            {
                // DB에는 데이터가 ID, 음식점 이름, 분류, 소재지, 지역, 읍면동 순서로 들어가있다고 가정
                t = new Tour(
                    rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)
                );
            }

            list.add(t);
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
        Lodgment r = null;

        try
        {
            //TODO: DB 구축 이후에 적절한 SQL문 삽입
            pstmt = conn.prepareStatement("");
            rs = pstmt.executeQuery();

            while(rs.next())
            {
                // DB에는 데이터가 ID, 이름, 분류, 소재지, 지역, 읍면동 순서로 들어가있다고 가정
                r = new Lodgment(
                        rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)
                );
            }

            list.add(r);
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
            //TODO: DB 구축 이후에 적절한 SQL문 삽입
            pstmt = conn.prepareStatement("");
            rs = pstmt.executeQuery();

            while(rs.next())
            {
                // DB에는 데이터가 ID, 음식점 이름, 주메뉴, 소재지, 지역, 읍면동 순서로 들어가있다고 가정
                r = new Restaurant(
                        rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)
                );
            }

            list.add(r);
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
            //TODO: DB 구축 이후에 적절한 SQL문 삽입
            pstmt = conn.prepareStatement("");
            rs = pstmt.executeQuery();

            while(rs.next())
            {
                // DB에는 데이터가 ID, 음식점 이름, 분류, 소재지, 지역, 읍면동 순서로 들어가있다고 가정
                t = new Tour(
                        rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)
                );
            }

            list.add(t);
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
            //TODO: DB 구축 이후에 적절한 SQL문 삽입
            pstmt = conn.prepareStatement("");

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
            //TODO: DB 구축 이후에 적절한 SQL문 삽입
            pstmt = conn.prepareStatement("");

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
            //TODO: DB 구축 이후에 적절한 SQL문 삽입
            pstmt = conn.prepareStatement("");

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
            //TODO: DB 구축 이후에 적절한 SQL문 삽입
            pstmt = conn.prepareStatement("");

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
            //TODO: DB 구축 이후에 적절한 SQL문 삽입
            pstmt = conn.prepareStatement("");

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
            //TODO: DB 구축 이후에 적절한 SQL문 삽입
            pstmt = conn.prepareStatement("");

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