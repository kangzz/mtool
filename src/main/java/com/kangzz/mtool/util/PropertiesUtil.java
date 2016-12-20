package com.kangzz.mtool.util;



import com.kangzz.mtool.exception.UtilException;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Properties;

/**
 * 描述：Properties
 * 作者 ：kangzz
 * 日期 ：2016-12-20 19:50:52
 */
public class PropertiesUtil {

    private static PropertiesUtil propertiesUtil = new PropertiesUtil();
    private static Logger logger = Logger.getLogger(PropertiesUtil.class);
    /**
     * 获取属性key对应字符串
     * @param key
     * @param path
     * @return
     * @throws Exception
     */
    public  static String getPropValAsString(String key, String path){
        try {
            Properties prop = propertiesUtil.getPropFile(path);
            Object obj = prop.get(key);
            if(obj != null && !"".equals(obj.toString().trim())){
                return String.valueOf(obj).trim();
            }else{
                throw new UtilException("属性文件"+path+"中没有找到相应的key："+key);
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
        return "";
    }
    
    /**
     * 获取属性key对应字符串
     * @param key
     * @param path
     * @param args
     * @return
     * @throws Exception
     */
    public  static String getPropValAsString(String key, Object[] args, String path){
        try {
            Properties prop = propertiesUtil.getPropFile(path);
            Object obj = prop.get(key);
            if(obj != null && !"".equals(obj.toString().trim())){
            	String str = String.valueOf(obj).trim();
                str = MessageFormat.format(str, args);
                return str;
            }else{
                throw new UtilException("属性文件"+path+"中没有找到相应的key："+key);
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
        return "";
    }
    /**
     * 描述：设置属性
     * 作者 ：kangzz
     * 日期 ：2016-12-20 19:55:48
     */
    public static void setProperty(Object bean,String propertyName,Object value){
        try{
            PropertyUtils.setProperty(bean, propertyName, value);
        }catch (Exception e){
            throw new UtilException(e);
        }
    }
    /**
     * 描述：获取属性
     * 作者 ：kangzz
     * 日期 ：2016-12-20 19:55:48
     */
    public static <T> T getProperty(Object bean,String propertyName){
        try{
            return (T) PropertyUtils.getProperty(bean, propertyName);
        }catch (Exception e){
            throw new UtilException(e);
        }
    }
    /**
     * 描述：将对象中的执行数据拼装到map中
     * 作者 ：kangzz
     * 日期 ：2016-12-20 19:56:13
     */
    public static Map<String, Object> describe(Object bean, String...propertyNames){
        Validate.notNull(bean, "bean can't be null!");
        if (ObjectUtil.isNullOrEmpty(propertyNames)){
            try{
                return PropertyUtils.describe(bean);
            }catch (Exception e){
                throw new UtilException(e);
            }
        }
        Map<String, Object> map = MapUtil.newLinkedHashMap(propertyNames.length);
        for (String propertyName : propertyNames){
            map.put(propertyName, getProperty(bean, propertyName));
        }
        return map;
    }
    /**
     * 获取Properties文件
     * @return
     * @throws Exception
     */
    private Properties getPropFile(String path)throws Exception {
        InputStream inputStream = this.getClass().getResourceAsStream(path);
        Properties prop = new Properties();
        prop.load(inputStream);
        return prop;
    }

}
