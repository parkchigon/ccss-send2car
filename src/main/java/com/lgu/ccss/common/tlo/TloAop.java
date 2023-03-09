package com.lgu.ccss.common.tlo;

import org.aspectj.lang.annotation.Aspect;

@Aspect
public class TloAop {
	
	
	/*@Pointcut("execution(* com.lgu.common.ncas.NCASQueryManager.querySubsInfo(..))")
	private void pointCutNcas() {
		
	}
	
	@Around("pointCutNcas()")
	public Object tloAopNcas(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.NCAS_SVC_CLASS, TloConst.N001);
		tlo.put(TloData.NCAS_REQ_TIME, TloData.getNowDate());
		
		String key = null;
		Object obj = null;
		try {
			key = (String)joinPoint.getArgs()[0];
			
			logger.debug("TloAop tloAopNcas key:" + key);
			
			obj = joinPoint.proceed();
			return obj;
			
		} catch(Exception ex)
		{
			logger.error("TloAop tloAopNcas Exception", ex);
		
		} finally {
			
			NCASResultData ncasData = null;
			if( obj != null )
			{
				ncasData = (NCASResultData) obj;
				tlo.put(TloData.NCAS_RESULT_CODE, ncasData.getRespCode());
			} else
			{
				tlo.put(TloData.NCAS_RESULT_CODE, NCASErrorCode.ERRORCODE_NCAS_ERROR);
			}

			tlo.put(TloData.NCAS_RESULT_CODE, NCASErrorCode.ERRORCODE_NCAS_ERROR);
			tlo.put(TloData.NCAS_RES_TIME, TloData.getNowDate());
			TloUtil.setTloData(key, tlo);
		}
		return obj;
	}
	
	@Pointcut("execution(* com.lgu.common.ai.AiPlatformIF.expirePlatformToken(..))")
	private void pointCutAIAuth() {
		
	}
	
	@Around("pointCutAIAuth()")
	public Object tloAopAIAuth(ProceedingJoinPoint joinPoint) throws Throwable {
		
		logger.debug("TloAop tloAopAIAuth call");
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.AI_AUTH_SVC_CLASS, TloConst.A008);
		tlo.put(TloData.AI_AUTH_REQ_TIME, TloData.getNowDate());
		
		String key = null;
		Object obj = null;
		try {
			key = (String)joinPoint.getArgs()[2];
			
			logger.debug("TloAop tloAopAIAuth key:" + key);
			
			obj = joinPoint.proceed();
			return obj;
			
		} catch(Exception ex)
		{
			logger.error("TloAop tloAopAIAuth Exception", ex);
		
		} finally {
			
			AiPlatform aiPlatform = null;
			if( obj != null )
			{
				aiPlatform = (AiPlatform) obj;
				tlo.put(TloData.AI_AUTH_RESULT_CODE, AuthErrorCode.RC_20000000.getCode());
			} else
			{
				tlo.put(TloData.AI_AUTH_RESULT_CODE, AuthErrorCode.RC_60000000.getCode());
			}

			tlo.put(TloData.AI_AUTH_RES_TIME, TloData.getNowDate());
			TloUtil.setTloData(key, tlo);
		}
		return obj;
	}
	
	
	@Pointcut("execution(* com.lgu.ccss.common.esb.ESBManager.getCustomerCTN(..))")
	private void pointCutESBGetCustomerCTN() {
		
	}
	
	@Around("pointCutESBGetCustomerCTN()")
	public Object tloAopESBGetCustomerCTN(ProceedingJoinPoint joinPoint) throws Throwable {
		
		logger.debug("TloAop tloAopESBGetCustomerCTN call");
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.ESB_SVC_CLASS, TloConst.ES01);
		tlo.put(TloData.ESB_REQ_TIME, TloData.getNowDate());
		
		String key = null;
		Object obj = null;
		try {
			key = (String)joinPoint.getArgs()[2];
			
			logger.debug("TloAop tloAopESBGetCustomerCTN key:" + key);
			
			obj = joinPoint.proceed();
			return obj;
			
		} catch(Exception ex)
		{
			logger.error("TloAop tloAopAIAuth Exception", ex);
		
		} finally {
			
			AiPlatform aiPlatform = null;
			if( obj != null )
			{
				aiPlatform = (AiPlatform) obj;
				tlo.put(TloData.ESB_RESULT_CODE, TloConst.ESB_SUCCESS);
			} else
			{
				tlo.put(TloData.ESB_RESULT_CODE, TloConst.ESB_FAIL);
			}

			tlo.put(TloData.ESB_RES_TIME, TloData.getNowDate());
			TloUtil.setTloData(key, tlo);
		}
		return obj;
	}*/
	
}
