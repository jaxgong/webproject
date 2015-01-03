package nagaseiori.commons.component;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

public class PropertiesConfig implements InitializingBean{
	
	private final static Log logger = LogFactory.getLog(PropertiesConfig.class);
	private Resource redis;
	private Map<String,String> props = new HashMap<String,String>();
	
	@Override
	public void afterPropertiesSet() throws Exception {
		reload(redis);
		logger.info("reload properties");
	}
	
	void reload(Resource resource) throws Exception{
		Properties properties = new Properties();
		properties.load(resource.getInputStream());
		for(String key : properties.stringPropertyNames()){
			props.put(key, properties.getProperty(key));
		}
	}

	public Resource getRedis() {
		return redis;
	}

	public void setRedis(Resource redis) {
		this.redis = redis;
	}

	public Map<String, String> getProps() {
		return props;
	}

}
