package retail.manager.exception;

/**
 * @author Snehal Kute
 * @version 1.0
 * @since 12 July 2017
 * 
 *        This class is Custom Exception class for the Retail Shop API
 */
public class CustomGenericException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String errCode;
	private String errMsg;
	private String errType;
	private Throwable throwable;

	public String getErrCode() {
		return errCode;
	}

	/**
	 * @return the errType
	 */
	public String getErrType() {
		return errType;
	}

	/**
	 * @param errType
	 *            the errType to set
	 */
	public void setErrType(String errType) {
		this.errType = errType;
	}

	/**
	 * @param errCode
	 */
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	/**
	 * @return
	 */
	public String getErrMsg() {
		return errMsg;
	}

	/**
	 * @param errMsg
	 */
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	/**
	 * @return the throwable
	 */
	public Throwable getThrowable() {
		return throwable;
	}

	/**
	 * @param throwable
	 *            the throwable to set
	 */
	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

	/**
	 * @param errCode
	 * @param errMsg
	 */
	public CustomGenericException(String errCode, String errMsg, String type,
			Throwable t) {
		this.errCode = errCode;
		this.errMsg = errMsg;
		this.errType = type;
		this.throwable = t;
	}

}
