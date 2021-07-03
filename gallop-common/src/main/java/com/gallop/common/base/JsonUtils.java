package com.gallop.common.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author leechenxiang
 * @version V1.0
 * @Title: JsonUtils.java
 * @Package com.lee.utils
 * @Description: 自定义响应结构, 转换类
 * Copyright: Copyright (c) 2016
 * Company:Nathan.Lee.Salvatore
 * @date 2016年4月29日 下午11:05:03
 */
public class JsonUtils {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转换成json字符串。
     * <p>Title: pojoToJson</p>
     * <p>Description: </p>
     *
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {
        try {
            String string = MAPPER.writeValueAsString(data);
            return string;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json结果集转化为对象
     *
     * @param jsonData json数据
     * @param clazz    对象中的object类型
     * @return
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> clazz) {
        try {
            T t = MAPPER.readValue(jsonData, clazz);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T parseObject(String body, String field, Class<T> clazz) {
        JsonNode node = null;
        try {
            node = MAPPER.readTree(body);
            node = node.get(field);
            return MAPPER.treeToValue(node, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object toNode(String json) {
        if (json == null) {
            return null;
        }
        try {
            JsonNode jsonNode = MAPPER.readTree(json);
            return jsonNode;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Map<String, String> toMap(String data) {
        try {
            return MAPPER.readValue(data, new TypeReference<Map<String, String>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json数据转换成pojo对象list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     *
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            List<T> list = MAPPER.readValue(jsonData, javaType);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String parseString(String body, String field) {
        JsonNode node = null;
        try {
            node = MAPPER.readTree(body);
            JsonNode leaf = node.get(field);
            if (leaf != null)
                return leaf.asText();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<String> parseStringList(String body, String field) {
        JsonNode node = null;
        try {
            node = MAPPER.readTree(body);
            JsonNode leaf = node.get(field);

            if (leaf != null)
                return MAPPER.convertValue(leaf, new TypeReference<List<String>>() {
                });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Integer parseInteger(String body, String field) {
        JsonNode node = null;
        try {
            node = MAPPER.readTree(body);
            JsonNode leaf = node.get(field);
            if (leaf != null)
                return leaf.asInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Integer> parseIntegerList(String body, String field) {
        JsonNode node = null;
        try {
            node = MAPPER.readTree(body);
            JsonNode leaf = node.get(field);

            if (leaf != null)
                return MAPPER.convertValue(leaf, new TypeReference<List<Integer>>() {
                });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Boolean parseBoolean(String body, String field) {
        JsonNode node = null;
        try {
            node = MAPPER.readTree(body);
            JsonNode leaf = node.get(field);
            if (leaf != null)
                return leaf.asBoolean();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Short parseShort(String body, String field) {
        JsonNode node = null;
        try {
            node = MAPPER.readTree(body);
            JsonNode leaf = node.get(field);
            if (leaf != null) {
                Integer value = leaf.asInt();
                return value.shortValue();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Byte parseByte(String body, String field) {
        JsonNode node = null;
        try {
            node = MAPPER.readTree(body);
            JsonNode leaf = node.get(field);
            if (leaf != null) {
                Integer value = leaf.asInt();
                return value.byteValue();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
