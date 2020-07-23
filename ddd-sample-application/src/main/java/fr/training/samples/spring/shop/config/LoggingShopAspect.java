package fr.training.samples.spring.shop.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingShopAspect {

	private static final Log LOGGER = LogFactory.getLog(LoggingShopAspect.class);

	@Pointcut("execution( * fr.training.samples.spring.shop.application..*.*(..))")
	private void developerIsDoingSomething() {
		// This is a simple pointcut
	}

	@Before("developerIsDoingSomething()")
	public void log(JoinPoint jp) {
		StringBuilder toLog = new StringBuilder("logging ")
				.append(jp.getSignature().toLongString())
				.append(" with args: [");
		Object[] args = jp.getArgs();
		for (Object arg : args) {
			toLog.append(arg).append(", ");
		}
		toLog.append("]");
		LOGGER.info(toLog.toString());
	}

}
