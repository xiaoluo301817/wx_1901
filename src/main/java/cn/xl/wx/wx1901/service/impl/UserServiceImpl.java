package cn.xl.wx.wx1901.service.impl;
/**
 * @author luowenqin
 * @create 2019-03-23 9:02
 */

import cn.xl.wx.wx1901.entity.TbUser;
import cn.xl.wx.wx1901.entity.TbUserExample;
import cn.xl.wx.wx1901.mapper.UserMapper;
import cn.xl.wx.wx1901.service.UserService;
import cn.xl.wx.wx1901.vo.PageParam;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 *   @ClassName UserServiceImpl
 *   @Description TODO
 *   @Author luowenqin
 *   @Date 2019/3/23 9:02
 *   @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public List<TbUser> findAll() {
        return userMapper.selectAll();
    }

    @Override
    public TbUser findOne(Integer id) {
        TbUser user =new  TbUser();
        user.setId(id);
        TbUser tbUser = userMapper.selectOne(user);
        return tbUser;
    }


    public TbUser findUsername(String username){
        TbUser user =new  TbUser();
        user.setUsername(username);
        TbUser tbUser = userMapper.selectOne(user);
        return tbUser;
    }
    @Override
    public PageInfo<TbUser> findPage(TbUser user, PageParam pageParam) {
        PageHelper.startPage(pageParam.getPageNo(),pageParam.getPageSize());

        TbUserExample example =new TbUserExample();
        Example example1 =new Example(TbUser.class);





        TbUserExample.Criteria criteria = example.createCriteria();
        if (user !=null){
            if (user.getUsername() !=null && !"".equals(user.getUsername())){
                criteria.andUsernameLike("%"+user.getUsername()+"%");
            }
        }
        List<TbUser> tbUsers = userMapper.selectByExample(example);
        PageInfo<TbUser> pageInfo =new PageInfo<>(tbUsers);
        return  pageInfo;
    }
}
