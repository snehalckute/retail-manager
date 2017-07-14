/**
 * 
 */
package retail.manager.service;

import java.util.List;

import retail.manager.beans.ShopDetailsBean;
import retail.manager.beans.ShopRequestBean;
import retail.manager.beans.ShopResponseBean;
import retail.manager.dto.AddressDetails;

/**
 * @author Snehal Kute
 * @version 1.0
 * @since 12 July 2017
 * 
 *        This Interface is Service contract for all Retail Service methods
 */
public interface RetailService {

	/**
	 * @param bean
	 * @return
	 * @throws Exception
	 * 
	 *             Adds new shop
	 */
	public ShopResponseBean addShop(ShopRequestBean bean);

	/**
	 * @param postcode
	 * @return
	 * @throws Exception
	 * 
	 *             get Lat and Long for provided Postcode
	 */
	public AddressDetails getLatLongPositions(String postcode);

	/**
	 * @param lat
	 * @param lng
	 * @return
	 * 
	 *         gets Nearest Shop for provided location
	 */
	public ShopResponseBean getNearestShop(String lat, String lng);

	/**
	 * @param origin
	 * @param list
	 * @return
	 * 
	 *         gets nearest location
	 */
	public ShopDetailsBean getNearestLocation(AddressDetails origin,
			List<ShopDetailsBean> list);
}