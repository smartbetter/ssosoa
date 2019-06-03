package com.example.server.soa.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.*;

/**
 * json转换方面的工具类 fastjson 推荐用
 *
 * @date 2018-10-01
 */
public class JsonUtil<T> {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    /**
     * 设置全局feature
     */
    private static SerializerFeature[] features = {
            // 设置序列化配置,为null的属性也加入到json中
            //SerializerFeature.WriteMapNullValue,
    };

    private JsonUtil() {
    }

    /**
     * 将对象转换成json字符串,如果转换失败则返回null
     *
     * @param object 需要转换为json的对象
     * @return String 转换后的json字符串
     */
    public static String write2JsonStr(Object object) {
        try {
            return JSON.toJSONString(object, features);
        } catch (Exception e) {
            logger.error("JsonUtil.write2JsonStr error", e);
        }
        return StringUtils.EMPTY;
    }

    /**
     * 将json转换为对象 如果对象模版为内部类会出现问题，所以不要使用内部类
     *
     * @param json  要转换的json
     * @param clazz 要映射的类型
     * @return 转换成的目标对象，如果转换失败返回null
     */
    public static <T> T json2Object(String json, Class<T> clazz) {
        try {
            if (StringUtils.isNotBlank(json)) {
                return JSON.parseObject(json, clazz);
            }
        } catch (Exception e) {
            logger.error("JsonUtil.json2Object error, clazz:{}, json:{}", clazz.getName(), json, e);
        }
        return null;
    }

    /**
     * 将json转换为对象
     * <p>
     * <pre>
     * JsonUtil.json2Object(json, new TypeReference<HashMap<String, HashSet<String>>>(){})
     * </pre>
     *
     * @param json         要转换的json
     * @param valueTypeRef
     * @param <T>
     * @return 转换成的目标对象，如果转换失败返回null
     */
    public static <T> T json2Object(String json, TypeReference<T> valueTypeRef) {
        try {
            if (StringUtils.isNotBlank(json)) {
                return JSON.parseObject(json, valueTypeRef);
            }
        } catch (Exception e) {
            logger.error("JsonUtil.json2Object error, valueTypeRef:{}, json:{}", valueTypeRef.getType().getTypeName(), json, e);
        }
        return null;
    }

    /**
     * 将字节输入流转换为对象 如果对象模版为内部类会出现问题，所以不要使用内部类
     *
     * @param src   字节输入流
     * @param clazz 要映射的类型
     * @return 转换成的目标对象，如果转换失败返回null
     */
    public static <T> T json2Object(InputStream src, Class<T> clazz) {
        try {
            if (src != null) {
                return JSON.parseObject(src, clazz);
            }
        } catch (Exception e) {
            logger.error("JsonUtil.json2Object error, 解析InputStream异常, clazz:{}", clazz.getName(), e);
        }
        return null;
    }

    /**
     * 将json字符串转换为Map
     *
     * @param json 需要转换为Map的json字符串 {}开头结尾的
     * @return 转换后的map 如果转换失败返回null
     */
    public static Map<String, Object> json2Map(String json) {
        try {
            if (StringUtils.isNotBlank(json) && (!json.startsWith("<"))) {
                if (json.contains(":,")) {
                    json = json.replaceAll(":,", ":null,");
                }
                return JSON.parseObject(json);
            }
        } catch (Exception e) {
            logger.error("JsonUtil.json2Map error, json:{}", json, e);
        }
        return new HashMap<String, Object>();
    }

    /**
     * 将json数组转换为List<Map<String,Object>> json数组格式[{},{}]
     *
     * @param jsonArray 需要转换的json数组
     * @return 转换后的列表  如果转换失败返回null
     */
    public static List<Map<String, Object>> jsonArray2List(String jsonArray) {
        try {
            if (StringUtils.isNotBlank(jsonArray)) {
                return JSON.parseObject(jsonArray, List.class);
            }
        } catch (Exception e) {
            logger.error("JsonUtil.jsonArray2List error, jsonArray:{}", jsonArray, e);
        }
        return new ArrayList<Map<String, Object>>();
    }

    public static <T> List<T> jsonArray2List(String jsonArray, Class clazz) {
        try {
            if (StringUtils.isNotBlank(jsonArray)) {
                return JSON.parseArray(jsonArray, clazz);
            }
        } catch (Exception e) {
            logger.error("JsonUtil.jsonArray2List error, clazz:{}, jsonArray:{}", clazz.getName(), jsonArray, e);
        }
        return new ArrayList<T>();
    }

    /**
     * json转jsonP
     *
     * @param json
     * @param callback 回调函数名
     * @return
     */
    public static String json2JsonP(String json, String callback) {
        return "/**/" + callback + "(" + json + ");";
    }

    /*
    public static void main(String[] args) {
        String json = "{'name':'tom','age':'18'}";
        String jsonArray = "[{\"name\":\"tom\",\"age\":\"18\"},{\"name\":\"bob\",\"age\":\"19\"}]";
        System.out.println(write2JsonStr(""));
        System.out.println(write2JsonStr(new java.util.Date()));
        HashMap map = new HashMap();
        map.put("1", "a");
        map.put("2", null);
        System.out.println(write2JsonStr(map));
        System.out.println(json2Object(json, Map.class));
        System.out.println(json2Object(json, new TypeReference<HashMap>(){}));
        System.out.println(json2Map(json));
        System.out.println(jsonArray2List(jsonArray));
        System.out.println(jsonArray2List(jsonArray).get(0).get("name"));
        System.out.println(jsonArray2List(jsonArray, Map.class));
        System.out.println(jsonArray2List(jsonArray, Map.class));
    }*/
}
