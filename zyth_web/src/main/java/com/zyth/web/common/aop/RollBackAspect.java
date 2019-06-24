package com.zyth.web.common.aop;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.zyth.web.common.exception.SyncException;


public class RollBackAspect {

	private static Logger logger = Logger.getLogger(RollBackAspect.class);

	public void doAfter(JoinPoint jp) {
		logger.info("log Ending method: "
                + jp.getTarget().getClass().getName() + "."
                + jp.getSignature().getName());
    }

	@Resource
	private DataSourceTransactionManager transactionManager;

	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long time = System.currentTimeMillis();
        Object retVal = null;

        //获取事务
        DefaultTransactionDefinition def =new DefaultTransactionDefinition();
        TransactionStatus status=transactionManager.getTransaction(def);
        try{
        	 retVal = pjp.proceed();

        	 //事务执行
        	 transactionManager.commit(status);
        } catch (SyncException e){
            if(retVal == null){
            	Map<String,Integer> reultMap  = new HashMap<String,Integer>();

            	retVal = reultMap;
            }
        	//事务回滚
        	transactionManager.rollback(status);
        	logger.error("SyncException:----------------------",e);
        }
        catch(RuntimeException e){
            if(retVal == null){

            }
        	//事务回滚
        	transactionManager.rollback(status);
        	logger.error("RuntimeException----------------------",e);
        }catch(Exception e){
        	//事务回滚
        	transactionManager.rollback(status);
        	logger.debug("update----------------------",e);
        	e.printStackTrace();
        }

        time = System.currentTimeMillis() - time;

        return retVal;
    }

    public void doBefore(JoinPoint jp) {
        logger.info("log Begining method: "
                + jp.getTarget().getClass().getName() + "."
                + jp.getSignature().getName());
    }

    public void doThrowing(JoinPoint jp, Throwable ex) {
    	 logger.info("method " + jp.getTarget().getClass().getName()
                + "." + jp.getSignature().getName() + " throw exception");
    	 logger.info(ex.getMessage());
    }
}
