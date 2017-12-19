package com.liuhao.mybatis.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class PointProductProvider implements ApplicationContextAware {

	private ApplicationContext applicationContext;
	
	private String[] impls;

	private Map<Integer, String> map = new HashMap<Integer, String>() {
		{
			put(1, "pointRedBagServiceImpl");
			put(2, "pointCouponServiceImpl");
			put(3, "pointInterestCouponServiceImpl");
			put(4, "pointCashierServiceImpl");

			put(5, "pointDiscountServiceImpl");
			put(6, "pointGoodsServiceImpl");
		}
	};

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		this.impls = applicationContext.getBeanNamesForType(PointPrizeService.class);
	}

	public PointPrizeService getInstance(int catagory) {
		String implName = map.get(catagory);
		for (String impl : impls) {
			if (implName.equals(impl)) {
				return (PointPrizeService) applicationContext.getBean(impl);
			}
		}

		return null;
	}

}
