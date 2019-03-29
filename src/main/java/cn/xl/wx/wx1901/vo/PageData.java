package cn.xl.wx.wx1901.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author luowenqin
 * @create 2019-03-04 15:27
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageData  implements Serializable {


    private  Integer code ;//请求状态  默认为0



    private  String msg ;  //提示信息


    private  Long count;  //数据总条数


    private List data;  //数据

}
