package unit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jTest {          

	 private static final Logger logger = LogManager.getLogger(Log4jTest.class);

            public static void main(String[] args) {
            	logger.info("This is an info log111.");
            	logger.warn("This is a warn log.111");
            	logger.error("This is a error log.111");
                        //

            } 
}