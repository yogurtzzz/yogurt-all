package concurrent.functional;

/**
 * @author yogurtzzz
 * @date 2020/4/21 17:46
 **/
public class FunTest {
    public static void main(String[] args) {
        MyFun fun = () -> System.out.println("yogurt drink yogurt");
        funAop(fun);
    }

    private static void funAop(MyFun fun){
    }
}
