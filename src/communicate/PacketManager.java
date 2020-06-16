package communicate;

import tableClass.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

// 클라이언트가 서버로 패킷을 보내기 위한 클래스
//TODO: 클라이언트에서 소켓 유지는 어떤식으로 할것인지 정할 필요 있음
public class PacketManager
{
    private Socket socket;
    private BufferedInputStream reader;
    private BufferedOutputStream writer;
    private Protocol protocol;
    private boolean isSet;

    public PacketManager(Socket socket) throws IOException
    {
        this.socket = socket;
        reader = new BufferedInputStream(socket.getInputStream());
        writer = new BufferedOutputStream(socket.getOutputStream());
        protocol = null;
        isSet = false;
    }

    // 설정 된 패킷을 전송
    public void packetSend()
    {
        if(isSet)
        {
            try
            {
                writer.write(protocol.getPacket());
                writer.flush();
                isSet = false;
                protocol = null;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    // 검색 요청, 매개변수는 Protocol의 static 상수로 지정해주세용
    // power는 Protocol.PW_USER or ADMIN, op는 Protocol.TAG_HOTEL or FOOD or TRAVEL
    public void searchRequest(int power, int op)    // 권한과 검색 대상(숙박 or 여행지 or 식당)
    {
        protocol = new Protocol((byte)power, Protocol.TYPE_REQ, Protocol.REQ_LOOK, (byte)op);
        isSet = true;
        packetSend();
    }

    // 키워드 검색을 위한 함수 오버로딩
    public void searchRequest(int power, int op, String keyword)    // 권한과 검색 대상(숙박 or 여행지 or 식당)과 검색 키워드
    {
        protocol = new Protocol((byte)power, Protocol.TYPE_REQ, Protocol.REQ_LOOK, (byte)op);
        protocol.setBody(SerialManager.toByteArray(keyword));
        isSet = true;
        packetSend();
    }

    // 추가 요청
    public void insertRequest(byte op, Object obj)   // 추가 대상(숙박 or 여행지 or 식당)과 해당 객체
    {
        protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_REQ, Protocol.REQ_ADD, op);
        protocol.setBody(SerialManager.toByteArray(obj));
        isSet = true;
        packetSend();
    }

    // 삭제 요청
    public void deleteRequest(int op, Object obj)   // 삭제 대상(숙박 or 여행지 or 식당)과 해당 객체
    {
        protocol = new Protocol(Protocol.PW_ADMIN, Protocol.TYPE_REQ, Protocol.REQ_DEL, (byte)op);
        protocol.setBody(SerialManager.toByteArray(obj));
        isSet = true;
        packetSend();
    }
}