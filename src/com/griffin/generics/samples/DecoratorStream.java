package com.griffin.generics.samples;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/*
From Coursera code challenges.
Modify a DecoratorStream class to extend OutputStream, implementing needed abstract methods, and
generate a onetime prefix for output stream writes through the DecoratorStream.
 */

public class DecoratorStream extends OutputStream {
    final private OutputStream stream;
    final private String prefix;
    private boolean writeOnce = false;

    public DecoratorStream(OutputStream stream, String prefix) {
        super();
        this.stream = stream;
        this.prefix = prefix;

    }

    /*
    Override and implement abstract OutputStream method.
     */
    @Override
    public void write(int b) throws IOException {
        byte[] result = new byte[4];

        result[0] = (byte) (b >> 24);
        result[1] = (byte) (b >> 16);
        result[2] = (byte) (b >> 8);
        result[3] = (byte) (b);

        write(result, 0, 4);
    }

    public void write(byte[] b, int off, int len) throws IOException {
        if (!writeOnce) {
            writeOnce = true;
            write(prefix.getBytes());
        } else
            stream.write(b);

    }

    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    public static void main(String[] args) throws IOException {
        byte[] message = new byte[]{0x48, 0x65, 0x6c, 0x6c, 0x6f, 0x2c, 0x20, 0x77, 0x6f, 0x72, 0x6c, 0x64, 0x21};
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            DecoratorStream decoratorStream = new DecoratorStream(baos, "First line: ");
            System.out.println("Write to baos");
            decoratorStream.write(message);

            System.out.println("read from baos");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(baos.toByteArray()), StandardCharsets.UTF_8))) {
                System.out.println(reader.readLine());  //should print "First line: Hello, world!"
            }
        }
    }
}