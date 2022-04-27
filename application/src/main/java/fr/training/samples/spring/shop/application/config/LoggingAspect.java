package fr.training.samples.spring.shop.application.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggingAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

	@Pointcut("execution(* fr.training.samples.spring.shop.application..*(..))")
	private void developerIsDoingSomething() { }

	@Around("developerIsDoingSomething()")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		Object output = pjp.proceed();
		long elapsedTime = System.currentTimeMillis() - start;
		LOGGER.info("Method "+ pjp.getSignature().getName() + " execution time: " + elapsedTime + " milliseconds.");
		return output;
	}
}