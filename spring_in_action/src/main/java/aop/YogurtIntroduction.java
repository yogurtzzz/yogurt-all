package aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;
import service.DinnerService;

@Aspect
@Component
public class YogurtIntroduction {
    @DeclareParents(value ="service.DinnerService+",defaultImpl = NewFunctionImpl.class)
    public static NewFunction function;
    @Before("execution(* service.DinnerService.*(..)) && this(function)")
    public void before(NewFunction function){
        function.fun();
    }
}
