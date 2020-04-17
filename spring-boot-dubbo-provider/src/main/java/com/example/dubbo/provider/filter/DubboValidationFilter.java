package com.example.dubbo.provider.filter;

import com.example.dubbo.provider.exception.InvalidParamException;
import com.google.common.collect.Sets;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.springframework.validation.annotation.Validated;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Set;

import static org.apache.dubbo.common.constants.CommonConstants.PROVIDER;

/**
 * 行不通。注解必须加在接口上才能取到。会增加接口依赖，不是好方式。
 */
@Activate(group = PROVIDER, order = -10000)
public class DubboValidationFilter  implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        Result result = invoker.invoke(invocation);
        try {
            Method method = invoker.getInterface().getMethod(invocation.getMethodName(), invocation.getParameterTypes());
            Parameter[] params = method.getParameters();
            for (Parameter param : params) {
                if (param.isAnnotationPresent(Validated.class)){
                    Set<String> msgs = validate(param);
                    if (!msgs.isEmpty()){
                        result.setException(new InvalidParamException(msgs.toString()));
                        return result;
                    }
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return result;
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
