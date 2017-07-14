/**
 * 
 */
package retail.manager.controller;

import retail.manager.beans.ShopRequestBean;
import retail.manager.beans.ShopResponseBean;

/**
 * @author Snehal Kute
 * @version 1.0
 * @since 12 July 2017
 * 
 *        This Interface holds contract of all Retail Operations
 */
public interface RetailController {

	/**
	 * @param lat
	 * @param lng
	 * @return ShopResponseBean
	 * 
	 *         Returns nearest shop as per user provided location
	 */
	public ShopResponseBean getNearestShop(String lat, String lng);

	/**
	 * @param req
	 * @return ShopResponseBean
	 * @throws Exception
	 * 
	 *             Adds or replaces the shop details provided by the user
	 */
	public ShopResponseBean submitShop(ShopRequestBean req);
}