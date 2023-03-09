package com.lgu.ccss.send2car.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lgu.ccss.common.model.AppPushQueueVO;
import com.lgu.ccss.common.model.CarPushQueueVO;
import com.lgu.ccss.common.model.ConnDeviceVO;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.MgrAppUserVO;
import com.lgu.ccss.common.model.ScheduleSetVO;
import com.lgu.ccss.common.model.Send2CarVO;
import com.lgu.ccss.send2car.model.Send2CarParam;


@Component
public class DatabaseMapper {

	@Autowired
	DatabaseMapperOracle mapperOracle;

	@Autowired
	DatabaseMapperAltibase mapperAltibase;

	// TB_SEND2CAR
	public List<Send2CarVO> selectSend2CarList(Send2CarParam send2CarParam) {
		return mapperOracle.selectSend2CarList(send2CarParam);
	}
	
	public List<Send2CarVO> selectSend2CarListByArrivHopeTime(Send2CarParam send2CarParam) {
		return mapperOracle.selectSend2CarListByArrivHopeTime(send2CarParam);
	}
	
	public int updateSend2Car(Send2CarVO send2carVO) {
		return mapperOracle.updateTargetStatus(send2carVO);
	}
	
	public int insertTargetSend(Send2CarVO send2carVO) {
		return mapperOracle.insertTargetSend(send2carVO);
	}
	
	// TB_CONN_DEVICE
	public ConnDeviceVO getDeviceInfo(ConnDeviceVO connDeviceVO) {
		return mapperOracle.getDeviceInfo(connDeviceVO);
	}
	
	// TB_MEMB
	public MembVO selectMemberbyID(MembVO membVO) {
		return mapperOracle.selectMemberbyID(membVO);
	}
	
	// TB_MGRAPP_USER
	public MgrAppUserVO selectMgrUserInfo(MgrAppUserVO mgrAppUserVO) {
		return mapperOracle.selectMgrUserInfo(mgrAppUserVO);
	}
	
	// TB_MGRAPP_USER
	public MgrAppUserVO selectCarOemInfo(MgrAppUserVO mgrAppUserVO) {
		return mapperOracle.selectCarOemInfo(mgrAppUserVO);
	}
	
	// TB_SCHEDULE_SET
	public List<ScheduleSetVO> selectScheduleList(ScheduleSetVO searchVO) {
		return mapperOracle.selectScheduleList(searchVO);
	}
	
	public int updateScheduleTime(ScheduleSetVO searchVO) {
		return mapperOracle.updateScheduleTime(searchVO);
	}
	
	// TB_CAR_PUSH_QUEUE
	public boolean insertTbCarPushQueue(CarPushQueueVO carPushQueueVO) {
		int count = mapperAltibase.insertTbCarPushQueue(carPushQueueVO);
		return (count > 0 )? true:false;
	}
	
	// TB_APP_PUSH_QUEUE
	public boolean insertTbAppPushQueue(AppPushQueueVO appPushQueueVO) {
		int count = mapperAltibase.insertTbAppPushQueue(appPushQueueVO);
		return (count > 0 )? true:false;
	}
	
}
