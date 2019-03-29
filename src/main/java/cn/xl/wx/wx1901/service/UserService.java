package cn.xl.wx.wx1901.service;

import cn.xl.wx.wx1901.entity.TbUser;
import cn.xl.wx.wx1901.vo.PageParam;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author luowenqin
 * @create 2019-03-25 11:33
 */

public interface UserService {


    /**
     * 查询所用用户集合数据
     * @return
     */
    public List<TbUser> findAll();


    /**
     * 单条数据查询
     * @param id
     * @return
     */
    public TbUser findOne(Integer id);


    /**
     * 根据当前传入的用户名单条数据
     * @param username
     * @return
     */
    public TbUser findUsername(String username);

    /**
     * 分页查询
     * @param user
     * @param pageParam
     * @return
     */
    public PageInfo<TbUser> findPage(TbUser user, PageParam pageParam);
}
