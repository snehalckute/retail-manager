package retail.manager.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import retail.manager.beans.ShopDetailsBean;
import retail.manager.beans.ShopDetailsWrapperBean;
import retail.manager.beans.ShopRequestBean;
import retail.manager.beans.ShopResponseBean;
import retail.manager.beans.ShopResponseExceptionMessageBean;
import retail.manager.constants.RetailConstants;
import retail.manager.dto.AddressDetails;
import retail.manager.exception.CustomGenericException;

/**
 * @author Snehal Kute
 * @version 1.0
 * @since 12 July 2017
 * 
 *        This class holds all the helper methods for retail Shop API
 */
public class RetailHelper {

	private static final Logger logger = LoggerFactory
			.getLogger(RetailHelper.class);

	/**
	 * @param bean
	 * @return
	 * 
	 *         builds shop Details
	 */
	public static ShopDetailsBean buildShopDetails(ShopRequestBean bean) {
		logger.info("building shop details");
		ShopDetailsBean shopDetailsBean = new ShopDetailsBean();
		if (bean != null) {
			shopDetailsBean.setShopAddress(RetailConstants.ADDRESS_PART1
					+ bean.getShopAddress().getNumber()
					+ RetailConstants.ADDRESS_PART2
					+ bean.getShopAddress().getAddressLine());
			shopDetailsBean.setShopName(bean.getName());
			shopDetailsBean
					.setShopPostCode(bean.getShopAddress().getPostcode());
		}
		return shopDetailsBean;
	}

	/**
	 * @param bean
	 * @return
	 * 
	 *         builds Shop Details wrapper
	 */
	public static ShopDetailsWrapperBean buildShopDetailsWapper(
			ShopDetailsBean bean) {
		logger.info("building shop wrapper");
		ShopDetailsWrapperBean shopDetailsWrapperBean = new ShopDetailsWrapperBean();
		shopDetailsWrapperBean.setShop(bean);
		return shopDetailsWrapperBean;
	}

	/**
	 * @param origin
	 * @param destination
	 * @return
	 * 
	 *         Finds distance between two locations
	 */
	public static Double getDistance(AddressDetails origin,
			ShopDetailsBean destination) {
		Double dist = null;
		if (origin != null && destination != null
				&& StringUtils.isNotBlank(origin.getLatitude())
				&& StringUtils.isNotBlank(origin.getLongitude())
				&& StringUtils.isNotBlank(destination.getLatitude())
				&& StringUtils.isNotBlank(destination.getLongitude())) {
			logger.info("Getting distance between two locations");
			double lat1 = Double.valueOf(origin.getLatitude());
			double lon1 = Double.valueOf(origin.getLongitude());
			double lat2 = Double.valueOf(destination.getLatitude());
			double lon2 = Double.valueOf(destination.getLongitude());
			double theta = lon1 - lon2;
			dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
					+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
					* Math.cos(deg2rad(theta));
			dist = Math.acos(dist);
			dist = rad2deg(dist);
			dist = dist * 60 * 1.1515;
			// Distance in Km
			dist = dist * 1.609344;
			logger.info("Dist : " + dist);
		} else {
			throw new CustomGenericException(RetailConstants.BUS_ERROR_CODE_5,
					RetailConstants.BUS_ERROR_DESC_5,
					RetailConstants.BUS_ERROR_TYP, null);
		}
		return dist;

	}

	/**
	 * This function converts decimal degrees to radians
	 * 
	 * @param deg
	 * @return
	 */
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/**
	 * This function converts radians to decimal degrees
	 * 
	 * @param rad
	 * @return
	 */
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

	/**
	 * converts stacktrace to string
	 * 
	 * @param e
	 * @return
	 */
	public static String stackTraceToString(Throwable e) {
		StringBuilder sb = new StringBuilder();
		for (StackTraceElement element : e.getStackTrace()) {
			sb.append(element.toString());
			sb.append(RetailConstants.NEXT_LINE);
		}
		return sb.toString();
	}

	/**
	 * Logs Exception
	 * 
	 * @param bean
	 * @param e
	 */
	public static void logException(ShopResponseExceptionMessageBean bean,
			Throwable e) {
		logger.error("Exception Code : " + bean.getCode()
				+ " \n Exception Type : " + bean.getType()
				+ "\n Exception Message : " + bean.getMsg()
				+ "\n StackTrace : " + bean.getStack(), e);
	}

	/**
	 * Builds exception
	 * 
	 * @param ex
	 * @return
	 */
	public static ShopResponseBean buildExceptionResponseBean(Throwable ex) {
		ShopResponseExceptionMessageBean response = new ShopResponseExceptionMessageBean();

		if (ex != null && ex instanceof CustomGenericException) {
			CustomGenericException e = (CustomGenericException) ex;
			response.setCode(e.getErrCode());
			response.setMsg(e.getErrMsg());
			response.setType(e.getErrType());
			if (e.getThrowable() != null) {
				response.setStack(stackTraceToString(e.getThrowable()));
			}
		} else {
			response.setCode(RetailConstants.GENERIC_ERROR_CODE);
			response.setMsg(RetailConstants.GENERIC_ERROR_DESC);
			response.setType(RetailConstants.TECH_ERROR_TYP);
		}
		logException(response, ex);
		return response;
	}
}
