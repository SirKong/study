package com.ccnu.test.nio;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class BufferTest {
    public static void main(String[] args) throws Exception {
        //缓冲区有两种方式来创建： 分配的方式  & 包装的方式
        //1.直接分配的方式
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //2.包装数组的方式
        byte[] barray = new byte[1024];
        ByteBuffer buffer2 = ByteBuffer.wrap(barray);


        //缓冲区的分片  slice方法可以根据position和limit设定的值，将position到limit之间的一片存储单元划分为一个子缓冲区
        ByteBuffer buffer3 = ByteBuffer.allocate(10);
        //如下操作将会创建一个包含槽 3 到槽 6 的子缓冲区，对子缓冲区的操作也会影响到原缓冲区
        buffer3.position(3);
        buffer3.limit(7);
        ByteBuffer slice = buffer3.slice();


        //将文件映射到内存
        FileChannel fc = new FileInputStream(new File("D:/test.txt")).getChannel();
        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE,
                0, 1024);


        //聚集|分散IO，一个分散的读取就像一个常规通道读取，只不过它是将数据读到一个缓冲区数组中而不是读到单个缓冲区中。
        //同样地，一个聚集写入是向缓冲区数组而不是向单个缓冲区写入数据

    }
}
