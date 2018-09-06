package com.baoke.trade.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.common.dto.api.ShoppingCartDto;
import com.baoke.common.dto.api.ShoppingCartItemListDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.util.api.json.JsonApiUtil;

/**
 * 购物车相关
 *
 * @author zjj
 * @date 2018年6月22日 下午2:54:41
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:*.xml")
public class OrderShoppingCartServiceTest {

	@Resource
	OrderShoppingCartService orderShoppingCartService;

	/**
	 * 查询购物车
	 *
	 * @author zjj
	 * @date 2018年6月22日 下午2:54:56
	 */
	@Test
	public void testQueryShoppingCart() {
		try {
			ShoppingCartDto shoppingCartParamDto = new ShoppingCartDto();
			shoppingCartParamDto.setUserId(100006L);

			ShoppingCartItemListDto shoppingCart = orderShoppingCartService.queryShoppingCart(shoppingCartParamDto);
			System.out.println(JsonApiUtil.convertToJson(shoppingCart));

			assertNotNull("查询不为空", shoppingCart);
		} catch (Exception e) {
			fail("error");
		}
	}

	/**
	 * 添加购物车
	 *
	 * @author zjj
	 * @date 2018年6月22日 下午5:31:43
	 */
	@Test
	public void testAddShoppingCart() {
		try {

			ShoppingCartDto shoppingCartParamDto = new ShoppingCartDto();
			shoppingCartParamDto.setItemDetailId(21L);
			shoppingCartParamDto.setItemId(28L);
			shoppingCartParamDto.setNum(2);
			shoppingCartParamDto.setUserId(100006L);

			BaseDto baseDto = orderShoppingCartService.addShoppingCart(shoppingCartParamDto);

			System.out.println(baseDto);
			assertNotNull("查询数据不为空", baseDto);
		} catch (Exception e) {
			fail("error");
		}
	}

	/**
	 * 删除购物车
	 *
	 * @author zjj
	 * @date 2018年6月22日 下午6:09:19
	 */
	@Test
	public void testDeleteShoppingCart() {
		try {
			ShoppingCartDto shoppingCartParamDto = new ShoppingCartDto();
			shoppingCartParamDto.setShoppingCartIds("22,23");
			shoppingCartParamDto.setUserId(9L);

			BaseResultDto baseResultDto = orderShoppingCartService.deleteShoppingCart(shoppingCartParamDto);

			assertNotNull("查询数据不为空", baseResultDto);
			assertNotNull("查询数据不为空", baseResultDto.isSuccess());
		} catch (Exception e) {
			fail("error");
		}
	}

	/**
	 * 更新购物车
	 *
	 * @author zjj
	 * @date 2018年6月22日 下午8:57:27
	 */
	@Test
	public void testUpdateShoppingCartNum() {
		try {
			ShoppingCartDto shoppingCartParamDto = new ShoppingCartDto();
			shoppingCartParamDto.setUserId(9L);
			shoppingCartParamDto.setContent("20:5;21:1");

			BaseResultDto baseResultDto = orderShoppingCartService.updateShoppingCartNum(shoppingCartParamDto);

			assertNotNull("查询数据不为空", baseResultDto);
			assertNotNull("查询数据不为空", baseResultDto.isSuccess());
		} catch (Exception e) {
			fail("error");
		}
	}

}
