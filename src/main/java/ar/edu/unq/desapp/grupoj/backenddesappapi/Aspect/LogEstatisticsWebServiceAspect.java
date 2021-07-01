package ar.edu.unq.desapp.grupoj.backenddesappapi.Aspect;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.FrontUser;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.StatisticsService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class LogEstatisticsWebServiceAspect {

	@Autowired
	private StatisticsService service;

	@Around("execution(* ar.edu.unq.desapp.grupoj.backenddesappapi.webservices.*.*(..)) && !@annotation(ExcludeFromMetrics)")

	public Object logWebServiceAspect(ProceedingJoinPoint joinPoint) throws Throwable {
		Object proceed = joinPoint.proceed();

		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		FrontUser userDetails = (FrontUser) auth.getPrincipal();

		service.update(methodSignature.getMethod().getName(),userDetails.getPlatformId());
		return proceed;
	}
}