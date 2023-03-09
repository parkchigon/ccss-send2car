package com.lgu.common.aop;


import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SampleAdminAspect {

    private static final Logger logger = LoggerFactory.getLogger(SampleAdminAspect.class);

    /**
     * API 전처리
     * 
     * @param joinPoint
     * @throws Exception
     */
    //@Before("execution(public * com.lgu.ccss.smple.service..*.*(..))")
    @Before("execution(* com.lgu.ccss.sample.*.*(..))")
    public void commonBefore(JoinPoint joinPoint) throws Exception {
    	logger.info("&&&&&&&&&&&&&&&&& " + joinPoint.getSignature() + " START &&&&&&&&&&&&&&&&&");
/*    	
    	Object[] obj = joinPoint.getArgs();
    	
    	if (obj.length > 0) {
    		HttpServletRequest req = (HttpServletRequest) obj[0];
    		AcquainVO reqParam = (AcquainVO) obj[2];
    		logger.info("##### req ==> " + req.getHeader("acquainIdNum") + "  #####");
    		logger.info("##### reqParam ==> " + JSONObject.fromObject(reqParam) + "  #####");
    		
    		reqParam.setAcquainIdNum(req.getHeader("acquainIdNum"));
    	}
*/    
    }

    /**
     * Method 정상 실행 시
     * @param joinPoint
     * @throws Exception
     */
    @AfterReturning(pointcut ="execution(* com.lgu.sscc.sample..*doTask.*(..))", returning = "returnVal")
    public void commonAfterReturn(JoinPoint joinPoint, Map<String, Object> returnVal) throws Exception {
    	logger.info("&&&&&&&&&&&&&&&&& " + joinPoint.getSignature() + " 정상케이스 &&&&&&&&&&&&&&&&&");
    	
    	
    	
    	logger.info("&&&&&&&&&&&&&&&&& Return value : "+ returnVal + " &&&&&&&&&&&&&&&&&");
    }
    
    /**
     * Method 에외 발생 시
     * @param joinPoint
     * @throws Exception
     */
    @AfterThrowing("execution(* com.lgu.ccss.sample..*Controller.*(..))")
    public void commonAfterThrow(JoinPoint joinPoint) throws Exception {
    	logger.info("&&&&&&&&&&&&&&&&& " + joinPoint.getSignature() + " 예외 발생 &&&&&&&&&&&&&&&&&");
    	
    /*	Object[] objs = joinPoint.getArgs();
    	
    	for(Object obj : objs) {
    		if(obj instanceof HttpServletResponse) {
    			// Response 객체
    			logger.info("response");
    			HttpServletResponse response = (HttpServletResponse)obj;
    			response.getWriter().write("error!!!!!!!!!!");
    		}
    	}*/
    	/*if(obj.length > 0) {
    		logger.info("request 있음");
    		HttpServletRequest req = (HttpServletRequest)obj[0];
    	}
    	if(obj.length > 1) {
    		if(obj[1] instanceof HttpServletResponse) {
    			HttpServletResponse res = (HttpServletResponse)obj[1];
        		logger.info("response");
    		}
    	}*/
    }
    
    /**
     * API 후처리
     * 
     * @param joinPoint
     * @throws Exception
     */
    @After("execution(* com.lgu.sample..*Controller.*(..))")
    public void commonAfter(JoinPoint joinPoint) throws Exception {
    	logger.info("##### " + joinPoint.getSignature() + " END #####");
    }

}
