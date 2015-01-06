package nagaseiori.commons.utils;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisOperations;

import com.alibaba.fastjson.JSON;

public class SimpleSpringDataRedisUtil{

	private static RedisOperations<String,String> redisOperations;

	public static void setRedisOperations(RedisOperations<String,String> redisOperations) {
		SimpleSpringDataRedisUtil.redisOperations = redisOperations;
	}
	
	public static void set2Json(String key, Object value){
		String valueJson = JSON.toJSONString(value);
		redisOperations.opsForValue().set(key, valueJson);
	}
	
	public static void set2Json(String key, Object value, long timeout, TimeUnit unit){
		String valueJson = JSON.toJSONString(value);
		redisOperations.opsForValue().set(key, valueJson, timeout, unit);
	}
	
	public static <T> T getJavaObject(String key, Class<T> clazz){
		String value = redisOperations.opsForValue().get(key);
		if(value != null){
			return JSON.parseObject(value, clazz);
		}
		return null;
	}

	
}
