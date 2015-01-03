package nagaseiori.commons.redis.serializer;

import java.nio.charset.Charset;

import org.springframework.data.redis.serializer.JavaType;
import org.springframework.data.redis.serializer.ObjectMapper;
import org.springframework.data.redis.serializer.RedisSerializer;

public class FastJson2RedisSerializer implements RedisSerializer<T> {

	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	private final JavaType javaType;

	private ObjectMapper objectMapper = new ObjectMapper();
}
