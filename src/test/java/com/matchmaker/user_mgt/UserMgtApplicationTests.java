package com.matchmaker.user_mgt;

import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = UserServiceImplTest.class)
public class UserMgtApplicationTests {


    @Test
    public void contextLoads() {
    }


}
