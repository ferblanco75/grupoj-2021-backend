package ar.edu.unq.desapp.grupoj.backenddesappapi.Aspect;

import ar.edu.unq.desapp.grupoj.backenddesappapi.service.EstatisticsService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class LogEstatisticsWebServiceAspect {

	//static Logger logger = LoggerFactory.getLogger(LogEstatisticsWebServiceAspect.class);
	@Autowired
	private EstatisticsService service;

	@Around("execution(* ar.edu.unq.desapp.grupoj.backenddesappapi.webservices..*(..))")

	public Object logWebServiceAspect(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object proceed = joinPoint.proceed();
		long executionTime = System.currentTimeMillis() - start;

		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();


		//logger.info("########## " + executionTime + " -> " +methodSignature.getMethod().getName());
		service.update(methodSignature.getMethod().getName());
		return proceed;
	}


}