package com.onkar.employeedetails.aspects;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect 
{
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("within(com.onkar.employeedetails.contollers.EmployeeController)")
	public void controller() {
	}
	
	@Before("controller()")
	public void logBefore(JoinPoint joinPoint) 
	{

		log.debug("Entering in Method :  " + joinPoint.getSignature().getName());
		log.debug("Class Name :  " + joinPoint.getSignature().getDeclaringTypeName());
		log.debug("Arguments :  " + Arrays.toString(joinPoint.getArgs()));
		log.debug("Target class : " + joinPoint.getTarget().getClass().getName());
	}
	
	@AfterReturning(pointcut = "controller()", returning = "result")
	public void logAfter(JoinPoint joinPoint, Object result) 
	{
		String returnValue = this.getValue(result);
		log.debug("Method Return value : " + returnValue);
	}
	
	@AfterThrowing(pointcut = "controller()", throwing = "exception")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) 
	{
		log.error("An exception has been thrown in " + joinPoint.getSignature().getName() + " ()");
		log.error("Cause : " + exception.getCause());
	}

	private String getValue(Object result) 
	{
		if (result != null)
			return result.toString();
		return null;
	}

}
