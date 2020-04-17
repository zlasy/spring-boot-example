package com.example.dubbo.provider.aspect;

import com.example.dubbo.result.ApiResult;
import com.google.common.collect.Sets;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Set;

@Aspect
@Component
public class DubboValidationAspect {

    @Pointcut("execution(public * com.example.dubbo.provider.service.*Impl.*(..))")
    public void validatePoint(){

    }
    
    @Before("validatePoint()")
    public void before(JoinPoint point){
        System.out.println("before");
    }

    @Around("validatePoint()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        MethodSignature ms = (MethodSignature)point.getSignature();
        Object target = point.getTarget();
        Method method = target.getClass().getMethod(ms.getName(), ms.getParameterTypes());
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            if (parameter.isAnnotationPresent(Validated.class)){
                Set<String> msgs = validate(args[i]);
                if (!msgs.isEmpty()){
                    return new ApiResult<>(103, msgs.toString());
                }
            }
        }
        return point.proceed();
    }

    private <T> Set<String> validate(T param) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<T>> validateResult = validator.validate(param);
        Set<String> msg = Sets.newHashSetWithExpectedSize(10);
        if(validateResult != null && validateResult.size() > 0) {
            for(ConstraintViolation<T> res : validateResult) {
                msg.add(res.getMessage());
            }
        }
        return msg;
    }
}
