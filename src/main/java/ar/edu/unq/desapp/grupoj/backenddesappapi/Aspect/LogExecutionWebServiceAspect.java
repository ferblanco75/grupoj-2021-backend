package ar.edu.unq.desapp.grupoj.backenddesappapi.Aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
@Order(1)
public class LogExecutionWebServiceAspect {

	static Logger logger = LoggerFactory.getLogger(LogExecutionWebServiceAspect.class);
	private ObjectMapper mapper = new ObjectMapper();

	@Around("execution(* ar.edu.unq.desapp.grupoj.backenddesappapi.webservices..*(..))")

	public Object logWebServiceAspect(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object proceed = joinPoint.proceed();
		long executionTime = System.currentTimeMillis() - start;

		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();


		logger.info("========================================");
		logger.info(new Date().toString());
		String[] names=methodSignature.getParameterNames();
		Object[] objects=joinPoint.getArgs();
		String parameters="";
		for (int i = 0; i < names.length; i++) {
			parameters+= names[i] + ": " + mapper.writeValueAsString(objects[i]) + ", ";
		}
		logger.info("ARGUMENTS: " + parameters);
		logger.info("METHOD: " + joinPoint.getSignature().getName());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		logger.info ("USER CREDENTIAL: " +mapper.writeValueAsString(auth.getPrincipal()));
		logger.info("EXECUTED IN: " + executionTime + " ms.");

		//logger.info("/////// AROUND FINISH  logExecutionTime annotation ///////");
		return proceed;
	}


}