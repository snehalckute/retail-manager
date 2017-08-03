package retail.manager.persist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import retail.manager.beans.ShopDetailsBean;
import retail.manager.constants.RetailConstants;
import retail.manager.exception.CustomGenericException;

/**
 * @author Snehal Kute
 * @version 1.0
 * @since 12 July 2017
 * 
 *        This class holds In Memory EHCache for the Data persistance
 */
public class PersistShop {

	private static PersistShop singleInstance;
	private static CacheManager cm = CacheManager.getInstance();
	private static final Logger logger = LoggerFactory
			.getLogger(PersistShop.class);

	/**
	 * Static Block to initialize cache
	 */
	static {
		if (!cm.cacheExists(RetailConstants.CACHE_NAME)) {
			cm.addCache(RetailConstants.CACHE_NAME);
			logger.info("Cache - retailCache has been initialized");
		}
	}

	private PersistShop() {
	}

	/**
	 * @return provides singleton instance
	 */
	public static PersistShop getSingleInstance() {
		if (singleInstance == null) {
			synchronized (PersistShop.class) {
				if (singleInstance == null) {
					singleInstance = new PersistShop();
					logger.info("First singleton Instance of the PersistShop is being instantiated");
				}
			}
		}
		return singleInstance;
	}

	/**
	 * @return the shopList
	 */
	public static List<ShopDetailsBean> getShopList() {
		List<ShopDetailsBean> list = new ArrayList<ShopDetailsBean>();
		Cache cache = cm.getCache(RetailConstants.CACHE_NAME);
		Map<Object, Element> listMap = cache.getAll(cache.getKeys());
		List<Element> eleList = new ArrayList<Element>(listMap.values());
		for (Element el : eleList) {
			list.add((ShopDetailsBean) el.getObjectValue());
		}
		logger.debug("Shop List : " + list);
		return list;
	}

	/**
	 * @param shopList
	 *            the shopList to set
	 */
	synchronized public static ShopDetailsBean addShop(ShopDetailsBean shop) {
		ShopDetailsBean response = null;
		if (shop != null && StringUtils.isNotBlank(shop.getShopName())) {
			Cache cache = cm.getCache(RetailConstants.CACHE_NAME);
			if (cache != null) {
				if (cache.isKeyInCache(shop.getShopName())) {
					Element ele = cache.get(shop.getShopName());
					response = (ShopDetailsBean) ele.getObjectValue();
					logger.debug("Found Existing shop : " + response);
				}
				cache.put(new Element(shop.getShopName(), shop));
				logger.debug("Shop added : " + shop);
			} else {
				throw new CustomGenericException(
						RetailConstants.TECH_ERROR_CODE_2,
						RetailConstants.TECH_ERROR_DESC_2,
						RetailConstants.TECH_ERROR_TYP, null);
			}
		} else {
			throw new CustomGenericException(RetailConstants.BUS_ERROR_CODE_2,
					RetailConstants.BUS_ERROR_DESC_2,
					RetailConstants.BUS_ERROR_TYP, null);
		}
		return response;
	}

}
