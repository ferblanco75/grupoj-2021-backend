package ar.edu.unq.desapp.grupoj.backenddesappapi.Aspect;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.FrontUser;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.StatisticsService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

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
		Description myAnnotation = methodSignature.getMethod().getAnnotation(Description.class);
		service.update(
				isNull(myAnnotation)?methodSignature.getMethod().getName():myAnnotation.value()
				,userDetails.getPlatformId());


		return proceed;
	}
}