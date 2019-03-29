package cn.xl.wx.wx1901.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * 
 * PageParam
 * 创建人:XiaoLuo
 * 时间：2018年2月27日-下午9:04:30 
 * @version 1.0.0
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageParam implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//第几页
	private Integer pageNo=1;
	//每页多少条
	private Integer pageSize=5;
}
