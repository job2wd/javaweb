package org.javaweb.showcase.cache.memcached.hibernate4.util;

/**
 * @author KwonNam Son (kwon37xi@gmail.com)
 */
public class IntToBytesUtils {

    /**
     * int value to 4 bytes array.
     */
    public static byte[] intToBytes(int value) {
        return new byte[]{(byte) (value >>> 24), (byte) (value >>> 16), (byte) (value >>> 8), (byte) (value)

        };
    }

    /**
     * 4 bytes array to int
     */
    public static int bytesToInt(byte[] bytes) {
        int value = (bytes[0] & 0xFF) << 24;
        value += (bytes[1] & 0xFF) << 16;
        value += (bytes[2] & 0xFF) << 8;
        value += bytes[3] & 0xFF;
        return value;
    }

}
