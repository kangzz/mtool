package com.kangzz.mtool.util;



import org.apache.commons.collections4.comparators.ReverseComparator;
import org.apache.commons.lang3.ArrayUtils;

import java.util.*;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;

/**
 * 描述：排序工具类
 * 作者 ：kangzz
 * 日期 ：2016-12-16 17:15:32
 */
public class SortUtil {

    /**
     * 描述：数组排序
     * 作者 ：kangzz
     * 日期 ：2016-12-16 19:00:02
     */
    public static <T> T[] sortArray(T[] arrays){
        if (null == arrays){
            return ArrayUtils.toArray();
        }
        Arrays.sort(arrays);
        return arrays;
    }
    /**
     * 描述：数组排序
     * 作者 ：kangzz
     * 日期 ：2016-12-16 19:00:02
     */
    public static <T> T[] sortArray(T[] arrays,Comparator<T>comparators){
        if (null == arrays){
            return ArrayUtils.toArray();
        }
        if (ObjectUtil.isNullOrEmpty(comparators)){
            return arrays;
        }
        Arrays.sort(arrays, comparators);
        return arrays;
    }
    /**
     * 描述：集合排序
     * 作者 ：kangzz
     * 日期 ：2016-12-16 19:00:02
     */
    public static <T extends Comparable<? super T>> List<T> sortList(List<T> list){
        if (null == list){
            return emptyList();
        }
        Collections.sort(list);
        return list;
    }
    /**
     * 描述：集合排序
     * 作者 ：kangzz
     * 日期 ：2016-12-16 19:00:02
     */
    public static <T> List<T> sortList(List<T> list,Comparator<T> comparator){
        if (null == list){
            return emptyList();
        }
        if (ObjectUtil.isNullOrEmpty(comparator)){
            return list;
        }
        Collections.sort(list, comparator);
        return list;
    }
    /**
     * 描述：Map按照Key升序
     * 作者 ：kangzz
     * 日期 ：2016-12-16 19:00:02
     */
    public static <K extends Comparable<K>, V> Map<K, V> sortMapByKeyAsc(Map<K, V> map){
        if (null == map){
            return emptyMap();
        }
        return sortMap(map, new Comparator<Map.Entry<K,V>>(){
                    public int compare(Map.Entry<K,V> mapping1,Map.Entry<K,V> mapping2){
                        if(mapping1.getKey() == null){
                            return 0;
                        }
                        return mapping1.getKey().compareTo(mapping2.getKey());
                    }});
    }
    /**
     * 描述：Map按照Key倒序升序
     * 作者 ：kangzz
     * 日期 ：2016-12-16 19:00:02
     */
    public static <K extends Comparable<K>, V> Map<K, V> sortMapByKeyDes(Map<K, V> map){
        if (null == map){
            return emptyMap();
        }
        return sortMap(map, new ReverseComparator<Map.Entry<K, V>>(new Comparator<Map.Entry<K,V>>(){
            public int compare(Map.Entry<K,V> mapping1,Map.Entry<K,V> mapping2){
                if(mapping1.getKey() == null){
                    return 0;
                }
                return mapping1.getKey().compareTo(mapping2.getKey());
            }}));
    }
    /**
     * 描述：Map按照Value倒序升序
     * 作者 ：kangzz
     * 日期 ：2016-12-16 19:00:02
     */
    public static <K , V extends Comparable<V>> Map<K, V> sortMapByValueAsc(Map<K, V> map){
        if (null == map){
            return emptyMap();
        }
        return sortMap(map, new Comparator<Map.Entry<K,V>>(){
            public int compare(Map.Entry<K,V> mapping1,Map.Entry<K,V> mapping2){
                if(mapping1.getValue() == null){
                    return 0;
                }
                return mapping1.getValue().compareTo(mapping2.getValue());
            }});
    }
    /**
     * 描述：Map按照Value倒序升序
     * 作者 ：kangzz
     * 日期 ：2016-12-16 19:00:02
     */
    public static <K , V  extends Comparable<V>> Map<K, V> sortMapByValueDes(Map<K, V> map){
        if (null == map){
            return emptyMap();
        }
        return sortMap(map, new ReverseComparator<Map.Entry<K, V>>(new Comparator<Map.Entry<K,V>>(){
            public int compare(Map.Entry<K,V> mapping1,Map.Entry<K,V> mapping2){
                if(mapping1.getValue() == null){
                    return 0;
                }
                return mapping1.getValue().compareTo(mapping2.getValue());
            }}));
    }
    /**
     * 描述：排序
     * 作者 ：kangzz
     * 日期 ：2016-12-16 19:01:47
     */
    public static <K, V> Map<K, V> sortMap(Map<K, V> map,Comparator<Map.Entry<K, V>> mapEntryComparator){
        if (null == map){
            return emptyMap();
        }
        List<Map.Entry<K, V>> mapEntryList = CollectionUtil.newArrayList(map.entrySet());
        return CollectionUtil.toMap(sortList(mapEntryList, mapEntryComparator));
    }

}
