package unit;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.jen.sen.untils.DateUtils;

public class TestThread {
	 
	 public static void main(String[] args)  {
		 TestThread ts = new TestThread();
		 ts.runMothe();
				 
	 }
	 ///newSingleThreadScheduledExecutor
	 private static void runMothe() {
		
		 ScheduledExecutorService    executorService = Executors.newScheduledThreadPool(2);
		 ScheduledFuture<?> fu = executorService.scheduleAtFixedRate(new Runnable() {
		        @Override
		        public void run() {
		        	Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {  
		        		  
		                @Override  
		                public void uncaughtException(Thread t, Throwable e) { 
		                	System.out.println("222222222");
		                    e.printStackTrace(); 
		                    
		                    runMothe();
		                }  
		            });
		        	
		          try {
		        	  System.out.println("11---:"+DateUtils.toDateTime(new Date()));
		        	
		        	  Thread.sleep(5000); 
//					for(int i=0;i<=20;i++){
//						
//						  if(i==5){
//							  int k=5/0;
//							  Thread.sleep(5000); 
							 
//						  }
//						  System.out.println("---:"+i+"==:"+DateUtils.now());
					          
//					  }
		        	  System.out.println("---:"+"==:"+DateUtils.toDateTime(new Date())+"---"+Thread.currentThread().getName());
//		        	  throw  new RuntimeException("模拟异常");
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
		       
		           
		        }
		    }, 3, 3, TimeUnit.SECONDS);
		 
		 try {
	            fu.get();
	        } catch ( Exception e) {
	            System.out.println("eeee:"+e.getMessage());
	            runMothe();
	        }finally {
	        	executorService.shutdown();
	        }
		 
	    }

	     
	 

}
