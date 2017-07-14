package retail.manager.beans;

import java.io.Serializable;

/**
 * @author Snehal Kute
 * @version 1.0
 * @since 12 July 2017
 * 
 *        This class is response bean for Shop Details
 */
public class ShopDetailsBean extends ShopResponseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String shopName;
	private String shopAddress;
	private String shopPostCode;
	private String latitude;
	private String longitude;

	/**
	 * @return the shopName
	 */
	public String getShopName() {
		return shopName;
	}

	/**
	 * @param shopName
	 *            the shopName to set
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	/**
	 * @return the shopAddress
	 */
	public String getShopAddress() {
		return shopAddress;
	}

	/**
	 * @param shopAddress
	 *            the shopAddress to set
	 */
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}

	/**
	 * @return the shopPostCode
	 */
	public String getShopPostCode() {
		return shopPostCode;
	}

	/**
	 * @param shopPostCode
	 *            the shopPostCode to set
	 */
	public void setShopPostCode(String shopPostCode) {
		this.shopPostCode = shopPostCode;
	}

	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

}
