package com.lgu.ccss.send2car.mapper;

import java.util.List;

import com.lgu.ccss.config.annontation.Master;
import com.lgu.ccss.send2car.model.Send2CarParam;
import com.lgu.ccss.common.model.ConnDeviceVO;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.MgrAppUserVO;
import com.lgu.ccss.common.model.ScheduleSetVO;
import com.lgu.ccss.common.model.Send2CarVO;

@Master
public interface DatabaseMapperOracle {
	public List<Send2CarVO> selectSend2CarList(Send2CarParam send2CarParam);
	public List<Send2CarVO> selectSend2CarListByArrivHopeTime(Send2CarParam send2CarParam);
	public int updateTargetStatus(Send2CarVO send2carVO);
	public int insertTargetSend(Send2CarVO send2carVO);
	
	public ConnDeviceVO getDeviceInfo(ConnDeviceVO connDeviceVO);
	public MembVO selectMemberbyID(MembVO membVO);
	public MgrAppUserVO selectMgrUserInfo(MgrAppUserVO mgrAppUserVO);
	public MgrAppUserVO selectCarOemInfo(MgrAppUserVO mgrAppUserVO);

	public List<ScheduleSetVO> selectScheduleList(ScheduleSetVO searchVO);
	public int updateScheduleTime(ScheduleSetVO searchVO); 
}
