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
        Protocol protocol = null;

        switch(packet[0])
        {
            case Protocol.PW_USER:          // 권한이 사용자일때
                switch(packet[2])
                {
                    case Protocol.REQ_LOOK:     // 사용자가 조회요청
                        switch(packet[3])
                        {
                            case Protocol.TAG_TRAVEL:   // 사용자의 관광지 조회
                                ArrayList<Tour> tourList = db.getTourList();

                                if(!tourList.isEmpty())     // 여행지 객체 배열을 전송
                                {
                                    Tour[] tourArray = (Tour[]) tourList.toArray();
                                    protocol = new Protocol(Protocol.PW_USER, Protocol.TYPE_SUCCESS, Protocol.PT_NULL, Protocol.PT_NULL);
                                    protocol.setBody(SerialManager.toByteArray(tourArray));
                                }
                                else
                                    protocol = new Protocol(Protocol.PW_USER, Protocol.TYPE_FAIL, Protocol.PT_NULL, Protocol.PT_NULL);
                                return protocol.getPacket();
                            case Protocol.TAG_FOOD:     // 사용자의 식당 조회
                                ArrayList<Restaurant> resList = db.getRestaurantList();

                                if(!resList.isEmpty())      // 식당 객체 배열을 전송
                                {
                                    Restaurant[] resArray = (Restaurant[]) resList.toArray();
                                    protocol = new Protocol(Protocol.PW_USER, Protocol.TYPE_SUCCESS, Protocol.PT_NULL, Protocol.PT_NULL);
                                    protocol.setBody(SerialManager.toByteArray(resArray));
                                }
                                else
                                    protocol = new Protocol(Protocol.PW_USER, Protocol.TYPE_FAIL, Protocol.PT_NULL, Protocol.PT_NULL);
                                return protocol.getPacket();
                            case Protocol.TAG_HOTEL:    // 사용자의 숙소 조회
                                ArrayList<Lodgment> lodgList = db.getLodgmentList();

                                if(!lodgList.isEmpty())     // 숙소 객체 배열을 전송
                                {
                                    Lodgment[] lodgArray = (Lodgment[]) lodgList.toArray();
                                    protocol = new Protocol(Protocol.PW_USER, Protocol.TYPE_SUCCESS, Protocol.PT_NULL, Protocol.PT_NULL);
                                    protocol.setBody(SerialManager.toByteArray(lodgArray));
                                }
                                else
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
                                ArrayList<Tour> tourList = db.getTourList();

                                if(!tourList.isEmpty())     // 여행지 객체 배열을 전송
                                {
                                    Tour[] tourArray = (Tour[]) tourList.toArray();
                                    protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_SUCCESS, Protocol.PT_NULL, Protocol.PT_NULL);
                                    protocol.setBody(SerialManager.toByteArray(tourArray));
                                }
                                else
                                    protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_FAIL, Protocol.PT_NULL, Protocol.PT_NULL);
                                return protocol.getPacket();
                            case Protocol.TAG_FOOD:     // 관리자의 식당 조회
                                ArrayList<Restaurant> resList = db.getRestaurantList();

                                if(!resList.isEmpty())      // 식당 객체 배열을 전송
                                {
                                    Restaurant[] resArray = (Restaurant[]) resList.toArray();
                                    protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_SUCCESS, Protocol.PT_NULL, Protocol.PT_NULL);
                                    protocol.setBody(SerialManager.toByteArray(resArray));
                                }
                                else
                                    protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_FAIL, Protocol.PT_NULL, Protocol.PT_NULL);
                                return protocol.getPacket();
                            case Protocol.TAG_HOTEL:    // 관리자의 숙소 조회
                                ArrayList<Lodgment> lodgList = db.getLodgmentList();

                                if(!lodgList.isEmpty())     // 숙소 객체 배열을 전송
                                {
                                    Lodgment[] lodgArray = (Lodgment[]) lodgList.toArray();
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
                                protocol.setPacket(packet);
                                byte[] temp = protocol.getBody();
                                tour = (Tour) SerialManager.toObject(temp);

                                result = db.addTour(tour);

                                if(result) protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_SUCCESS, Protocol.PT_NULL, Protocol.PT_NULL);
                                else protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_FAIL, Protocol.PT_NULL, Protocol.PT_NULL);
                                return protocol.getPacket();
                            case Protocol.TAG_FOOD:     // 관리자의 식당 추가
                                Restaurant res = null;
                                protocol.setPacket(packet);
                                temp = protocol.getBody();
                                res = (Restaurant) SerialManager.toObject(temp);

                                result = db.addRestaurant(res);

                                if(result) protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_SUCCESS, Protocol.PT_NULL, Protocol.PT_NULL);
                                else protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_FAIL, Protocol.PT_NULL, Protocol.PT_NULL);
                                return protocol.getPacket();
                            case Protocol.TAG_HOTEL:    // 관리자의 숙소 추가
                                Lodgment lodg = null;
                                protocol.setPacket(packet);
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
                                protocol.setPacket(packet);
                                byte[] temp = protocol.getBody();
                                tour = (Tour) SerialManager.toObject(temp);

                                result = db.deleteTour(tour.getTourId());

                                if(result) protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_SUCCESS, Protocol.PT_NULL, Protocol.PT_NULL);
                                else protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_FAIL, Protocol.PT_NULL, Protocol.PT_NULL);
                                return protocol.getPacket();
                            case Protocol.TAG_FOOD:     // 관리자의 식당 삭제
                                Restaurant res = null;
                                protocol.setPacket(packet);
                                temp = protocol.getBody();
                                res = (Restaurant) SerialManager.toObject(temp);

                                result = db.deleteTour(res.getRestaurantId());

                                if(result) protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_SUCCESS, Protocol.PT_NULL, Protocol.PT_NULL);
                                else protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_FAIL, Protocol.PT_NULL, Protocol.PT_NULL);
                                return protocol.getPacket();
                            case Protocol.TAG_HOTEL:    // 관리자의 숙소 삭제
                                Lodgment lodg = null;
                                protocol.setPacket(packet);
                                temp = protocol.getBody();
                                lodg = (Lodgment) SerialManager.toObject(temp);

                                result = db.deleteTour(lodg.getLodgmentId());

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