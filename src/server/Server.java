package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

import server.*;

public class Server
{
    private static final int PORT = 10000;

    public static void main(String[] args) throws IOException
    {
        ServerSocket serverSocket = new ServerSocket(PORT);
        Socket socket = null;
        System.out.println("시작");

        try
        {
            while(true)
            {
                System.out.println("접속 대기");
                socket = serverSocket.accept();
                System.out.println(socket.getInetAddress() + " - 접속");
                SocketManager socketManager = new SocketManager(socket);
                socketManager.start();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            serverSocket.close();
        }
    }
}

class SocketManager extends Thread
{
    Socket socket = null;
    byte power = Protocol.PT_NULL;

    public SocketManager(Socket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run()
    {
        boolean isExit = false;
        try
        {
            OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream();

            Protocol protocol = new Protocol();

            while(true)
            {
                if(isExit) break;

                byte buf[] = new byte[1000000];

                int bytesRead = is.read(buf, 0, buf.length);

                for(int i = 0; i < 8; i++)
                {
                    System.out.print(buf[i]);
                }
                System.out.println();

                //PacketReader.read(buf);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            System.out.println(socket.getInetAddress() + " - 연결 종료");
            try
            {
                socket.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}