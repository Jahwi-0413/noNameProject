package admin;

import admin.*;
import communicate.PacketManager;

public class AdminServerConnector
{
    private static PacketManager packetManager;

    public static void setPacketManager(PacketManager manager)
    {
        packetManager = manager;
    }
    public static PacketManager getPacketManager()
    {
        return packetManager;
    }

}
