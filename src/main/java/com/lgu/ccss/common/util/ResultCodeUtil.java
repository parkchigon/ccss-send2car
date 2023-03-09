package com.lgu.ccss.common.util;

public final class ResultCodeUtil {
	
	public final static ResultCode RC_2S000000 = new ResultCode("2S000000", "성공");
	
	public final static ResultCode RC_3C002000 = new ResultCode("3C002000", "존재하지 않는 약관");
	public final static ResultCode RC_3C002001 = new ResultCode("3C002001", "서비스약관 동의 필요");
	public final static ResultCode RC_3C002002 = new ResultCode("3C002002", "데이터약관 동의 필요");
	public final static ResultCode RC_3C002003 = new ResultCode("3C002003", "이미 존재하는 가입자");
	public final static ResultCode RC_3C002004 = new ResultCode("3C002004", "가입자 정보 없음");
	public final static ResultCode RC_3C002005 = new ResultCode("3C002005", "가입자 일시 정지");
	
	public final static ResultCode RC_3C003000 = new ResultCode("3C003000", "CCSS 세션 만료");
	public final static ResultCode RC_3C003001 = new ResultCode("3C003001", "AI 플랫폼 세션 만료");
	public final static ResultCode RC_3C003002 = new ResultCode("3C003002", "UICC 정보 인증 실패");
	public final static ResultCode RC_3C003003 = new ResultCode("3C003003", "AI 플랫폼 인증 실패");
	public final static ResultCode RC_3C003004 = new ResultCode("3C003004", "유효하지 않은 CCSSToken ");
	public final static ResultCode RC_3C003005 = new ResultCode("3C003005", "새로운 음성가이드 없음");
	public final static ResultCode RC_3C003006 = new ResultCode("3C003006", "로그인 횟수 제한");
	
	public final static ResultCode RC_3C004000 = new ResultCode("3C004000", "파라미터 오류");
	public final static ResultCode RC_3C004001 = new ResultCode("3C004001", "지원하지 않는 차량제조사");
	public final static ResultCode RC_3C004002 = new ResultCode("3C004002", "지원하지 않는 디바이스");
	public final static ResultCode RC_3C004003 = new ResultCode("3C004003", "지원하지 않는 APP");
	public final static ResultCode RC_3C004004 = new ResultCode("3C004004", "지원하지 않는 클라이언트");
	public final static ResultCode RC_3C004005 = new ResultCode("3C004005", "위치정보 복호화 실패");
	
	public final static ResultCode RC_4C005000 = new ResultCode("4S005000", "서버 내부 오류");
	public final static ResultCode RC_4C005001 = new ResultCode("4S005001", "데이터베이스 오류");
	
	public final static ResultCode RC_5N000000 = new ResultCode("5N000000", "NCAS 인증 실패");
	public final static ResultCode RC_5N000001 = new ResultCode("5N000001", "NCAS 통신 오류");

	public final static ResultCode RC_5A000000 = new ResultCode("5A000000", "AI_AUTH 인증 실패");
	public final static ResultCode RC_5A000001 = new ResultCode("5A000001", "AI_AUTH 서버 연동 실패");
	public final static ResultCode RC_5A000002 = new ResultCode("5A000002", "인포테인먼트 로그인 정보 저장 실패");
	public final static ResultCode RC_5A000003 = new ResultCode("5A000003", "인포테인먼트 로그인 정보 없음");
	public final static ResultCode RC_5A000004 = new ResultCode("5A000004", "인포테인먼트 로그아웃 실패");

	public final static ResultCode RC_5C000000 = new ResultCode("5C000000", "AI_WEATHER 조회 실패");	
	public final static ResultCode RC_5C000001 = new ResultCode("5C000001", "AI_WEATHER 서버 연동 실패");		
	
}
