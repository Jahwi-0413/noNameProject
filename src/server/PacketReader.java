package server;

import server.Protocol;

// 패킷을 분석해서 적절한 작업으로 연결해주는 클래스
public class PacketReader {
    public static void read(byte[] packet)
    {
        switch(packet[0])
        {
            case Protocol.PW_USER:          // 권한이 사용자일때
                switch(packet[2])
                {
                    case Protocol.REQ_LOOK:     // 사용자가 조회요청
                        switch(packet[3])
                        {
                            case Protocol.TAG_TRAVEL:   // 사용자의 관광지 조회
                                // 이곳에 동작 기술
                                break;
                            case Protocol.TAG_FOOD:     // 사용자의 식당 조회
                                // 이곳에 동작 기술
                                break;
                            case Protocol.TAG_HOTEL:    // 사용자의 숙소 조회
                                // 이곳에 동작 기술
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
                                // 이곳에 동작 기술
                                break;
                            case Protocol.TAG_FOOD:     // 관리자의 식당 조회
                                // 이곳에 동작 기술
                                break;
                            case Protocol.TAG_HOTEL:    // 관리자의 숙소 조회
                                // 이곳에 동작 기술
                                break;
                        }
                        break;
                    case Protocol.REQ_ADD:      // 관리자가 추가요청
                        switch(packet[3])
                        {
                            case Protocol.TAG_TRAVEL:   // 관리자의 관광지 추가
                                // 이곳에 동작 기술
                                break;
                            case Protocol.TAG_FOOD:     // 관리자의 식당 추가
                                // 이곳에 동작 기술
                                break;
                            case Protocol.TAG_HOTEL:    // 관리자의 숙소 추가
                                // 이곳에 동작 기술
                                break;
                        }
                        break;
                    case Protocol.REQ_DEL:      // 관리자가 삭제요청
                        switch(packet[3])
                        {
                            case Protocol.TAG_TRAVEL:   // 관리자의 관광지 삭제
                                // 이곳에 동작 기술
                                break;
                            case Protocol.TAG_FOOD:     // 관리자의 식당 삭제
                                // 이곳에 동작 기술
                                break;
                            case Protocol.TAG_HOTEL:    // 관리자의 숙소 삭제
                                // 이곳에 동작 기술
                                break;
                        }
                        break;
                }
                break;
        }
    }
}