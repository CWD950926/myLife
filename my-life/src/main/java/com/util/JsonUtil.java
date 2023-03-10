package com.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import freemarker.template.utility.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author chenanlian
 */
public class JsonUtil {

    private static Logger log = LoggerFactory.getLogger(JsonUtil.class);

    private static volatile ObjectMapper objectMapper;

    /**
     * 获取单例ObjectMapper
     *
     * @return
     */
    public static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            synchronized (JsonUtil.class) {
                if (objectMapper == null) {
                    objectMapper = new ObjectMapper();
                    objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
                    objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                }
            }
        }
        return objectMapper;
    }

    /**
     * 对象转json字符串
     *
     * @param object
     * @return
     */
    public static String entity2Json(Object object) {
        try {
            return getObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 将json形式字符串转换为java实体类
     */
    public static <T> T str2Entity(String jsonStr, Class<T> clazz) {
        if (StringUtils.isBlank(jsonStr)) {
            return null;
        }
        T readValue = null;
        try {
            readValue = getObjectMapper().readValue(jsonStr, clazz);
        } catch (JsonParseException e) {
            log.error(e.getMessage(), e);
        } catch (JsonMappingException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return readValue;
    }

    /**
     * 将json形式字符串转换为java实体类
     */
    public static <T> T str2Entity(String jsonStr, TypeReference<T> clazz) {
        if (StringUtils.isBlank(jsonStr)) {
            return null;
        }
        T readValue = null;
        try {
            readValue = getObjectMapper().readValue(jsonStr, clazz);
        } catch (JsonParseException e) {
            log.error(e.getMessage(), e);
        } catch (JsonMappingException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return readValue;
    }

    /**
     * jsonNode 转实体
     *
     * @param jsonNode
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T jsonNode2Entity(JsonNode jsonNode, Class<T> clazz) {
        T readValue = getObjectMapper().convertValue(jsonNode, clazz);
        return readValue;
    }

    /**
     * 获取泛型的Collection Type
     *
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类
     * @return JavaType Java类型
     * @since 1.0
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return getObjectMapper().getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * json字符串转集合类
     *
     * @param jsonString
     * @param collectionClass
     * @param elementClasses
     * @return
     */
    public static Object str2Collection(String jsonString, Class<?> collectionClass, Class<?>... elementClasses) {
        JavaType javaType = getCollectionType(collectionClass, elementClasses);
        try {
            return getObjectMapper().readValue(jsonString, javaType);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static Object jsonNode2Collection(JsonNode jsonString, Class<?> collectionClass, Class<?>... elementClasses) {
        JavaType javaType = getCollectionType(collectionClass, elementClasses);
        try {
            return getObjectMapper().convertValue(jsonString, javaType);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }


    /**
     * jsonNode 转字符串并按字母排序
     *
     * @param node
     * @return
     */
    public static String jsonNode2SortStr(final JsonNode node) {
        Object obj;
        String json = null;
        try {
            obj = objectMapper.treeToValue(node, Object.class);
            json = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        return json;
    }

    /**
     * 字符串转JsonNode
     *
     * @param str
     * @return
     */
    public static JsonNode str2JsonNode(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        try {
            return getObjectMapper().readTree(str);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return null;
        }

    }

    /**
     * 字符串转泛型类
     * @param str
     * @param typeReference
     * @param <T>
     * @return
     */
    public static <T> T str2JsonNode(String str, TypeReference<T> typeReference) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        try {
            return getObjectMapper().readValue(str, typeReference);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return null;
        }

    }

    public static JsonNode entity2JsonNode(Object entity) {
        return objectMapper.convertValue(entity, JsonNode.class);
    }


    public static JsonNode map2JsonNode(Map<String, String> map) {

        ObjectNode jsonNodes = getObjectMapper().createObjectNode();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            jsonNodes.put(entry.getKey(), entry.getValue());
        }
        return jsonNodes;
    }

    /**
     * JsonNode转Map，只取第一层，值转为字符串
     *
     * @param jsonNode
     * @return
     */
    public static Map<String, String> jsonNode2MapStr(JsonNode jsonNode) {
        Map<String, String> map = new HashMap<>();
        if (jsonNode.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> it = jsonNode.fields();
            while (it.hasNext()) {
                Map.Entry<String, JsonNode> entry = it.next();
                map.put(entry.getKey(), entry.getValue().asText(null));
            }
        }
        return map;
    }

    /**
     * JsonNode转Map，只取第一层
     *
     * @param jsonNode
     * @return
     */
    public static Map<String, Object> jsonNode2Map(JsonNode jsonNode) {
        Map<String, Object> map = new HashMap<>();
        if (jsonNode.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> it = jsonNode.fields();
            while (it.hasNext()) {
                Map.Entry<String, JsonNode> entry = it.next();
                map.put(entry.getKey(), entry.getValue());
            }
        }
        return map;
    }

    public static void jsonLeaf(JsonNode node) {
        if (node.isValueNode()) {
            System.out.println(node.toString());
            return;
        }

        if (node.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> it = node.fields();
            while (it.hasNext()) {
                Map.Entry<String, JsonNode> entry = it.next();
                jsonLeaf(entry.getValue());
            }
        }

        if (node.isArray()) {
            Iterator<JsonNode> it = node.iterator();
            while (it.hasNext()) {
                jsonLeaf(it.next());
            }
        }
    }


    public static List<String> getStrList(JsonNode jsonNode) {
        List<String> listll = new ArrayList<>();
        if (jsonNode.isArray()) {
            for (JsonNode objNode : jsonNode) {
                String value = objNode.asText();
                listll.add(value);
            }
        }
        return listll;
    }

    /**
     * 将数据源jsonNode 按模板jsonNode输出sql语句列表
     *
     * @param data     数据来源
     * @param template json 模板
     * @return sql 语句列表
     */
    public static List<String> transform(JsonNode data, JsonNode template) {
        List<String> sqlList = new ArrayList<>();
        Iterator<String> tableIterator = template.fieldNames();
        while (tableIterator.hasNext()) {
            /*表*/
            String tableName = tableIterator.next();
            JsonNode tableNode = template.get(tableName);
            Iterator<String> fieldIterator = tableNode.fieldNames();

            StringBuilder sb = new StringBuilder();
            StringBuilder sbv = new StringBuilder();
            sb.append("INSERT INTO ");
            sb.append(tableName);
            sb.append(" (");
            sbv.append(" VALUES (");

            while (fieldIterator.hasNext()) {
                /*字段*/
                String fieldName = fieldIterator.next();
                String fieldPath = tableNode.get(fieldName).asText();
                JsonNode fileValue = findValueByPath(data, fieldPath);
                /*去除没有值或取值错误的节点*/
                if (null != fileValue && !fileValue.equals("")) {
                    sb.append(fieldName);
                    sb.append(",");
                    sbv.append(fileValue);
                    sbv.append(",");
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            sbv.deleteCharAt(sbv.length() - 1);
            sb.append(")");
            sbv.append(")");
            sb.append(sbv);

            sqlList.add(sb.toString());
        }
        return sqlList;
    }


    /**
     * 根据路径获取值
     *
     * @param jsonNode
     * @param valuePath 节点路径
     * @return
     */
    public static JsonNode findValueByPath(JsonNode jsonNode, String valuePath) {
        String[] pathMutil = valuePath.split("\\.");
        JsonNode jsonTem = jsonNode;
        for (int i = 0; i < pathMutil.length; i++) {
            jsonTem = jsonTem.path(pathMutil[i]);
        }
        return jsonTem;
    }


    /**
     * 重组json
     *
     * @param jsonNode
     * @param nodePath
     * @return
     */
    public static JsonNode jsonNodeTransform(JsonNode jsonNode, List<String> nodePath) {
        JsonNodeFactory factory = new JsonNodeFactory(false);
        ObjectNode objectNode = factory.objectNode();
        for (String path : nodePath) {
            String[] pathMutil = path.split("\\.");
            if (pathMutil.length > 1) {
                JsonNode jsonTem = jsonNode;
                for (int i = 0; i < pathMutil.length; i++) {
                    jsonTem = jsonTem.path(pathMutil[i]);
                }
                objectNode.put(path, jsonTem);
            } else {
                JsonNode jsonNodeChild = jsonNode.path(path);
                objectNode.put(path, jsonNodeChild);
            }
        }
        return objectNode;
    }

    /**
     * 获取字符串值
     *
     * @param jsonNode
     * @param key
     * @return
     */
    public static String getString(JsonNode jsonNode, String key) {
        JsonNode value = jsonNode.get(key);
        if (value == null) {
            return null;
        } else {
            return value.asText().trim();
        }
    }

    public static Integer getInt(JsonNode jsonNode, String key) {
        JsonNode value = jsonNode.get(key);
        if (value == null) {
            return null;
        } else {
            return value.asInt();
        }
    }


    /**
     * 实体对象转成Map
     *
     * @param obj 实体对象
     * @return
     */
    public static Map<String, Object> object2Map(Object obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (obj == null) {
            return map;
        }
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * Map转成实体对象
     *
     * @param map   map实体对象包含属性
     * @param clazz 实体对象类型
     * @return
     */
    public static <T> T map2Object(Map<String, Object> map, Class<T> clazz) {
        if (map == null) {
            return null;
        }
        T obj = null;
        try {
            obj = clazz.newInstance();

            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                String filedTypeName = field.getType().getName();
                if (filedTypeName.equalsIgnoreCase("java.util.date")) {
                    String datetimestamp = String.valueOf(map.get(field.getName()));
                    if (datetimestamp.equalsIgnoreCase("null")) {
                        field.set(obj, null);
                    } else {
                        field.set(obj, new Date(Long.parseLong(datetimestamp)));
                    }
                } else {
                    field.set(obj, map.get(field.getName()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}

