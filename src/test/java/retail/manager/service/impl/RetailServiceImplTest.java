/**
 * 
 */
package retail.manager.service.impl;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;

import retail.manager.beans.ShopAddressBean;
import retail.manager.beans.ShopDetailsBean;
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
		service = null;
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
		req.setName("McD");
		ShopAddressBean shopAddressBean = new ShopAddressBean();
		shopAddressBean.setAddressLine("Wardha");
		shopAddressBean.setNumber("999");
		shopAddressBean.setPostcode("442001");
		req.setShopAddress(shopAddressBean);
		assertNotNull(service.addShop(req));
	}
	
	/**
	 * Test method for NearestLocation
	 */
	@Test
	public void testNearestLocation() {
		AddressDetails add = new AddressDetails();
		add.setLatitude("20.719194");
		add.setLongitude("78.315712");
		List<ShopDetailsBean> shopList = new ArrayList<ShopDetailsBean>();
		ShopDetailsBean bean = new ShopDetailsBean();
		bean.setLatitude("20.719194");
		bean.setLongitude("78.315712");
		bean.setShopAddress("Add");
		bean.setShopName("ABC");
		bean.setShopPostCode("442001");
		shopList.add(bean);
		assertNotNull(service.getNearestLocation(add, shopList));
	}
	
	/**
	 * Test method for NearestShop
	 */
	@Test
	public void testNearestShop() {
		assertNotNull(service.getNearestShop("20.719194", "78.315712"));
	}
	
	/**
	 * Test method for LatLongPositions
	 */
	@Test
	public void testLatLongPositions() {
		assertNotNull(service.getLatLongPositions("442001"));
	}
}
