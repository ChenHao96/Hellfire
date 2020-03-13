package com.github.chenhao96.config;

import com.github.chenhao96.annotation.SystemAdminOption;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
//@Aspect
//@Component
public class SpringControllerAop {

    /**
     * 匹配规则：modifiers-pattern? ret-type-pattern declaring-type-pattern?name-pattern(param-pattern) throws-pattern?
     * <p>
     * modifiers-pattern：方法的可见性，如public，protected；
     * ret-type-pattern：方法的返回值类型，如int，void等；
     * declaring-type-pattern：方法所在类的全路径名，如com.spring.Aspect；
     * name-pattern：方法名类型，如buisinessService()；
     * param-pattern：方法的参数类型，如java.lang.String；
     * throws-pattern：方法抛出的异常类型，如java.lang.Exception；
     * <p>
     * execution:切面粒度最小级别
     * execution(* com.github.chenhao96.controller.*.*(..))//controller包下所有类所有方法任意参数任意返回值
     * execution(public * com.github.chenhao96.controller..*.*(..))//controller包及子包下所有类public方法任意参数任意返回值
     * execution(* com.github.chenhao96.controller..*.*To(..))//controller包及子包下所有类'To'结尾方法任意参数任意返回值
     * execution(* com.github.chenhao96.controller.*.*(java.lang.String,..))//controller包下所有类所有方法任意参数(String第一参数)任意返回值
     * <p>
     * (@)args:切面粒度匹配指定参数类型和指定参数数量的方法
     * args:匹配指定参数
     * \@args:匹配指定参数注解
     * <p>
     * (@)within:切面粒度为类
     * within(?):匹配指定类
     * \@within(?):匹配指定注解下的类
     * <p>
     * \@annotation:切面粒度为方法
     * 匹配指定注解下的方法
     * <p>
     * https://blog.csdn.net/hzau_itdog/article/details/89450225?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task
     */

    @Pointcut("execution(public * com.github.chenhao96.controller.*.*(..))")
    public void controllerPointcut() {
    }

    @Before("controllerPointcut() && @annotation(systemAdminOption)")
    public void beforeMethod(JoinPoint joinPoint, SystemAdminOption systemAdminOption) {
        //TODO:
    }

    @AfterReturning(value = "controllerPointcut()", returning = "result")
    public void afterReturningMethod(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().toShortString();
        //TODO:
        log.info("controllerAop afterReturningMethod:{}, return:{}", methodName, result);
    }
}
