package cn.xl.wx.wx1901;/**
 * @author luowenqin
 * @create 2019-03-25 11:27
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 *   @ClassName WXApplication
 *   @Description TODO
 *   @Author luowenqin
 *   @Date 2019/3/25 11:27
 *   @Version 1.0
 **/

@SpringBootApplication
@MapperScan(basePackages = "cn.xl.wx.wx1901.mapper")
public class WXApplication {


    public static void main(String[] args) {
        SpringApplication.run(WXApplication.class,args);
    }
}
