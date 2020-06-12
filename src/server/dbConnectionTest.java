//package server;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class dbConnectionTest
//{
//    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//    public static final String URL = "jdbc:mysql://" + "192.168.25.43" + ":3306" + "/창의프로젝트" + "?characterEncoding=UTF-8&serverTimezone=UTC";
//    private Connection conn;
//
//    public dbConnectionTest(String id, String pw)
//    {
//        try
//        {
//            Class.forName(JDBC_DRIVER);
//            conn = DriverManager.getConnection(URL, id, pw);
//        }
//         catch (SQLException | ClassNotFoundException e)
//         {
//             System.out.println("연결실패");
//             e.printStackTrace();
//         }
//        System.out.println("연결성공!");
//    }
//
//    public static void main(String[] args)
//    {
//        dbConnectionTest test = new dbConnectionTest("user","12345");
//    }
//}
