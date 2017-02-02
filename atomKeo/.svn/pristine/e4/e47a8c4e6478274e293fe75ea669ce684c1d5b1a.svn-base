package com.agiledge.atom.setup.executionhandler;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AdvicesConfiguration {

	private static final Logger logger = Logger.getLogger(AdvicesConfiguration.class);
	
	/*@Autowired(required=true)
	private HttpServletRequest request;*/
	
	//HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		
	/*@Before("execution(public * *(..))")
	public void doBeforeAccessCheck() throws Throwable{
		//bussiness logic
	}*/
	
	
	/*@AfterReturning("execution(public * *(..))")
    public void doAfterReturningAccessCheck() throws Throwable {
		
	}*/
	
	//@AfterThrowing(pointcut="execution(* com.agiledge.atom..*.*(..))", throwing="ex")
	@AfterThrowing(pointcut="execution(* com.agiledge.atom.service..*.*(..))", throwing="ex")
    public void doAfterThrowingAccessCheck(Exception ex /*,HttpSession httpSession*/) {
		
		//request.getSession().getAttribute("status");
		//logger.error("Error in  ",ex);
		
		
		
	}


	
	
	
	
}
