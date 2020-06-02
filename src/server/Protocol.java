package server;

public class Protocol
{
    // Power
    public static final byte PW_USER = 1;   // 사용자
    public static final byte PW_ADMIN = 2;  // 관리자

    // Type
    public static final byte TYPE_REQ = 1;      // 작업 요청
    public static final byte TYPE_SUCCESS = 2;  // 성공 응답
    public static final byte TYPE_FAIL = 3;     // 실패 응답

    // Request
    public static final byte REQ_LOOK = 1;      // 조회
    public static final byte REQ_ADD = 2;       // 추가
    public static final byte REQ_DEL = 3;       // 삭제

    // Target
    public static final byte TAG_TRAVEL = 1;    // 관광지
    public static final byte TAG_FOOD = 2;      // 식당
    public static final byte TAG_HOTEL = 3;     // 숙소

    // 해당 없는 파트에 넣을 값
    public static final byte PT_NULL = 0;

    // Protocol 내부 변수
    private byte power;
    private byte type;
    private byte request;
    private byte target;
    private int bodyLength;
    private byte[] body;

    public Protocol()
    {
        power = PT_NULL;
        type = PT_NULL;
        request = PT_NULL;
        target = PT_NULL;
        bodyLength = PT_NULL;
        body = null;
    }

    public Protocol(byte power, byte type, byte request, byte target)
    {
        this.power = power;
        this.type = type;
        this.request = request;
        this.target = target;
        bodyLength = PT_NULL;
        body = null;
    }

    public Protocol(byte power, byte type, byte request, byte target, byte[] data)
    {
        this.power = power;
        this.type = type;
        this.request = request;
        this.target = target;
        setBody(data);
    }

    public byte getPower() {
        return power;
    }

    public void setPower(byte power) {
        this.power = power;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getRequest() {
        return request;
    }

    public void setRequest(byte request) {
        this.request = request;
    }

    public byte getTarget() {
        return target;
    }

    public void setTarget(byte target) {
        this.target = target;
    }

    public int getBodyLength() {
        return bodyLength;
    }

    private void setBodyLength(int bodyLength) {
        this.bodyLength = bodyLength;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
        setBodyLength(body.length);
    }

    // Integer -> Byte[] 로 변환하는 함수
    public static byte[] intToByteArray(int val) {
        byte[] bytearr = new byte[4];
        bytearr[0] = (byte) (val);
        bytearr[1] = (byte) (val >> 8);
        bytearr[2] = (byte) (val >> 16);
        bytearr[3] = (byte) (val >> 24);
        return bytearr;
    }

    // Byte[] -> Integer 로 변환하는 함수
    public static int byteArrayToint(byte[] bytearr) {
        return ((int) (bytearr[0] & 0xff) + (int) ((bytearr[1] & 0xff) << 8) + (int) ((bytearr[2] & 0xff) << 16)
                + (int) ((bytearr[3] & 0xff) << 24));
    }

    public byte[] getPacket()
    {
        int packetSize = 0;

        if(this.body == null) packetSize = 8;
        else packetSize = 8 + getBodyLength();

        byte[] packet = new byte[packetSize];
        packet[0] = getPower();
        packet[1] = getType();
        packet[2] = getRequest();
        packet[3] = getTarget();
        byte[] length = intToByteArray(getBodyLength());
        System.arraycopy(length, 0, packet, 4, 4);

        if(packetSize != 8)
        {
            System.arraycopy(getBody(), 0, packet, 8, getBody().length);
        }

        return packet;
    }

    public void setPacket(byte[] packet)
    {
        setPower(packet[0]);
        setType(packet[1]);
        setRequest(packet[2]);
        setTarget(packet[3]);
        byte[] length = new byte[4];
        System.arraycopy(packet, 4, length, 0, 4);
        setBodyLength(byteArrayToint(length));

        if(bodyLength != 0)
        {
            byte[] body = new byte[bodyLength];
            System.arraycopy(packet, 8, body, 0, body.length);
            setBody(body);
        }
    }
}