package cn.xl.wx.wx_1901;/**
 * @author luowenqin
 * @create 2019-03-25 11:37
 */

import cn.xl.wx.wx1901.entity.TbUser;
import cn.xl.wx.wx1901.mapper.UserMapper;
import cn.xl.wx.wx1901.service.UserService;
import cn.xl.wx.wx1901.vo.PageParam;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 *   @ClassName WXApplicationTests
 *   @Description TODO
 *   @Author luowenqin
 *   @Date 2019/3/25 11:37
 *   @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class WXApplicationTests {



    @Autowired
    private UserMapper userMapper;


    @Test
    public void contextLoads() {
        System.out.println("userService："+userMapper);
    }
}
