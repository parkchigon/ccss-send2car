package com.lgu.ccss.common.tlo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lgu.common.collection.ExpirationListener;

public class TloExpiredMapListner<E> implements ExpirationListener<E> {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void expired(E expiredObject) {
		// TODO Auto-generated method stub
		
		logger.debug("expiredObject : " + expiredObject);
	}

}
