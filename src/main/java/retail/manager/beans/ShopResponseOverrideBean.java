package retail.manager.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Snehal Kute
 * @version 1.0
 * @since 12 July 2017
 * 
 *        This class is response bean for Shop Details API when Existing shop is
 *        being Overridden in Memory
 */
public class ShopResponseOverrideBean extends ShopResponseBean {

	@JsonProperty("CurrentAddress")
	private ShopDetailsWrapperBean currentAddress;

	@JsonProperty("PreviousAddress")
	private ShopDetailsWrapperBean previousAddress;

	/**
	 * @return the currentAddress
	 */
	public ShopDetailsWrapperBean getCurrentAddress() {
		return currentAddress;
	}

	/**
	 * @param currentAddress
	 *            the currentAddress to set
	 */
	public void setCurrentAddress(ShopDetailsWrapperBean currentAddress) {
		this.currentAddress = currentAddress;
	}

	/**
	 * @return the previousAddress
	 */
	public ShopDetailsWrapperBean getPreviousAddress() {
		return previousAddress;
	}

	/**
	 * @param previousAddress
	 *            the previousAddress to set
	 */
	public void setPreviousAddress(ShopDetailsWrapperBean previousAddress) {
		this.previousAddress = previousAddress;
	}

}
