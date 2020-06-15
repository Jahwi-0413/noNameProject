package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import communicate.Protocol;

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
            BufferedInputStream reader = new BufferedInputStream(socket.getInputStream());
            BufferedOutputStream writer = new BufferedOutputStream(socket.getOutputStream());

            while(true)
            {
                if(isExit) break;

                byte buf[] = new byte[1000000];

                int bytesRead = reader.read(buf, 0, buf.length);

                for(int i = 0; i < 8; i++)
                {
                    System.out.print(buf[i]);
                }
                System.out.println();

                byte[] answer = PacketReader.read(buf);

                writer.write(answer);
                writer.flush();
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