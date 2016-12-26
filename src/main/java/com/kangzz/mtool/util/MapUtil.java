package com.kangzz.mtool.util;

import com.kangzz.mtool.exception.UtilException;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * 描述：map相关操作工具类
 * 作者 ：kangzz
 * 日期 ：2016-10-31 20:11:30
 */
public class MapUtil {

	private MapUtil() {
		//AssertionError不是必须的. 但它可以避免不小心在类的内部调用构造器. 保证该类在任何情况下都不会被实例化.
		//see 《Effective Java》 2nd
		throw new UtilException(StrUtil.format("MapUtil [{}] Can not instances for you!", getClass().getName()));
	}
	/**
	 * 描述：如果不为空 添加到map中
	 * 作者 ：kangzz
	 * 日期 ：2016-10-31 20:28:05
	 */
	public static <K, V> void putIfValueNotNull(final Map<K, V> map, final K key, final V value) {
		if (null != map && null != value) {
			map.put(key, value);
		}
	}
	/**
	 * 描述：如果不为空 添加到map中
	 * 作者 ：kangzz
	 * 日期 ：2016-10-31 20:28:05
	 */
	public static <K, V> void putAllIfNotNull(final Map<K, V> map, Map<? extends K, ? extends V> m) {
		if (null != map && null != m && !m.isEmpty()) {
			map.putAll(m);
		}
	}
	/**
	 * 描述：计算相同key的统计和值
	 * 作者 ：kangzz
	 * 日期 ：2016-10-31 20:48:46
	 */
	public static <K> Map<K, BigDecimal> putSumValue(Map<K, BigDecimal> map, K key, BigDecimal value){
		if(ObjectUtil.isNull(map)){
			throw new UtilException(StrUtil.format("MapUtil {} map Can not be null !", "putSumValue"));
		}
		if(ObjectUtil.isNull(value)){
			throw new UtilException(StrUtil.format("MapUtil {} value Can not be null !", "putSumValue"));
		}
		BigDecimal v = map.get(key);//这里不要使用 map.containsKey(key),否则会有2次  two potentially expensive operations
		map.put(key, null == v ? value : value.add(v));//Suggestion: you should care about code readability more than little performance gain in most of the time.
		return map;
	}
	/**
	 * 描述：将相同key的数据合并为List
	 * 作者 ：kangzz
	 * 日期 ：2016-10-31 20:51:42
	 */
	public static <K, V> Map<K, List<V>> putMultiValue(Map<K, List<V>> map, K key, V value){
		if(ObjectUtil.isNull(map)){
			throw new UtilException(StrUtil.format("MapUtil {} map Can not be null !", "putMultiValue"));
		}
		List<V> list = (List<V>) ObjectUtil.defaultIfNull(map.get(key), new ArrayList<V>());
		list.add(value);
		map.put(key, list);
		return map;
	}
	/**
	 * 描述：map 移除key
	 * 作者 ：kangzz
	 * 日期 ：2016-11-25 12:13:26
	 */
	public static <K, V> Map<K, List<V>> removeKey(Map<K, List<V>> map, K key){
		if(ObjectUtil.isNull(map)){
			throw new UtilException(StrUtil.format("MapUtil {} map Can not be null !", "removeKey"));
		}
		if(map.containsKey(key)){
			map.remove(key);
		}
		return map;
	}
	/**
	 * 描述：获取所有key集合
	 * 作者 ：kangzz
	 * 日期 ：2016-11-25 13:26:45
	 */
	public static <K,V> List<K> getKeys(Map<K, V> map){
		ArrayList<K> array = CollectionUtil.newArrayList();
		if(ObjectUtil.isNull(map)){
			return array;
		}
		for (K key : map.keySet()) {
			array.add(key);
		}
		return array;
	}
	/**
	 * 描述：获取所有value集合
	 * 作者 ：kangzz
	 * 日期 ：2016-11-25 13:26:45
	 */
	public static <K,V> List<V> getValues(Map<K, V> map){
		ArrayList<V> array = CollectionUtil.newArrayList();
		if(ObjectUtil.isNull(map)){
			return array;
		}
		for (V value : map.values()) {
			array.add(value);
		}
		return array;
	}
	/**
	 * 描述：获取Map内元素个数
	 * 作者 ：kangzz
	 * 日期 ：2016-11-25 13:32:06
	 */
	public static <K,V> Long getMapSize(Map<K, V> map){
		return map != null ? map.size() : 0L;
	}
	/**
	 * 描述：根据负载因子new HashMap
	 * 作者 ：kangzz
	 * 日期 ：2016-10-31 20:52:00
	 */
	public static <K, V> HashMap<K, V> newHashMap(int expectedSize){
		return new HashMap<K, V>(toInitialCapacity(expectedSize));
	}
	/**
	 * 描述：根据负载因子new LinkedHashMap
	 * 作者 ：kangzz
	 * 日期 ：2016-10-31 20:52:00
	 */
	public static <K, V> LinkedHashMap<K, V> newLinkedHashMap(int expectedSize){
		return new LinkedHashMap<K, V>(toInitialCapacity(expectedSize));
	}
	/**
	 * 描述：计算负载因子
	 * 作者 ：kangzz
	 * 日期 ：2016-10-31 20:52:58
	 */
	private static int toInitialCapacity(int size){
		if(!NumberUtil.isCompareToGT(size,0)){
			throw new UtilException(StrUtil.format("MapUtil {} size must greater than zero !", "toInitialCapacity"));
		}
		//借鉴了 google guava 的实现,不过 guava 不同版本实现不同
		//guava 19 (int) (expectedSize / 0.75F + 1.0F)
		//guava 18  expectedSize + expectedSize / 3
		//google-collections 1.0  Math.max(expectedSize * 2, 16)
		//This is the calculation used in JDK8 to resize when a putAll happens it seems to be the most conservative calculation we can make.
		return (int) (size / 0.75f) + 1;//0.75 is the default load factor
	}
	/**
	 * 描述：Map数据封装到Object中
	 * 作者 ：kangzz
	 * 日期 ：2016-12-14 10:55:24
	 */
	public static <T> T parseObject(Map<String,Object> map, Class<T> clazz) throws Exception {
		if (map == null || map.isEmpty()){
			return null;
		}
		T obj = clazz.newInstance();
		BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor property : propertyDescriptors) {
			Method setter = property.getWriteMethod();
			if (setter != null) {
				setter.invoke(obj, map.get(property.getName()));
			}
		}
		return obj;
	}
	/**
	 * 描述：Object To Map
	 * 作者 ：kangzz
	 * 日期 ：2016-12-14 11:06:40
	 */
	public static Map<String, Object> parseObjectToMap(Object obj) throws Exception {
		if(obj == null){
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor property : propertyDescriptors) {
			String key = property.getName();
			if (key.compareToIgnoreCase("class") == 0) {
				continue;
			}
			Method getter = property.getReadMethod();
			Object value = getter!=null ? getter.invoke(obj) : null;
			map.put(key, value);
		}
		return map;
	}
	/**
	 * 描述：判断map中是否存在某个key 支持map为null
	 * 作者 ：kangzz
	 * 日期 ：2016-12-26 19:12:13
	 */
	public static <T> Boolean containsKey(Map<Object,T> map, Object object){
		if(ObjectUtil.isNullOrEmpty(map)){
			return false;
		}
		return map.containsKey(object);
	}



}
