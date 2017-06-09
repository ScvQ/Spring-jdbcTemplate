package cn.spiderpig.jdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import cn.spiderpig.demo.dao.UserDao;
import cn.spiderpig.demo.entity.User;

public class MessTest {

    private ApplicationContext ctx = null;
    private UserDao userDao = null;

    @Before
    public void before() {
        ctx = new ClassPathXmlApplicationContext("beans.xml");
        userDao = (UserDao) ctx.getBean("userDao");
        System.out.println("before");
    }

    @Test
    public void dataSourceTest() throws SQLException {
        DataSource dataSource = (DataSource) ctx.getBean("dataSource");
        //Assert.assertNotNull(dataSource);
        ResultSet resultSet = dataSource.getConnection().createStatement().executeQuery("select * from user");
        while(resultSet.next()){
            System.out.println(resultSet.getString("username"));
            System.out.println(resultSet.getString("password"));
        }
        
    }
    
    @Test
    public void jdbcTemplateTest(){
        /*JdbcTemplate jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        List list = jdbcTemplate.queryForList("select * from user");
        System.out.println(list);*/
        List<User> list = userDao.getAll();
        for(User u:list){
            System.out.println("用户名："+u.getUsername());
            System.out.println("密码："+u.getPassword());
        }
        
    }

    @After
    public void after() {
        ctx = null;
        System.out.println("after");
    }

}
