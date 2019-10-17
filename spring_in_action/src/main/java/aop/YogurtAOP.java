package aop;

import anno.Yogurt;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class YogurtAOP {
    @Pointcut("target(service.SomeService) && args(number)")
    public void service(Long number){}

    @Before("service(number)")
    public void aop(Long number){
        System.out.println("aop take effect with number " + number);
    }
}
