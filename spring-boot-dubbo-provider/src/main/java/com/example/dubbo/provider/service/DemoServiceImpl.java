package com.example.dubbo.provider.service;

import com.example.dubbo.result.ApiResult;
import com.example.dubbo.result.dto.UserDTO;
import com.example.dubbo.service.DemoService;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Set;

@Produces({MediaType.APPLICATION_JSON})
@Path("rest")
@Service(timeout = 3000)
@Component
@Slf4j
public class DemoServiceImpl implements DemoService {

    @GET
    @Path("/{str}")
    @Override
    public String test(@PathParam("str") String str) {
        log.info(str);
        return str;
    }

    @POST
    @Path("/createUser")
    @Override
    public ApiResult createUser(@Validated UserDTO user) {
        log.info(user.toString());
//        ApiResult result = validateRequestBody(user);
//        log.info(result.toString());
        log.info(user.getPassword().isEmpty() ? "true" : "false");
        return ApiResult.builder().code("102").data(user).build();
    }


//    public <T> ApiResult validateRequestBody(T reqData) {
//        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
//        Set<ConstraintViolation<T>> validateResult = validator.validate(reqData);
//        if(validateResult != null && validateResult.size() > 0) {
//            Set<String> msg = Sets.newHashSetWithExpectedSize(10);
//            for(ConstraintViolation<T> res : validateResult) {
//                msg.add(res.getMessage().toString());
//            }
//            return new ApiResult(101,msg.toString());
//        }
//        return null;
//    }
}
