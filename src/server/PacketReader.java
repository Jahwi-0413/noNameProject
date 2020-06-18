package server;

import communicate.Protocol;
import java.util.*;

import communicate.SerialManager;
import tableClass.*;

// 패킷을 분석해서 적절한 작업으로 연결해주는 클래스
public class PacketReader {
    private static DBManager db = DBManager.getInstance();

    // 패킷을 읽어 적절한 작업 처리, 이후 전송 해야할 result 패킷을 반환
    public static byte[] read(byte[] packet)
    {
        boolean result = false;
        Protocol protocol = new Protocol();
        protocol.setPacket(packet);

        switch(packet[0])
        {
            case Protocol.PW_USER:          // 권한이 사용자일때
                switch(packet[2])
                {
                    case Protocol.REQ_LOOK:     // 사용자가 조회요청
                        switch(packet[3])
                        {
                            case Protocol.TAG_TRAVEL:   // 사용자의 관광지 조회
                                ArrayList<Tour> tourList = null;

                                if(protocol.getBodyLength() == 0)   // 키워드가 없는 경우 전체 데이터 전송
                                    tourList = db.getTourList();
                                else                                // 키워드가 있는 경우 해당 키워드로 검색 후 해당하는 데이터만 전송
                                {
                                    String keyword = (String) SerialManager.toObject(protocol.getBody());
                                    tourList = db.getTourList(keyword);
                                }

                                if(!tourList.isEmpty())     // 여행지 객체 배열을 전송
                                {
                                    Tour[] tourArray = tourList.toArray(new Tour[tourList.size()]);
                                    protocol = new Protocol(Protocol.PW_USER, Protocol.TYPE_SUCCESS, Protocol.PT_NULL, Protocol.PT_NULL);
                                    protocol.setBody(SerialManager.toByteArray(tourArray));
                                }
                                else                        // 검색 데이터가 없거나, DB 연결에 실패한 경우 FAIL 메시지 전송
                                    protocol = new Protocol(Protocol.PW_USER, Protocol.TYPE_FAIL, Protocol.PT_NULL, Protocol.PT_NULL);
                                return protocol.getPacket();
                            case Protocol.TAG_FOOD:     // 사용자의 식당 조회
                                ArrayList<Restaurant> resList = null;

                                if(protocol.getBodyLength() == 0)   // 키워드가 없는 경우 전체 데이터 전송
                                    resList = db.getRestaurantList();
                                else                                // 키워드가 있는 경우 해당 키워드로 검색 후 해당하는 데이터만 전송
                                {
                                    String keyword = (String) SerialManager.toObject(protocol.getBody());
                                    resList = db.getRestaurantList(keyword);
                                }

                                if(!resList.isEmpty())      // 식당 객체 배열을 전송
                                {
                                    Restaurant[] resArray = resList.toArray(new Restaurant[resList.size()]);
                                    protocol = new Protocol(Protocol.PW_USER, Protocol.TYPE_SUCCESS, Protocol.PT_NULL, Protocol.PT_NULL);
                                    protocol.setBody(SerialManager.toByteArray(resArray));
                                }
                                else                        // 검색 데이터가 없거나, DB 연결에 실패한 경우 FAIL 메시지 전송
                                    protocol = new Protocol(Protocol.PW_USER, Protocol.TYPE_FAIL, Protocol.PT_NULL, Protocol.PT_NULL);
                                return protocol.getPacket();
                            case Protocol.TAG_HOTEL:    // 사용자의 숙소 조회
                                ArrayList<Lodgment> lodgList = null;

                                if(protocol.getBodyLength() == 0)   // 키워드가 없는 경우 전체 데이터 전송
                                    lodgList = db.getLodgmentList();
                                else                                // 키워드가 있는 경우 해당 키워드로 검색 후 해당하는 데이터만 전송
                                {
                                    String keyword = (String) SerialManager.toObject(protocol.getBody());
                                    lodgList = db.getLodgmentList(keyword);
                                }

                                if(!lodgList.isEmpty())     // 숙소 객체 배열을 전송
                                {
                                    Lodgment[] lodgArray = lodgList.toArray(new Lodgment[lodgList.size()]);
                                    protocol = new Protocol(Protocol.PW_USER, Protocol.TYPE_SUCCESS, Protocol.PT_NULL, Protocol.PT_NULL);
                                    protocol.setBody(SerialManager.toByteArray(lodgArray));
                                }
                                else                        // 검색 데이터가 없거나, DB 연결에 실패한 경우 FAIL 메시지 전송
                                    protocol = new Protocol(Protocol.PW_USER, Protocol.TYPE_FAIL, Protocol.PT_NULL, Protocol.PT_NULL);
                                return protocol.getPacket();
                        }
                        break;
                    // 사용자는 조회 이외의 작업 요청할 수 없으니 나머지 동작은 없음
                }
                break;
            case Protocol.PW_ADMIN:       // 권한이 관리자일때
                switch(packet[2])
                {
                    case Protocol.REQ_LOOK:     // 관리자가 조회요청
                        switch(packet[3])
                        {
                            case Protocol.TAG_TRAVEL:   // 관리자의 관광지 조회
                                ArrayList<Tour> tourList = null;

                                if(protocol.getBodyLength() == 0)   // 키워드가 없는 경우 전체 데이터 전송
                                    tourList = db.getTourList();
                                else                                // 키워드가 있는 경우 해당 키워드로 검색 후 해당하는 데이터만 전송
                                {
                                    String keyword = (String) SerialManager.toObject(protocol.getBody());
                                    tourList = db.getTourList(keyword);
                                }

                                if(!tourList.isEmpty())     // 여행지 객체 배열을 전송
                                {
                                    Tour[] tourArray = tourList.toArray(new Tour[tourList.size()]);
                                    protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_SUCCESS, Protocol.PT_NULL, Protocol.PT_NULL);
                                    protocol.setBody(SerialManager.toByteArray(tourArray));
                                }
                                else
                                    protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_FAIL, Protocol.PT_NULL, Protocol.PT_NULL);
                                return protocol.getPacket();
                            case Protocol.TAG_FOOD:     // 관리자의 식당 조회
                                ArrayList<Restaurant> resList = null;

                                if(protocol.getBodyLength() == 0)   // 키워드가 없는 경우 전체 데이터 전송
                                    resList = db.getRestaurantList();
                                else                                // 키워드가 있는 경우 해당 키워드로 검색 후 해당하는 데이터만 전송
                                {
                                    String keyword = (String) SerialManager.toObject(protocol.getBody());
                                    resList = db.getRestaurantList(keyword);
                                }

                                if(!resList.isEmpty())      // 식당 객체 배열을 전송
                                {
                                    Restaurant[] resArray = resList.toArray(new Restaurant[resList.size()]);
                                    protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_SUCCESS, Protocol.PT_NULL, Protocol.PT_NULL);
                                    protocol.setBody(SerialManager.toByteArray(resArray));
                                }
                                else
                                    protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_FAIL, Protocol.PT_NULL, Protocol.PT_NULL);
                                return protocol.getPacket();
                            case Protocol.TAG_HOTEL:    // 관리자의 숙소 조회
                                ArrayList<Lodgment> lodgList = null;

                                if(protocol.getBodyLength() == 0)   // 키워드가 없는 경우 전체 데이터 전송
                                    lodgList = db.getLodgmentList();
                                else                                // 키워드가 있는 경우 해당 키워드로 검색 후 해당하는 데이터만 전송
                                {
                                    String keyword = (String) SerialManager.toObject(protocol.getBody());
                                    lodgList = db.getLodgmentList(keyword);
                                }

                                if(!lodgList.isEmpty())     // 숙소 객체 배열을 전송
                                {
                                    Lodgment[] lodgArray = lodgList.toArray(new Lodgment[lodgList.size()]);
                                    protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_SUCCESS, Protocol.PT_NULL, Protocol.PT_NULL);
                                    protocol.setBody(SerialManager.toByteArray(lodgArray));
                                }
                                else
                                    protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_FAIL, Protocol.PT_NULL, Protocol.PT_NULL);
                                return protocol.getPacket();
                        }
                        break;
                    case Protocol.REQ_ADD:      // 관리자가 추가요청
                        switch(packet[3])
                        {
                            case Protocol.TAG_TRAVEL:   // 관리자의 관광지 추가
                                Tour tour = null;
                                byte[] temp = protocol.getBody();
                                tour = (Tour) SerialManager.toObject(temp);

                                result = db.addTour(tour);

                                if(result) protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_SUCCESS, Protocol.PT_NULL, Protocol.PT_NULL);
                                else protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_FAIL, Protocol.PT_NULL, Protocol.PT_NULL);
                                return protocol.getPacket();
                            case Protocol.TAG_FOOD:     // 관리자의 식당 추가
                                Restaurant res = null;
                                temp = protocol.getBody();
                                res = (Restaurant) SerialManager.toObject(temp);

                                result = db.addRestaurant(res);

                                if(result) protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_SUCCESS, Protocol.PT_NULL, Protocol.PT_NULL);
                                else protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_FAIL, Protocol.PT_NULL, Protocol.PT_NULL);
                                return protocol.getPacket();
                            case Protocol.TAG_HOTEL:    // 관리자의 숙소 추가
                                Lodgment lodg = null;
                                temp = protocol.getBody();
                                lodg = (Lodgment) SerialManager.toObject(temp);

                                result = db.addLodgment(lodg);

                                if(result) protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_SUCCESS, Protocol.PT_NULL, Protocol.PT_NULL);
                                else protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_FAIL, Protocol.PT_NULL, Protocol.PT_NULL);
                                return protocol.getPacket();
                        }
                        break;
                    case Protocol.REQ_DEL:      // 관리자가 삭제요청
                        switch(packet[3])
                        {
                            case Protocol.TAG_TRAVEL:   // 관리자의 관광지 삭제
                                Tour tour = null;
                                byte[] temp = protocol.getBody();
                                tour = (Tour) SerialManager.toObject(temp);

                                result = db.deleteTour(tour.getTourId());

                                if(result) protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_SUCCESS, Protocol.PT_NULL, Protocol.PT_NULL);
                                else protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_FAIL, Protocol.PT_NULL, Protocol.PT_NULL);
                                return protocol.getPacket();
                            case Protocol.TAG_FOOD:     // 관리자의 식당 삭제
                                Restaurant res = null;
                                temp = protocol.getBody();
                                res = (Restaurant) SerialManager.toObject(temp);

                                result = db.deleteRestaurant(res.getRestaurantId());

                                if(result) protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_SUCCESS, Protocol.PT_NULL, Protocol.PT_NULL);
                                else protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_FAIL, Protocol.PT_NULL, Protocol.PT_NULL);
                                return protocol.getPacket();
                            case Protocol.TAG_HOTEL:    // 관리자의 숙소 삭제
                                Lodgment lodg = null;
                                temp = protocol.getBody();
                                lodg = (Lodgment) SerialManager.toObject(temp);

                                result = db.deleteLodgment(lodg.getLodgmentId());

                                if(result) protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_SUCCESS, Protocol.PT_NULL, Protocol.PT_NULL);
                                else protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_FAIL, Protocol.PT_NULL, Protocol.PT_NULL);
                                return protocol.getPacket();
                        }
                        break;
                }
                break;
        }

        return null;
    }
}