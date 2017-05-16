package org.bluedon.lightweb.frame.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bluedon.lightweb.common.entity.utils.DataGrid;
import org.bluedon.lightweb.common.entity.utils.JsonData;
import org.bluedon.lightweb.common.entity.utils.PageHelper;
import org.bluedon.lightweb.frame.entity.SysRole;
import org.bluedon.lightweb.frame.entity.SysRoleResource;
import org.bluedon.lightweb.frame.entity.SysUser;
import org.bluedon.lightweb.frame.entity.SysUserRole;
import org.bluedon.lightweb.frame.entity.TreeNode;
import org.bluedon.lightweb.frame.service.ISysRoleService;
import org.bluedon.lightweb.frame.service.ISysUserRoleService;
import org.bluedon.lightweb.frame.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sysRoleController") 
public class SysRoleController{
	
	private final static Logger log= Logger.getLogger(SysRoleController.class);

	@Autowired(required=false) 
	private ISysRoleService sysRoleService; 
	
	@Autowired
	private ISysUserRoleService sysUserRoleService;
	
	@Autowired
	private ISysUserService sysUserService;
	
	/**
	 * 列表页面
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/toList") 
	public ModelAndView toList()throws Exception{
		ModelAndView mv = new ModelAndView("sys/sysrole/sysrole_list");
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
	public DataGrid list (SysRole vo) throws Exception{
		DataGrid dataGrid = new DataGrid();
		PageHelper page = new PageHelper();
		List<SysRole> pagination = sysRoleService.findListByPage(SysRole.class,null,page);
		dataGrid.setTotal(pagination.size());
		dataGrid.setRows(pagination);
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
		ModelAndView mv = new ModelAndView("sys/sysrole/sysrole_add");
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
	public JsonData  add(@ModelAttribute SysRole po) {
		JsonData json = new JsonData();
		try {
			this.sysRoleService.save(po);
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
			this.sysRoleService.deleteIds("SysRole",ids);
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
	public ModelAndView findById(@ModelAttribute SysRole vo)throws Exception{
		ModelAndView mv = new ModelAndView("sys/sysrole/sysrole_view");
		SysRole po = this.sysRoleService.selectByPrimaryKey(SysRole.class,vo.getId().toString());
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
	public ModelAndView editById(@ModelAttribute SysRole po)throws Exception{
		ModelAndView mv = new ModelAndView("sys/sysrole/sysrole_edit");
		po = this.sysRoleService.selectByPrimaryKey(SysRole.class,po.getId().toString());
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
	public JsonData editSubmit(@ModelAttribute SysRole po)throws Exception{
		JsonData json = new JsonData();
		try {
			this.sysRoleService.update(po);
			json.setSuccess(true);
			json.setMsg("修改成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	@RequestMapping("/toUserRoleTree") 
	public ModelAndView toUserRoleTree(Integer userId)throws Exception{
		ModelAndView mv = new ModelAndView("sys/sysrole/sysrole_userroles_tree");
		if(null!=userId){
			mv.addObject("userId",userId);
 			StringBuffer roleId =new StringBuffer();
 			
 			Map<String, Object> map = new HashMap<>();
 			map.put("userId", userId);
			List<SysUserRole> list = sysUserRoleService.selectByEntry(SysUserRole.class, map);
			if(null!=list&&list.size()>0){
				for(SysUserRole ur:list){
					roleId.append(ur.getRoleId()+",");
				}
			}
			mv.addObject("roleId", roleId.toString());
		}
 
		return mv;
	}
	
	@RequestMapping("/toUserTree") 
	public ModelAndView toUserTree(Integer roleId,Integer roleType)throws Exception{
		ModelAndView mv = new ModelAndView("sys/sysrole/sysrole_user_tree");
		if(null!=roleId){
			mv.addObject("roleId",roleId);
			mv.addObject("roleType",roleType);
 			StringBuffer userId =new StringBuffer();
 
 			Map<String, Object> map = new HashMap<>();
 			map.put("roleId", roleId);
			List<SysUserRole> list = sysUserRoleService.selectByEntry(SysUserRole.class, map);
			if(null!=list&&list.size()>0){
				for(SysUserRole ur:list){
					userId.append(ur.getUserId()+",");
				}
			}
			mv.addObject("userId", userId.toString());
		}
 
		return mv;
	}
	
	@RequestMapping("/loadRoleTree") 
	@ResponseBody
	public List<TreeNode> loadGenerlRoleTree(String roleId){
	   
		List<TreeNode> treenodes = new ArrayList<TreeNode>();
		//只读取通用角色
		List<SysRole> list = this.sysRoleService.findListAll(SysRole.class);
		for(SysRole sysRole :list){
			TreeNode treeNode = new TreeNode(); 
			treeNode.setId(sysRole.getId()+"");
			treeNode.setText(sysRole.getRoleName());
			treeNode.setpId("0");
			treeNode.setIsParent(false);
			if(roleId.indexOf(sysRole.getId()+"")>=0){
				treeNode.setChecked(true);
			} 
			treenodes.add(treeNode);
		}
		return treenodes;
	}
	@RequestMapping("/loadUserTree") 
	@ResponseBody
	public List<TreeNode> loadUserTree(String userId,Integer roleType){
		
		List<TreeNode> treenodes = new ArrayList<TreeNode>();
		//读取用户
		Map<String, Object> map = new HashMap<>();
		map.put("userType", roleType);
		List<SysUser> list = this.sysUserService.selectByEntry(SysUser.class, map);
		for(SysUser sysUser :list){
			TreeNode treeNode = new TreeNode(); 
			treeNode.setId(sysUser.getId()+"");
			treeNode.setText(sysUser.getUserName());
			treeNode.setpId("0");
			treeNode.setIsParent(false);
			if(userId.indexOf(sysUser.getId()+"")>=0){
				treeNode.setChecked(true);
			} 
			treenodes.add(treeNode);
		}
		return treenodes;
	}
	
	@RequestMapping("/toAuthorization") 
	@ResponseBody
	public JsonData toAuthorization(@RequestBody SysRoleResource[] resources){
		JsonData jsonData = new JsonData();
		try {
			this.sysRoleService.setRoleResources(resources);
			jsonData.setSuccess(true);
		} catch (Exception e) {
			jsonData.setSuccess(false);
		}
	
		return jsonData;
	}
}