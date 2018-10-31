package org.javaweb.showcase.springboot.test;

import org.javaweb.showcase.springboot.controller.HelloWorldController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MockServletContext.class)
@WebAppConfiguration
public class JavawebShowcaseSpringbootApplicationTests {

  private MockMvc mock;
  
  //@Autowired
  //@Qualifier("mailSender")
  private JavaMailSender mailSender;
  
  @Before
  public void setUp() throws Exception {
    mock = MockMvcBuilders.standaloneSetup(new HelloWorldController()).build();
  }
  
  @Test
  public void testHome() throws Exception {
    mock.perform(MockMvcRequestBuilders.get("/hello/world").accept(MediaType.APPLICATION_JSON))
        .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
        .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.content()
            .string(org.hamcrest.CoreMatchers.equalTo("Hello World!")));
  }
  
  //@Test
  public void sendSimpleMail() throws Exception {
    SimpleMailMessage message = new SimpleMailMessage();
    //message.setFrom("dyc87112@qq.com");
    message.setTo("joy2wd@163.com");
    message.setSubject("主题：简单邮件");
    message.setText("测试邮件内容");
    
    mailSender.send(message);
  }

}
