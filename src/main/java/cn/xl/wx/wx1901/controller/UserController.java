package cn.xl.wx.wx1901.controller;/**
 * @author luowenqin
 * @create 2019-03-25 11:41
 */

import cn.xl.wx.wx1901.entity.TbUser;
import cn.xl.wx.wx1901.service.UserService;
import cn.xl.wx.wx1901.vo.PageData;
import cn.xl.wx.wx1901.vo.PageParam;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Security;
import java.util.List;
import java.util.Map;

/**
 *
 *   描述：用户类
 *   @ClassName UserController
 *   @Description TODO
 *   @Author luowenqin
 *   @Date 2019/3/25 11:41
 *   @Version 1.0
 **/
@Controller
@RequestMapping(value = "/user")
public class UserController {


    @Autowired
    private UserService userService;


    /**
     * 跳转到系统用户页面
     * @return
     */
    @GetMapping(value = "/gotoList")
    public  String gotoList(){
        return "/user/list";
    }



    /**
     * 获取当前登录名
     * @return
     */
    @GetMapping(value = "/getName")
    public @ResponseBody String getName(){
        String loginName = SecurityContextHolder.getContext().getAuthentication().getName();
        return loginName;
    }



    /**
     * 根据传入的序号查询单条用户信息
     * @return
     */
    @GetMapping(value = "/findOne/{id}")
    public ResponseEntity<TbUser> findOne(@PathVariable("id") Integer id) {
        TbUser tbUser = userService.findOne(id);
        return ResponseEntity.ok(tbUser);
    }






    /**
     *
     * 根据传入的序号查询所用用户信息
     * @return
     */
    @GetMapping(value = "/findAll")
    public @ResponseBody ResponseEntity<List<TbUser>> findAll() {
        List<TbUser> tbUsers = userService.findAll();
        return ResponseEntity.ok(tbUsers);
    }


    /**
     * 根据传入的名称查询当前的分页
     * @param username
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(value = "/getPageDataList")
    public @ResponseBody ResponseEntity<PageData> findPage(String username, Integer page, Integer limit){


        PageData pageData =new PageData();

        TbUser user =new TbUser();
        if (username !=null){
            user.setUsername(username);
        }
        PageParam pageParam =new PageParam();
        if (page !=null)pageParam.setPageNo(page);
        if (limit !=null)pageParam.setPageSize(limit);
        PageInfo<TbUser> pageInfo = userService.findPage(user, pageParam);
        List<TbUser> tbUsers = pageInfo.getList();
        long total = pageInfo.getTotal();





        pageData.setCode(0);
        pageData.setMsg("1");
        pageData.setCount(pageInfo.getTotal());
        pageData.setData(pageInfo.getList());
        return  ResponseEntity.ok(pageData);
    }
}
