package retail.manager.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
@ConfigurationProperties
public class GlobalProperties {

	private String key;
	private String shopMessage;

	/**
	 * @return the shopMessage
	 */
	public String getShopMessage() {
		return shopMessage;
	}

	/**
	 * @param shopMessage
	 *            the shopMessage to set
	 */
	public void setShopMessage(String shopMessage) {
		this.shopMessage = shopMessage;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

}