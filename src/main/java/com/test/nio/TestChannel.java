package com.test.nio;



import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * java.nio.Channel
 *  FileChannel
 *  SocketChannel
 *  ServerSocketChannel
 *  DatagramChannel
 *
 *  获取通道的三种方式
 *  1. Java 针对支持通道的类提供了getChannel()的方法
 *  2. 在jdk1.7中 NIO.2 针对各个通道提供了静态方法 open()
 *  3. 在jdk1.7中 NIO.2 的 Files工具类的newFileChannel()
 *
 *  四、 通道之间的数据传输
 *  transferForm()
 *  transferTo()
 *
 *  五、分散(Scatter)与聚集(Gatter)
 *  分散读取（Scatter Readers）: 将通道中的数据分散到多个缓冲区
 *  聚集写入 （Gatter Writes）： 将多个Buffer中的数据 “聚集”到Channel中
 */
public class TestChannel {

    /**
     * 非直接缓冲区
     * 利用通道完成文件复制
     */
    public void test1() throws IOException {
        try(
                FileInputStream fis = new FileInputStream("E:\\JavaDev\\Test_2.txt");
                FileOutputStream fos = new FileOutputStream("E:\\JavaDev\\Test_22.txt");
                // 获取通道
                FileChannel inChannel = fis.getChannel();
                FileChannel outChannel = fos.getChannel();
            ) {
            // 分配指定大小的缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);
            // 将通道中的数据存入缓冲区中
            while(inChannel.read(buf) != -1) {
                buf.flip(); //切换成读取数据模式
                // 将缓冲区的数据写入通道
                outChannel.write(buf);
                buf.clear();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 使用直接缓冲区完成文件复制(内存映射文件)
     */
    public void test2() throws IOException {
        try( FileChannel inChannel = FileChannel.open(Paths.get("E:\\JavaDev\\Test_22.txt"),
                StandardOpenOption.READ);
             FileChannel outChannel = FileChannel.open(Paths.get("E:\\JavaDev\\Test_222.txt"),
                     StandardOpenOption.READ,
                     StandardOpenOption.WRITE,
                     StandardOpenOption.CREATE_NEW);
             ) {
            // 内存映射文件(直接缓冲区) （只有ByteBuffer支持直接缓冲区）
            MappedByteBuffer inMapBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
            MappedByteBuffer outMapBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());
            // 直接对缓冲区进行数据读写操作
            byte[] dst = new byte[inMapBuf.limit()];
            inMapBuf.get(dst);
            outMapBuf.put(dst);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    /**
     * 通道之间的数据传输
     * transferTo() (也是直接缓冲区的方式)
     */
    public void test3() {
        try( FileChannel inChannel = FileChannel.open(Paths.get("E:\\JavaDev\\Test_22.txt"),
                StandardOpenOption.READ);
             FileChannel outChannel = FileChannel.open(Paths.get("E:\\JavaDev\\Test_2222.txt"),
                     StandardOpenOption.READ,
                     StandardOpenOption.WRITE,
                     StandardOpenOption.CREATE_NEW);
        ) {
            inChannel.transferTo(0, inChannel.size(), outChannel);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 分散和聚集
     *
     */
    public void test4() {
        try(RandomAccessFile raf1 = new RandomAccessFile("E:\\JavaDev\\Test_22.txt", "rw");
            // 1.获取通道
            FileChannel raf1Channel = raf1.getChannel();
        ) {
            // 2. 分配指定大小的缓冲区
            ByteBuffer buf1 = ByteBuffer.allocate(100);
            ByteBuffer buf2 = ByteBuffer.allocate(1024);
            // 3. 分散读取
            ByteBuffer[]  bufferArray = new ByteBuffer[]{buf1, buf2};
            raf1Channel.read(bufferArray);
            for (ByteBuffer byteBuffer : bufferArray) {
                byteBuffer.flip();
            }
            // 前100个字节
            System.out.println(new String(bufferArray[0].array(), 0, bufferArray[0].limit()));
            System.out.println("--------------------------------");
            System.out.println(new String(bufferArray[1].array(), 0, bufferArray[1].limit()));

            //4 . 聚集写入
            RandomAccessFile raf2 = new RandomAccessFile("E:\\JavaDev\\Test_22_聚集写入.txt", "rw");
            FileChannel raf2Channel = raf2.getChannel();
            raf2Channel.write(bufferArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testCharset() {
        final SortedMap<String, Charset> stringCharsetSortedMap = Charset.availableCharsets();
        Set<Map.Entry<String, Charset>> entries = stringCharsetSortedMap.entrySet();
        for(Map.Entry<String, Charset> entry: entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

    }

    public void test6() throws CharacterCodingException {
        Charset cs1 = Charset.forName("GBK");
        // 获取编码器
        CharsetEncoder ce = cs1.newEncoder();
        // 获取解码器
        CharsetDecoder cd = cs1.newDecoder();

        CharBuffer cBuffer = CharBuffer.allocate(1024);
        cBuffer.put("人民威武！");
        cBuffer.flip();
        //编码
        ByteBuffer byteBuffer = ce.encode(cBuffer);
        for (int i = 0; i < 10; i++) {
            System.out.println(byteBuffer.get());
        }

        // 解码
        byteBuffer.flip();
        CharBuffer charBuffer = cd.decode(byteBuffer);
        System.out.println(charBuffer.toString());
        System.out.println("---------------------------------------------------");
        Charset charsetUTF8 = Charset.forName("UTF-8");
        byteBuffer.flip();
        CharBuffer c2Buffer = charsetUTF8.decode(byteBuffer);
        System.out.println(c2Buffer.toString());

    }
    

}
