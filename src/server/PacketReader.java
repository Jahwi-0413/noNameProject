package server;

import server.Protocol;
import java.util.*;
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

                                if(!tourList.isEmpty())
                                {
                                    //TODO: list를 출력함수로 넘겨서 출력
                                    protocol = new Protocol(Protocol.PW_USER, Protocol.TYPE_SUCCESS, Protocol.PT_NULL, Protocol.PT_NULL);
                                }
                                else
                                    protocol = new Protocol(Protocol.PW_USER, Protocol.TYPE_FAIL, Protocol.PT_NULL, Protocol.PT_NULL);
                                return protocol.getPacket();
                                break;
                            case Protocol.TAG_FOOD:     // 사용자의 식당 조회
                                ArrayList<Restaurant> resList = db.getRestaurantList();

                                if(!resList.isEmpty())
                                {
                                    //TODO: list를 출력함수로 넘겨서 출력
                                    protocol = new Protocol(Protocol.PW_USER, Protocol.TYPE_SUCCESS, Protocol.PT_NULL, Protocol.PT_NULL);
                                }
                                else
                                    protocol = new Protocol(Protocol.PW_USER, Protocol.TYPE_FAIL, Protocol.PT_NULL, Protocol.PT_NULL);
                                return protocol.getPacket();
                                break;
                            case Protocol.TAG_HOTEL:    // 사용자의 숙소 조회
                                ArrayList<Lodgment> lodgList = db.getLodgmentList();

                                if(!lodgList.isEmpty())
                                {
                                    //TODO: list를 출력함수로 넘겨서 출력
                                    protocol = new Protocol(Protocol.PW_USER, Protocol.TYPE_SUCCESS, Protocol.PT_NULL, Protocol.PT_NULL);
                                }
                                else
                                    protocol = new Protocol(Protocol.PW_USER, Protocol.TYPE_FAIL, Protocol.PT_NULL, Protocol.PT_NULL);
                                return protocol.getPacket();
                                break;
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

                                if(!tourList.isEmpty())
                                {
                                    //TODO: list를 출력함수로 넘겨서 출력
                                    protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_SUCCESS, Protocol.PT_NULL, Protocol.PT_NULL);
                                }
                                else
                                    protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_FAIL, Protocol.PT_NULL, Protocol.PT_NULL);
                                return protocol.getPacket();
                                break;
                            case Protocol.TAG_FOOD:     // 관리자의 식당 조회
                                ArrayList<Restaurant> resList = db.getRestaurantList();

                                if(!tourList.isEmpty())
                                {
                                    //TODO: list를 출력함수로 넘겨서 출력
                                    protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_SUCCESS, Protocol.PT_NULL, Protocol.PT_NULL);
                                }
                                else
                                    protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_FAIL, Protocol.PT_NULL, Protocol.PT_NULL);
                                return protocol.getPacket();
                                break;
                            case Protocol.TAG_HOTEL:    // 관리자의 숙소 조회
                                ArrayList<Lodgment> lodgList = db.getLodgmentList();

                                if(!tourList.isEmpty())
                                {
                                    //TODO: list를 출력함수로 넘겨서 출력
                                    protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_SUCCESS, Protocol.PT_NULL, Protocol.PT_NULL);
                                }
                                else
                                    protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_FAIL, Protocol.PT_NULL, Protocol.PT_NULL);
                                return protocol.getPacket();
                                break;
                        }
                        break;
                    case Protocol.REQ_ADD:      // 관리자가 추가요청
                        switch(packet[3])
                        {
                            case Protocol.TAG_TRAVEL:   // 관리자의 관광지 추가
                                Tour tour = null;  //TODO: 패킷에서 바디부분 분리해서 역직렬화
                                result = db.addTour(tour);

                                if(result) protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_SUCCESS, Protocol.PT_NULL, Protocol.PT_NULL);
                                else protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_FAIL, Protocol.PT_NULL, Protocol.PT_NULL);
                                return protocol.getPacket();
                                break;
                            case Protocol.TAG_FOOD:     // 관리자의 식당 추가
                                Restaurant res = null;  //TODO: 패킷에서 바디부분 분리해서 역직렬화
                                result = db.addRestaurant(res);

                                if(result) protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_SUCCESS, Protocol.PT_NULL, Protocol.PT_NULL);
                                else protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_FAIL, Protocol.PT_NULL, Protocol.PT_NULL);
                                return protocol.getPacket();
                                break;
                            case Protocol.TAG_HOTEL:    // 관리자의 숙소 추가
                                Lodgment lodg = null;   //TODO: 패킷에서 바디부분 분리해서 역직렬화
                                result = db.addLodgment(lodg);

                                if(result) protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_SUCCESS, Protocol.PT_NULL, Protocol.PT_NULL);
                                else protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_FAIL, Protocol.PT_NULL, Protocol.PT_NULL);
                                return protocol.getPacket();
                                break;
                        }
                        break;
                    case Protocol.REQ_DEL:      // 관리자가 삭제요청
                        switch(packet[3])
                        {
                            case Protocol.TAG_TRAVEL:   // 관리자의 관광지 삭제
                                //TODO: 이곳에 동작 기술
                                break;
                            case Protocol.TAG_FOOD:     // 관리자의 식당 삭제
                                //TODO: 이곳에 동작 기술
                                break;
                            case Protocol.TAG_HOTEL:    // 관리자의 숙소 삭제
                                //TODO: 이곳에 동작 기술
                                break;
                        }
                        break;
                }
                break;
        }
    }
}