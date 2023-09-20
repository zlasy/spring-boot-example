package com.example.springbootactuator;

import com.example.redis.controller.RedisController;
import com.example.redis.vo.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.concurrent.TimeUnit;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRedisApplicationTests {

    private MockMvc mvc;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new RedisController()).build();
    }

    @Test
    public void getStr() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/getStr/12345").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("12345"));
    }

    @Test
    public void testStr() {
        stringRedisTemplate.opsForValue().set("aaa", "111");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testObj() throws Exception {
        User user = new User("zlasy", 123L, 23, "aa123");
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set("obju", user);
        operations.set("obju1s", user,100, TimeUnit.SECONDS);
        Thread.sleep(1000);
        //redisTemplate.delete("obju1s");
        boolean exists = redisTemplate.hasKey("obju1s");
        if(exists){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }
    }
}
