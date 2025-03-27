package cn.earlymor.stringTable;

/**
 * @projectName: JVM
 * @package: cn.earlymor.stringTable
 * @className: StringTableTest
 * @author: earlymor
 * @description: 字符串常量池测试
 * @date: 2025/3/27 19:33
 * @version: 1.0
 */
public class StringTableTest {
    // 反汇编指令 javap -c StringTableTest.class
    // 详细信息 javap -v StringTableTest.class
    public static void main(String[] args) {
        test4();
    }

    public static void test1() {
        String s1 = new String("abc");  // 堆中对象，池中已有"abc"（因字面量）
        String s2 = s1.intern();        // 返回池中的引用（不会重复添加）
        System.out.println(s1 == s2);    // false，因为s1是堆对象，s2是池引用。
    }
    public static void test2() {
        String s1 = new String("abc");  // 堆中对象，不在池中
        String s2 = s1.intern();        // 将"abc"添加到池中，返回池引用
        String s3 = new String("abc");              // 直接引用池中对象
        String s4 = "abc";
        System.out.println(s1 == s2);  // 会返回false
        System.out.println(s2 == s3);  // 会返回false
        System.out.println(s1 == s3);  // 会返回false
        System.out.println(s2 == s4);  // 会返回true
        System.out.println("s1 = " + System.identityHashCode(s1));
        System.out.println("s2 = " + System.identityHashCode(s2));
        System.out.println("s3 = " + System.identityHashCode(s3));
        System.out.println("s4 = " + System.identityHashCode(s4));

    }
    /**
     * @description 所有值相等的String 调用intern方法的返回值都相等
     * @param : 
     * @return void
     * @author earlymor
     * @date 2025/3/27 19:56
     */
    public static void test3() {
        String s1 = new String("abc");
        String s2 = s1.intern();        // 将"abc"添加到池中，返回池引用
        String s3 = "abc".intern();              // 直接引用池中对象
        System.out.println(s1 == s2);  // 会返回false
        System.out.println(s2 == s3);  // 会返回true
        System.out.println(s1 == s3);  // 会返回false
        System.out.println("s1 = " + System.identityHashCode(s1));
        System.out.println("s2 = " + System.identityHashCode(s2));
        System.out.println("s3 = " + System.identityHashCode(s3));

    }
    public static void test4() {
        String s4 = new String("3") + new String("3");
        String s5 = s4.intern();
        String s6 = "33";
        System.out.println(s4 == s6);
        System.out.println(s5 == s6);
        System.out.println(System.identityHashCode(s4));
        System.out.println(System.identityHashCode(s6));
    }
}
