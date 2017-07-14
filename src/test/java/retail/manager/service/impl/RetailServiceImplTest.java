/**
 * 
 */
package retail.manager.service.impl;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;

import retail.manager.beans.ShopAddressBean;
import retail.manager.beans.ShopRequestBean;
import retail.manager.dto.AddressDetails;
import retail.manager.properties.GlobalProperties;

/**
 * @author Snehal Kute
 * @version 1.0
 * @since 12 July 2017
 * 
 */
@Category(RetailServiceImplTest.class)
public class RetailServiceImplTest {

	@Autowired
	private RetailServiceImpl service;
	
	@Before
	public void setUp() throws Exception {
		service= new RetailServiceImpl();
		GlobalProperties global = new GlobalProperties();
		global.setKey("AIzaSyAmDJYthSh2vLO3Q1HADRj8TBSJ49AksI8");
		global.setShopMessage("New shop added");
		service.setGlobal(global);
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link retail.manager.service.impl.RetailServiceImpl#getLatLongPositions(java.lang.String)}.
	 */
	@Test
	public void testGetLatLongPositions() {
		AddressDetails response = service.getLatLongPositions("442001");
		assertNotNull(response);
	}

	/**
	 * Test method for {@link retail.manager.service.impl.RetailServiceImpl#addShop(retail.manager.beans.ShopRequestBean)}.
	 */
	@Test
	public void testAddShop() {
		ShopRequestBean req = new ShopRequestBean();
		req.setName("ABC");
		ShopAddressBean shopAddressBean = new ShopAddressBean();
		shopAddressBean.setAddressLine("Wardha");
		shopAddressBean.setNumber("333");
		shopAddressBean.setPostcode("442001");
		req.setShopAddress(shopAddressBean);
		assertNotNull(service.addShop(req));
	}

}
