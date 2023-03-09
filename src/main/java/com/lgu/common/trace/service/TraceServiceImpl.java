package com.lgu.common.trace.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lgu.common.trace.model.TraceInfoVO;


@Service("traceService")
public class TraceServiceImpl implements TraceService {
		
	//@Resource(name = "commonDao_oracle")
	//private CommonDao commonDao_oracle;
	
	public List<TraceInfoVO> getTraceInfo(){
		//return commonDao_oracle.selectList("Trace.getTraceInfo");
		return null;
	}

}
