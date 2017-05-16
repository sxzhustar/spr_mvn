package org.bluedon.lightweb.frame.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.bluedon.lightweb.common.entity.utils.DataGrid;
import org.bluedon.lightweb.common.entity.utils.JsonData;
import org.bluedon.lightweb.common.entity.utils.PageHelper;
import org.bluedon.lightweb.common.utils.common.PinyinUtil;
import org.bluedon.lightweb.frame.entity.SysUser;
import org.bluedon.lightweb.frame.entity.SysUserRole;
import org.bluedon.lightweb.frame.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sysUserController") 
public class SysUserController{
	
	private final static Logger log= Logger.getLogger(SysUserController.class);

	@Autowired(required=false) 
	private ISysUserService sysUserService; 
	
	/**
	 * 列表页面
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/toList") 
	public ModelAndView toList()throws Exception{
		ModelAndView mv = new ModelAndView("sys/sysuser/sysuser_list");
		return mv;
	}

	/**
	 * 请求列表数据
	 * @param request
	 * @param vo 查询对象
	 * @param rows  每页显示多少行
	 * @param page  当前页码
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list") 
	@ResponseBody
	public DataGrid list (SysUser vo) throws Exception{
		DataGrid dataGrid = new DataGrid();
		PageHelper page = new PageHelper();
		List<SysUser> list = sysUserService.findListByPage(page);
		dataGrid.setTotal(list.size());
		dataGrid.setRows(list);
		return dataGrid;
	}
		
	/**
	 * 打开新增页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toAdd") 
	public ModelAndView  toAdd() throws Exception{
		log.debug("打开新增页面");
		ModelAndView mv = new ModelAndView("sys/sysuser/sysuser_add");
		return mv;
	}
	
	/**
	 * 新增方法
	 * @param request
	 * @param po
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/add") 
	@ResponseBody
	public JsonData  add(@ModelAttribute SysUser po) {
		JsonData json = new JsonData();
		try {
			this.sysUserService.save(po);
			json.setSuccess(true);
			json.setMsg("添加成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 根据ID删除对象
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/deleteById")
	@ResponseBody
	public JsonData deleteById(String  ids) {
		JsonData json = new JsonData();
		try {
			this.sysUserService.deleteIds("SysUser", ids);
			json.setSuccess(true);
			json.setMsg("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 根据ID找到对象
	 * @param request
	 * @param vo
	 * @return
	 */
	@RequestMapping("/findById")
	public ModelAndView findById(@ModelAttribute SysUser vo)throws Exception{
		ModelAndView mv = new ModelAndView("sys/sysuser/sysuser_view");
		SysUser po = this.sysUserService.selectByPrimaryKey(SysUser.class,vo.getId().toString());
		mv.addObject("vo", po);
		return mv;
	}

	/**
	 * 跳转到更新页面
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/editById")
	public ModelAndView editById(@ModelAttribute SysUser po)throws Exception{
		ModelAndView mv = new ModelAndView("sys/sysuser/sysuser_edit");
		po = this.sysUserService.selectByPrimaryKey(SysUser.class,po.getId().toString());
		mv.addObject("vo", po);
		return mv;
	}
	
	/**
	 * 更新提交
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/editSubmit")
	@ResponseBody
	public JsonData editSubmit(@ModelAttribute SysUser po)throws Exception{
		JsonData json = new JsonData();
		try {
			this.sysUserService.update(po);
			json.setSuccess(true);
			json.setMsg("修改成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	@RequestMapping("/toSetUserRoles")
	@ResponseBody
	public JsonData toSetUserRoles(@RequestBody SysUserRole[] sysUserRoles){
		JsonData jsonData = new JsonData();
		try {
//			this.sysUserService.setUserRole(sysUserRoles);
			jsonData.setSuccess(true);
		} catch (Exception e) {
			jsonData.setSuccess(false);
		}
	
		return jsonData;
	}
	@RequestMapping("/toSetUsers")
	@ResponseBody
	public JsonData toSetUsers(@RequestBody SysUserRole[] sysUserRoles){
		JsonData jsonData = new JsonData();
		try {
//			this.sysUserService.setUsersRole(sysUserRoles);
			jsonData.setSuccess(true);
		} catch (Exception e) {
			jsonData.setSuccess(false);
		}
		
		return jsonData;
	}
	
	@RequestMapping("/findJsonById")
	@ResponseBody
	public JsonData findJsonById(@ModelAttribute SysUser po)throws Exception{
		JsonData json = new JsonData();
		try {
			po = this.sysUserService.selectByPrimaryKey(SysUser.class,po.getId().toString());
			json.setSuccess(true);
			json.setObj(po);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	* Description:获得中文字的拼音    
	* @Title: getUserAccount  
	* @author Jalf
	* @since 2016年6月6日 上午11:52:06
	* @param name
	* @param type
	* @return
	* Copyright  blueDon All right reserved.
	 */
	@RequestMapping("/getUserAccount")
	@ResponseBody
	public JsonData getUserAccount(String name){
		JsonData jsonData=new JsonData();
		try {
			String pinyinForName = PinyinUtil.getPinyinForName(name);
			jsonData.setSuccess(true);
			jsonData.setObj(pinyinForName);
		} catch (Exception e) {
			jsonData.setSuccess(false);
		}
		return jsonData;
	}
	
	
	@RequestMapping("/getIsExist")
	@ResponseBody
	public JsonData  getIsExist(String name,String type,String id){
		JsonData jsonData=new JsonData();
		boolean findIsExist = sysUserService.findIsExist(name, type,id);
		jsonData.setSuccess(findIsExist);
		return jsonData;
		
	}
	
}