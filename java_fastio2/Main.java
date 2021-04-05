import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class Main {
	public static void main(String args[]) throws Exception {
        FastIO io = new FastIO(); 

		int a, b;
		a = io.nextInt();
		b = io.nextInt();
        
        io.println(a+b);
        io.flushbuffer();
	}
    
    private static class FastIO {
        private static final int EOF = -1;
        
        private static final byte ASCII_n = 10;
        private static final byte ASCII_space = 32;
        private static final byte ASCII_minus = 45;
        private static final byte ASCII_0 = 48;
        private static final byte ASCII_9 = 57;
        
        private final DataInputStream din;
        private final DataOutputStream dout;
        
        private byte[] inbuffer;
        private int inbufferpointer, bytesread;
        private byte[] outbuffer;
        private int outbufferpointer;
        
        private byte[] bytebuffer;
        
        private FastIO() {
            this.din = new DataInputStream(System.in);
            this.dout = new DataOutputStream(System.out);
            
            this.inbuffer = new byte[65536];
            this.inbufferpointer = 0;
            this.bytesread = 0;
            this.outbuffer = new byte[65536];
            this.outbufferpointer = 0;
            
            this.bytebuffer = new byte[20];
        }
        
        private byte read() {
            if (inbufferpointer == bytesread)
                fillbuffer();
            return bytesread == EOF ? EOF : inbuffer[inbufferpointer++];
        }
        
        private void fillbuffer() {
            try {
                bytesread = din.read(inbuffer, inbufferpointer = 0, inbuffer.length);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        
        private void write(byte b) {
            if (outbufferpointer == outbuffer.length)
                flushbuffer();
            outbuffer[outbufferpointer++] = b;
        }
        
        private void flushbuffer() {
            if (outbufferpointer != 0) {
                try {
                    dout.write(outbuffer, 0, outbufferpointer);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                outbufferpointer = 0;
            }
        }
        
        private int nextInt() {
            byte b;
            while(isSpace(b = read()))
                ;
            boolean negative = false;
            if (b == '-') {
                negative = true;
                b = read();
            }
            int result = b - '0';
            while (isDigit(b = read()))
                result = result * 10 + b - '0';
            return negative ? -result : result;
        }
        
        private void println(int i) {
            if (i == 0) {
                write(ASCII_0);
            } else {
                if (i < 0) {
                    write(ASCII_minus);
                    i = -i;
                }
                int index = 0;
                while (i > 0) {
                    bytebuffer[index++] = (byte) ((i % 10) + ASCII_0);
                    i /= 10;
                }
                while (index-- > 0) {
                    write(bytebuffer[index]);
                }
            }
            write(ASCII_n);
        }
        
        private boolean isSpace(byte b) {
            return b <= ASCII_space && b >= 0;
        }
        
        private boolean isDigit(byte b) {
            return b >= ASCII_0 && b <= ASCII_9;
        }
    }
}