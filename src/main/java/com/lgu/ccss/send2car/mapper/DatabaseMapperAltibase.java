package com.lgu.ccss.send2car.mapper;

import com.lgu.ccss.common.model.AppPushQueueVO;
import com.lgu.ccss.common.model.CarPushQueueVO;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.config.annontation.Slave;


@Slave
public interface DatabaseMapperAltibase {
	int deleteDeviceSess(MembVO membVO);
	public int insertTbCarPushQueue(CarPushQueueVO carPushQueueVO);
	public int insertTbAppPushQueue(AppPushQueueVO appPushQueueVO);
}
