/**
 * 
 */
package retail.manager.constants;

/**
 * @author Snehal Kute
 * @version 1.0
 * @since 12 July 2017
 * 
 *        This class Holds all constants for the API
 */
public class RetailConstants {

	public static final String CACHE_NAME = "retailCache";

	public static final String TECH_ERROR_TYP = "E";

	public static final String TECH_ERROR_CODE_1 = "Tech_001";
	public static final String TECH_ERROR_DESC_1 = "Exception Occured in Google service";

	public static final String TECH_ERROR_CODE_2 = "Tech_002";
	public static final String TECH_ERROR_DESC_2 = "Data Persistance Exception";

	public static final String TECH_ERROR_CODE_3 = "Tech_002";
	public static final String TECH_ERROR_DESC_3 = "Null/Empty result recieved from Google service";

	public static final String BUS_ERROR_TYP = "B";

	public static final String BUS_ERROR_CODE_1 = "BUS_001";
	public static final String BUS_ERROR_DESC_1 = "Insufficient Data provided";

	public static final String BUS_ERROR_CODE_2 = "BUS_002";
	public static final String BUS_ERROR_DESC_2 = "Shop Name is Null/Empty";

	public static final String BUS_ERROR_CODE_3 = "BUS_003";
	public static final String BUS_ERROR_DESC_3 = "Insufficient/Incorrect Data provided";

	public static final String BUS_ERROR_CODE_4 = "BUS_004";
	public static final String BUS_ERROR_DESC_4 = "No Shop is available to locate";

	public static final String BUS_ERROR_CODE_5 = "BUS_005";
	public static final String BUS_ERROR_DESC_5 = "Insufficient Data to calculate distance";

	public static final String ADDRESS_PART1 = "Shop No ";
	public static final String ADDRESS_PART2 = " , ";
	public static final String NEXT_LINE = "\n";

	public static final String GENERIC_ERROR_CODE = "000000";
	public static final String GENERIC_ERROR_DESC = "Generic Exception occured";

}