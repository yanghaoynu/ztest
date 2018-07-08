package test;

@SuppressWarnings("all")
public class TryCatchTest {
    public static void main(String[] args) {
        int a = TryCatchTest.simpleTryCatch();
        System.out.println("返回结果为"+a);
    }

    /**
     * 测试finally的执行顺序,可以通过调整return顺序来查看不同的执行效果
     * @return int
     */
    public static int simpleTryCatch() {
        int a = 0;
        try {
            System.out.println("this is try,a="+a);
            int i = 1 / 0;
        } catch (Exception e) {
            e.printStackTrace();
            ++a;
            System.out.println("this is catch,a="+a);
            return a;
        } finally {
            ++a;
            System.out.println("this is finally,a="+a);
        }
        ++a;
        System.out.println("this is after finally,a="+a);
        return a;
    }
}
