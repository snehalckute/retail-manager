package retail.manager.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import retail.manager.beans.ShopDetailsBean;
import retail.manager.beans.ShopRequestBean;
import retail.manager.beans.ShopResponseBean;
import retail.manager.beans.ShopResponseMessageBean;
import retail.manager.beans.ShopResponseOverrideBean;
import retail.manager.constants.RetailConstants;
import retail.manager.dto.AddressDetails;
import retail.manager.exception.CustomGenericException;
import retail.manager.persist.PersistShop;
import retail.manager.properties.GlobalProperties;
import retail.manager.service.RetailService;
import retail.manager.utils.RetailHelper;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

/**
 * @author Snehal Kute
 * @version 1.0
 * @since 12 July 2017
 * 
 *        This class is the implementation of retail service
 */
@Service
public class RetailServiceImpl implements RetailService {

	private static final Logger logger = LoggerFactory
			.getLogger(RetailServiceImpl.class);

	GlobalProperties global;

	@Autowired
	public void setGlobal(GlobalProperties global) {
		this.global = global;
	}

	/**
	 * @param postcode
	 * @return
	 * @throws Exception
	 * 
	 *             get Lat and Long for provided Postcode
	 */
	public AddressDetails getLatLongPositions(String postcode) {
		AddressDetails addressDetails = new AddressDetails();
		GeoApiContext context = new GeoApiContext().setApiKey(global.getKey());
		GeocodingResult[] results = null;
		try {
			results = GeocodingApi.geocode(context, postcode).await();
		} catch (ApiException e) {
			throw new CustomGenericException(RetailConstants.TECH_ERROR_CODE_1,
					RetailConstants.TECH_ERROR_DESC_1,
					RetailConstants.TECH_ERROR_TYP, e);
		} catch (InterruptedException e) {
			throw new CustomGenericException(RetailConstants.TECH_ERROR_CODE_1,
					RetailConstants.TECH_ERROR_DESC_1,
					RetailConstants.TECH_ERROR_TYP, e);
		} catch (IOException e) {
			throw new CustomGenericException(RetailConstants.TECH_ERROR_CODE_1,
					RetailConstants.TECH_ERROR_DESC_1,
					RetailConstants.TECH_ERROR_TYP, e);
		}
		if (results != null && results.length > 0) {
			addressDetails.setPostcode(postcode);
			addressDetails.setLatitude(String
					.valueOf(results[0].geometry.location.lat));
			addressDetails.setLongitude(String
					.valueOf(results[0].geometry.location.lng));
			logger.debug("Got Location details as - Lat : "
					+ addressDetails.getLatitude() + " , Long : "
					+ addressDetails.getLongitude());
			return addressDetails;
		} else {
			throw new CustomGenericException(RetailConstants.TECH_ERROR_CODE_3,
					RetailConstants.TECH_ERROR_DESC_3,
					RetailConstants.TECH_ERROR_TYP, null);
		}
	}

	/**
	 * @param lat
	 * @param lng
	 * @return
	 * 
	 *         gets Nearest Shop for provided location
	 */
	public ShopDetailsBean getNearestShop(String lat, String lng) {
		ShopDetailsBean response = null;
		if (StringUtils.isNotBlank(lat) && StringUtils.isNotBlank(lng)) {
			logger.info("Getting nearest Shop");
			AddressDetails addressDetails = new AddressDetails();
			addressDetails.setLatitude(lat);
			addressDetails.setLongitude(lng);
			List<ShopDetailsBean> shopList = PersistShop.getShopList();
			if (shopList != null && !shopList.isEmpty()) {
				response = getNearestLocation(addressDetails, shopList);
			} else {
				throw new CustomGenericException(
						RetailConstants.BUS_ERROR_CODE_4,
						RetailConstants.BUS_ERROR_DESC_4,
						RetailConstants.BUS_ERROR_TYP, null);
			}
		} else {
			throw new CustomGenericException(RetailConstants.BUS_ERROR_CODE_3,
					RetailConstants.BUS_ERROR_DESC_3,
					RetailConstants.BUS_ERROR_TYP, null);
		}
		return response;
	}

	/**
	 * @param origin
	 * @param list
	 * @return
	 * 
	 *         gets nearest location
	 */
	public ShopDetailsBean getNearestLocation(AddressDetails origin,
			List<ShopDetailsBean> list) {
		logger.info("Getting nearest Location");
		NavigableMap<Double, ShopDetailsBean> map = new TreeMap<Double, ShopDetailsBean>();
		for (ShopDetailsBean destination : list) {
			Double d = RetailHelper.getDistance(origin, destination);
			if (d != null) {
				map.put(d, destination);
			}
		}
		logger.debug("Available Shops : " + map);
		Entry<Double, ShopDetailsBean> firstEntry = map.firstEntry();
		return firstEntry.getValue();
	}

	/**
	 * @param bean
	 * @return
	 * @throws Exception
	 * 
	 *             Adds new shop
	 */
	public ShopResponseBean addShop(ShopRequestBean req) {

		logger.info("Adding Shop");
		ShopResponseMessageBean responseNew = new ShopResponseMessageBean();
		ShopResponseOverrideBean responseUpdate = new ShopResponseOverrideBean();

		if (req != null && StringUtils.isNotBlank(req.getName())
				&& req.getShopAddress() != null
				&& StringUtils.isNotBlank(req.getShopAddress().getPostcode())) {
			ShopDetailsBean currentDetails = RetailHelper.buildShopDetails(req);
			AddressDetails addressDetails = getLatLongPositions(req
					.getShopAddress().getPostcode());
			if (addressDetails != null) {
				currentDetails.setLatitude(addressDetails.getLatitude());
				currentDetails.setLongitude(addressDetails.getLongitude());
			}
			ShopDetailsBean previousDetails = PersistShop
					.addShop(currentDetails);
			if (previousDetails == null) {
				responseNew.setMessage(global.getShopMessage());
				logger.info("New shop added");
				return responseNew;
			} else {
				responseUpdate.setCurrentAddress(RetailHelper
						.buildShopDetailsWapper(currentDetails));
				responseUpdate.setPreviousAddress(RetailHelper
						.buildShopDetailsWapper(previousDetails));
				logger.info("Updating Existing Shop");
				return responseUpdate;
			}
		} else {
			throw new CustomGenericException(RetailConstants.BUS_ERROR_CODE_1,
					RetailConstants.BUS_ERROR_DESC_1,
					RetailConstants.BUS_ERROR_TYP, null);
		}
	}

}