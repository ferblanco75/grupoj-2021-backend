package ar.edu.unq.desapp.grupoj.backenddesappapi.Aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogExecutionActivityAspectAnnotation {

	static Logger logger = LoggerFactory.getLogger(LogExecutionActivityAspectAnnotation.class);

	/// ANNOTATION POINTCUT////
	@Around("@annotation(ar.edu.unq.desapp.grupoj.backenddesappapi.Aspect.LogActivity)")

	public Object logExecutionTimeAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("/////// AROUND START  logExecutionTime annotation //////");
		long start = System.currentTimeMillis();
		Object proceed = joinPoint.proceed();
		long executionTime = System.currentTimeMillis() - start;
		logger.info("/////// " + joinPoint.getSignature() + " executed in " + executionTime + "ms");
		logger.info("/////// AROUND FINISH  logExecutionTime annotation ///////");
		return null;
	}


}