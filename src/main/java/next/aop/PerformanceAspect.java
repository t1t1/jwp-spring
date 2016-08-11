package next.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component // Bean 등록
@Aspect
public class PerformanceAspect {

	@Pointcut("within(next.service..*) || within(next.dao..*)")
	public void myPointcut() {
	}

	// @Around("next.aop.myPointcut()")
	@Around("myPointcut()")
	public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
		// start stopwatch
		StopWatch watch = new StopWatch();
		watch.start();
		Object retVal = pjp.proceed();
		// stop stopwatch
		watch.stop();
		System.out.println(pjp.toShortString() + ": " + watch.getTotalTimeSeconds());
		return retVal;
	}
}
