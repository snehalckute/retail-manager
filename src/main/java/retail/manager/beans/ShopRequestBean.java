package retail.manager.beans;

/**
 * @author Snehal Kute
 * @version 1.0
 * @since 12 July 2017
 * 
 *        This class is request bean for Shop Details
 */
public class ShopRequestBean {

	private String name;
	private ShopAddressBean shopAddressBean;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the shopAddress
	 */
	public ShopAddressBean getShopAddress() {
		return shopAddressBean;
	}

	/**
	 * @param shopAddressBean
	 *            the shopAddress to set
	 */
	public void setShopAddress(ShopAddressBean shopAddressBean) {
		this.shopAddressBean = shopAddressBean;
	}

}
