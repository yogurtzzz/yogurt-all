package condition;

import annotation.MagicBean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class SmzCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
//        Environment environment = conditionContext.getEnvironment();
//        String yogurt = environment.getProperty("yogurt");
//        return "on".equals(yogurt);
        return annotatedTypeMetadata.isAnnotated(MagicBean.class.getName());
    }
}
