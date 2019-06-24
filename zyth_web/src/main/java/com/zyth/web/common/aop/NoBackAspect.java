package com.zyth.web.common.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class NoBackAspect {
	
	private static Logger logger = Logger.getLogger(NoBackAspect.class);
	
	public void doAfter(JoinPoint jp) {  
 
    }  
  
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {  
        long time = System.currentTimeMillis();  
        Object retVal = null;
        try{
        	 retVal = pjp.proceed(); 
        }catch(Exception e){
        	logger.debug("get------------------------");
        	e.printStackTrace();
        }
         
        time = System.currentTimeMillis() - time;  
       
        return retVal;  
    }  
  
    public void doBefore(JoinPoint jp) {  
       
    }  
  
    public void doThrowing(JoinPoint jp, Throwable ex) {  
       
    }  
}
