/**
 * 
 */
package retail.manager.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import retail.manager.beans.ShopRequestBean;
import retail.manager.beans.ShopResponseBean;
import retail.manager.controller.RetailController;
import retail.manager.service.RetailService;
import retail.manager.utils.RetailHelper;

/**
 * @author Snehal Kute
 * @version 1.0
 * @since 12 July 2017
 * 
 *        This class is the implementation of the Retail controller
 */
@RestController
@RequestMapping("/retail/manager/")
public class RetailControllerImpl implements RetailController {

	@Autowired
	private RetailService service;

	/**
	 * @param lat
	 * @param lng
	 * @return ShopResponseBean
	 * 
	 *         Returns nearest shop as per user provided location
	 */
	@Override
	@RequestMapping("v1/shops")
	public ShopResponseBean getNearestShop(
			@RequestParam(value = "lat") String lat,
			@RequestParam(value = "lng") String lng) {
		return service.getNearestShop(lat, lng);
	}

	/**
	 * @param req
	 * @return ShopResponseBean
	 * @throws Exception
	 * 
	 *             Adds or replaces the shop details provided by the user
	 */
	@Override
	@RequestMapping(value = "v1/shops", method = RequestMethod.POST)
	public ShopResponseBean submitShop(@RequestBody ShopRequestBean req) {
		return service.addShop(req);
	}

	/**
	 * @param ex
	 * @return
	 * 
	 *         handles all generic Exceptions
	 */
	@ExceptionHandler(Throwable.class)
	public ShopResponseBean handleAllExceptions(Throwable ex) {
		return RetailHelper.buildExceptionResponseBean(ex);
	}

	/**
	 * @return the service
	 */
	public RetailService getService() {
		return service;
	}

	/**
	 * @param service
	 *            the service to set
	 */
	public void setService(RetailService service) {
		this.service = service;
	}
}