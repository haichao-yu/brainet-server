package com.yhc.brainetserver.util;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.util.Base64;

public class Base64DoubleEncoder {

    public static String encode(double[] signalToEncode) {
        return Base64.getEncoder().encodeToString(doubleToByteArray(signalToEncode));
    }

    public static double[] decode(String encodedString) {
        return byteToDoubleArray(Base64.getDecoder().decode(encodedString));
    }

    private static byte[] doubleToByteArray(double[] doubles) {
        ByteBuffer buf = ByteBuffer.allocate(Double.SIZE / Byte.SIZE * doubles.length);
        buf.asDoubleBuffer().put(doubles);
        return buf.array();
    }

    private static double[] byteToDoubleArray(byte[] bytes) {
        DoubleBuffer buf = ByteBuffer.wrap(bytes).asDoubleBuffer();
        double[] doubleArray = new double[buf.limit()];
        buf.get(doubleArray);
        return doubleArray;
    }
}
