package communicate;

import tableClass.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerialManager
{
    // Object to Byte[]
    public static byte[] toByteArray(Object obj)
    {
        byte[] result = null;

        try
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            result = baos.toByteArray();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return result;
    }

    // Object[] to Byte[]
    public static byte[] toByteArray(Object[] obj)
    {
        byte[] result = null;

        try
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            result = baos.toByteArray();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return result;
    }

    // Byte[] to Object
    public static Object toObject(byte[] data)
    {
        Object obj = null;
        try
        {
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(bais);
            obj = ois.readObject();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return obj;
    }

    // Byte[] to Object[]
    public static Object[] toObjectArr(byte[] data)
    {
        Object[] obj = null;
        try
        {
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(bais);
            obj = (Object[]) ois.readObject();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return obj;
    }
}
