package com.demo.springbootrabbitmq.message;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class TranslateObject {
    /**
     * 对象转化为字节码
     */
    public static byte[] getBytesFromObject(Serializable obj) {
        byte[] result = null;
        try {
            ByteOutputStream bos = new ByteOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            result = bos.getBytes();
            bos.close();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    /**
     * 字节码转化为对象
     */
    public static Object getObjectFromBytes(byte[] objBytes) {
        Object result = null;
        try {
            if (objBytes == null || objBytes.length == 0) {
                return null;
            }
            ByteArrayInputStream bai = new ByteArrayInputStream(objBytes);
            ObjectInputStream ois = new ObjectInputStream(bai);
            result = ois.readObject();
            bai.close();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
