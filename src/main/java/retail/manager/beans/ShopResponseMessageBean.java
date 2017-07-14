package retail.manager.beans;

/**
 * @author Snehal Kute
 * @version 1.0
 * @since 12 July 2017
 * 
 *        This class is response bean for Adding shop to the Api
 */
public class ShopResponseMessageBean extends ShopResponseBean {

	private String message;

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
