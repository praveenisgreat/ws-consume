package com.soap.produce.aop.api.advice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Aspect
public class LoggingAdvice {
	private static Logger logger = LogManager.getLogger(LoggingAdvice.class);

	@Pointcut("execution(* com.soap.produce.controller.*.*(..))")
	public void myPointCut() {}
	
	@Around("myPointCut()")
	public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
		ObjectMapper mapper = new ObjectMapper();
		String methodName = pjp.getSignature().getName();
		String className = pjp.getTarget().getClass().getName();
		Object[] array = pjp.getArgs();
		logger.info("Request: Class= " + className + ", Method= " + methodName + "()" + ", Arguments ="
				+ mapper.writeValueAsString(array));
		Object returnObj = pjp.proceed();
		logger.info("Response: Class= " + className + ", Method= " + methodName + "()" + ", Arguments ="
				+ mapper.writeValueAsString(returnObj));
		return returnObj;
	}
}
