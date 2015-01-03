package nagaseiori.crontab;

import java.text.ParseException;

import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class Demo {

	public void RunJob() throws SchedulerException, ParseException {
		SchedulerFactory sf = new StdSchedulerFactory();  
		Scheduler sched = sf.getScheduler();
		JobDetail job = JobBuilder.newJob(SimpleJob.class).withIdentity("job1", "group1").build();
		CronExpression cexp = new CronExpression("0 15 0 ? * *");
		CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cexp);
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").withSchedule(cronScheduleBuilder).build();
		sched.scheduleJob(job, trigger);
	}

	/**
	 * 主任务运行入口，初始化后一直存在与程序后台
	 * 
	 * @param args
	 * @throws ParseException 
	 * @throws SchedulerException 
	 */
	public static void main(String[] args) throws SchedulerException, ParseException {
		new Demo().RunJob();
	}
}
