package aop;

public class NewFunctionImpl implements NewFunction {
    @Override
    public void fun() {
        System.out.println("this is method by introduction");
    }
}
