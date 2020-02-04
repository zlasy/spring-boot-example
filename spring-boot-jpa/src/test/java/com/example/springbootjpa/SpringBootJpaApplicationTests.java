package com.example.springbootjpa;

import com.example.springbootjpa.dao.StaffRepository;
import com.example.springbootjpa.dto.Staff;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootJpaApplicationTests {

    @Autowired
    private StaffRepository staffRepository;

    @Test
    public void test() throws Exception {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);

        staffRepository.save(new Staff("s1", "aa@126.com", "aa", "aa123456",formattedDate));
        staffRepository.save(new Staff("s2", "bb@126.com", "bb", "bb123456",formattedDate));
        staffRepository.save(new Staff("s3", "cc@126.com", "cc", "cc123456",formattedDate));

        Assert.assertEquals(3, staffRepository.findAll().size());
        Assert.assertEquals("bb", staffRepository.findByUserNameOrEmail("bb", "cc@126.com").getNickName());
        staffRepository.delete(staffRepository.findByUserName("aa1"));
    }

    @Test
    public void execute() {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);

//        staffRepository.save(new Staff("s1", "aa@126.com", "aa", "aa123456",formattedDate));

//        Assert.assertEquals(3, staffRepository.findAll().size());
//        Assert.assertEquals("bb", staffRepository.findByUserNameOrEmail("bb", "cc@126.com").getNickName());
//        staffRepository.delete(staffRepository.findByUserName("cc"));

//        System.out.println(staffRepository.findById(2L));

        Pageable pageable = PageRequest.of(1,2);
        System.out.println(staffRepository.findAll(pageable).getContent());
    }
}
