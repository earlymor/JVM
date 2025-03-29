package cn.earlymor.directbuffer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @projectName: JVM
 * @package: cn.earlymor.directbuffer
 * @className: DirectBufferTest
 * @author: earlymor
 * @description: DirectBuffer Demo
 * @date: 2025/3/29 19:42
 * @version: 1.0
 */
public class DirectBufferTest {
    public static void main(String[] args) {
        testDirectBuffer();
    }
    public static  void testDirectBuffer(){
        // 1. 分配直接内存（堆外内存）
        // 分配1KB直接内存
        ByteBuffer directBuffer = ByteBuffer.allocateDirect(1024);
        System.out.println("DirectBuffer allocated. Capacity: " + directBuffer.capacity());

        // 2. 写入数据到DirectByteBuffer
        String data = "Hello, DirectByteBuffer!";
        directBuffer.put(data.getBytes(StandardCharsets.UTF_8));
        System.out.println("Data written to buffer: " + data);

        // 3. 切换为读模式，读取数据
        directBuffer.flip();
        byte[] readData = new byte[directBuffer.remaining()];
        directBuffer.get(readData);
        System.out.println("Data read from buffer: " + new String(readData, StandardCharsets.UTF_8));

        // 4. 模拟JNI层操作（通过GetDirectBufferAddress获取内存地址）
        // 假设调用JNI方法处理数据
        nativeProcessBuffer(directBuffer);

        // 5. 手动触发GC观察内存回收（仅测试用，生产环境不推荐）
        System.gc(); // 触发Cleaner回收堆外内存
        System.out.println("GC triggered. Direct memory may be released.");

        // 6. 防止程序退出过快（观察GC日志）
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // 模拟JNI方法：通过GetDirectBufferAddress操作直接内存
    private static native void nativeProcessBuffer(ByteBuffer buffer);

    static {
        // 加载JNI库（实际项目需实现对应的C/C++代码）
        System.loadLibrary("nativebuffer");
    }
}
