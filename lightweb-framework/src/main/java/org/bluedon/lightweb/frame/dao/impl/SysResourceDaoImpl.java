package org.bluedon.lightweb.frame.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bluedon.lightweb.frame.dao.ISysResourceDao;
import org.bluedon.lightweb.frame.entity.SysResource;
import org.bluedon.lightweb.frame.entity.SysUser;
import org.springframework.stereotype.Repository;

@Repository("resourceDao")
public class SysResourceDaoImpl extends BaseDao<SysResource> implements ISysResourceDao {

	public List<SysResource> findByUser(SysUser user){
		String hql ="SELECT s.*  FROM t_sys_resource s "
				+ "INNER JOIN t_sys_role_resource r on s.id=r.resource_id "
				+ "INNER JOIN t_sys_user_role u on u.role_id = r.role_id "
				+ "WHERE u.user_id=?";
		return findWithSql(hql,new Integer[]{user.getId()},SysResource.class);
	}


	@Override
	public SysResource selectByPrimaryKey(Integer id) {
		return get(SysResource.class, id);
	}

	@Override
	public Integer deleteByIds(List<Integer> ids) {
		if(ids.size() < 1) return 0;
		StringBuilder builder = new StringBuilder("delete from SysResource where id in (");
		List<Object> list = new ArrayList<>();
		for(int i=0;i<ids.size();i++){
			builder.append("?").append(",");
			list.add(ids.get(i));
		}
		builder.deleteCharAt(builder.length()-1).append(")");
		String sql = builder.toString(); 
		Integer count = executeHql(sql, list);
		return count;
	}

	@Override
	public Integer deleteById(Integer id) {
		String sql = "delete from SysResource where id in(?)";
		Integer count = executeHql(sql, new Object[]{id});
		return count;
	}

	@Override
	public List<SysResource> selectListByParentId(Integer pId) {
		String hql = "from SysResource s where s.parentId = " + pId;
		return find(hql);
	}

	@Override
	public List<Map<String, Object>> findAuthorizationAll(Integer roleId) {
		String hql = "select DISTINCT t.*,r.role_id from t_sys_resource t "
				+ "LEFT JOIN ( SELECT m.id,m.role_id ,m.resource_id from t_sys_role_resource m "
				+ "where m.role_id = ? ) as r "
				+ "on t.id = r.resource_id order by order_num";
		return findWithSqlMulTbMulFields(hql, new Object[]{roleId});
	}

	@Override
	public void saveAndCreateRes(SysResource po, boolean createButton) {
		
	}

	@Override
	public void createButtonRes(SysResource po) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Long selectResourceReference(Integer resourceId) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT SUM(total_num) FROM ")
			.append("(SELECT COUNT(*) total_num FROM t_sys_resource r WHERE r.parent_id=? ")
		 	.append("UNION ALL ")
		 	.append("SELECT COUNT(*) total_num2 FROM  t_sys_role_resource r2 WHERE r2.resource_id=?)t_union");
		String sql = builder.toString();
		return countSql(sql, new Object[]{resourceId,resourceId});
	}


	@Override
	public List<SysResource> findAuthorizationOnly(Integer roleId,Integer pId) {
		String sql = "select * from t_sys_resource s left join t_sys_role_resource r on s.id=r.resource_id where r.role_id=? and s.parent_id=?";
		return findWithSql(sql, new Object[]{roleId,pId},SysResource.class);
	}
}
