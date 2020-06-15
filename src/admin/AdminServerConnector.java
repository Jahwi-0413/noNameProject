package admin;

import admin.*;
import communicate.PacketManager;

public class AdminServerConnector
{
    private static PacketManager packetManager;

    public static void setPacketManager(PacketManager manager)
    {
        packetManager = manager;
        System.out.println("set");
    }
    public static PacketManager getPacketManager()
    {
        System.out.println("get");
        return packetManager;
    }
}
