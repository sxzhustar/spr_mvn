package org.bluedon.lightweb.common.utils.common;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ClassPropertiesMapUtil {

	/**
	 * 遍历某个对象的字段属性为Map供查询使用
	 * @param t
	 * @return
	 */
	public static Map<String, Object> toMap(Object t) {
		Field[] fields = t.getClass().getDeclaredFields();
		Map<String, Object> map = new HashMap<>();
		for(Field field:fields){
			String name = field.getName();
			int type = field.getModifiers();
			if(Modifier.isStatic(type) || Modifier.isFinal(type) || Modifier.isTransient(type)){
				continue;
			}
			field.setAccessible(true);
			Object value = null;
			try {
				value = field.get(t);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
			if(value != null && value.toString() != ""){
				map.put(name, value);
			}
		}
		return map;
	}
	
	/**
	 * 查询结果集转换为对象(对于没有Hibernate映射的)
	 * @return
	 * @throws IntrospectionException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public static void toEntity(Map<String, Object> map,Object entity) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//转换Map中key为类字段形式
		if(map == null) return;
		Map<String, String> kMap = new HashMap<>();
		for (Entry<String, Object> entry : map.entrySet()) {
			String newKey = entry.getKey().replace("_", "").toLowerCase();
			kMap.put(newKey,entry.getKey());
		}
		
		Field[] fields = entity.getClass().getDeclaredFields();
		for (Field field : fields) {
			int type = field.getModifiers();
			if(Modifier.isStatic(type) || Modifier.isFinal(type) || Modifier.isTransient(type)){
				continue;
			}
			PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), entity.getClass());
			Method method = descriptor.getWriteMethod();
			if(method != null){
				String fieldName = field.getName().toLowerCase();
				Object args = map.get(kMap.get(fieldName));
				method.invoke(entity, args);
			}
		}
	}
}
