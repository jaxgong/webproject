package nagaseiori.crontab;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleJob implements Job{

	private static final Logger logger = LoggerFactory.getLogger(SimpleJob.class);
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		 JobKey jobKey = context.getJobDetail().getKey();  
		 logger.info("SimpleJob says: " + jobKey + " executing at " + new Date());  
	}
	
}
