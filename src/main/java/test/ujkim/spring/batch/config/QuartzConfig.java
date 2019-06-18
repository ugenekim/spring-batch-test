package test.ujkim.spring.batch.config;

import java.io.IOException;
import java.util.Properties;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import test.ujkim.spring.batch.job.CustomQuartzJob;

@Configuration
public class QuartzConfig {
	
	private JobLauncher jobLauncher;
	private JobLocator jobLocator;
	
	public QuartzConfig(JobLauncher jobLauncher, JobLocator jobLocator) {
		this.jobLauncher = jobLauncher;
		this.jobLocator = jobLocator;
	}
	
    @Bean
    public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry) {
        JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
        jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
        return jobRegistryBeanPostProcessor;
    }
 
 
    @Bean
    public JobDetail jobOneDetail() {
        //Set Job data map
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("jobName", "simpleJobOne");
        jobDataMap.put("jobLauncher", jobLauncher);
        jobDataMap.put("jobLocator", jobLocator);
         
        return JobBuilder.newJob(CustomQuartzJob.class)
                .withIdentity("simpleJobOne")
                .setJobData(jobDataMap)
                .storeDurably()
                .build();
    }	
    
    @Bean
    public JobDetail jobTwoDetail() {
        //Set Job data map
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("jobName", "simpleJobTwo");
        jobDataMap.put("jobLauncher", jobLauncher);
        jobDataMap.put("jobLocator", jobLocator);
         
        return JobBuilder.newJob(CustomQuartzJob.class)
                .withIdentity("simpleJobTwo")
                .setJobData(jobDataMap)
                .storeDurably()
                .build();
    }	
    
//    @Bean
//    public JobDetail jobThreeDetail() {
//        //Set Job data map
//        JobDataMap jobDataMap = new JobDataMap();
//        jobDataMap.put("jobName", "validationProcessJob");
//        jobDataMap.put("jobLauncher", jobLauncher);
//        jobDataMap.put("jobLocator", jobLocator);
//         
//        return JobBuilder.newJob(CustomQuartzJob.class)
//                .withIdentity("validationProcessJob")
//                .setJobData(jobDataMap)
//                .storeDurably()
//                .build();
//    }	
    
    @Bean
    public Trigger jobOneTrigger()
    {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
                .simpleSchedule()
                .withIntervalInSeconds(10)
                .repeatForever();
 
        return TriggerBuilder
                .newTrigger()
                .forJob(jobOneDetail())
                .withIdentity("jobOneTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
    
    @Bean
    public Trigger jobTwoTrigger()
    {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
                .simpleSchedule()
                .withIntervalInSeconds(10)
                .repeatForever();
 
        return TriggerBuilder
                .newTrigger()
                .forJob(jobTwoDetail())
                .withIdentity("jobTwoTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
    
//    @Bean
//    public Trigger jobThreeTrigger()
//    {
//        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
//                .simpleSchedule()
//                .withIntervalInSeconds(10)
//                .repeatForever();
// 
//        return TriggerBuilder
//                .newTrigger()
//                .forJob(jobTwoDetail())
//                .withIdentity("jobThreeTrigger")
//                .withSchedule(scheduleBuilder)
//                .build();
//    }
    
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException
    {
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
//        scheduler.setTriggers(jobOneTrigger(), jobTwoTrigger(), jobThreeTrigger());
        scheduler.setTriggers(jobOneTrigger(), jobTwoTrigger());
        scheduler.setQuartzProperties(quartzProperties());
//        scheduler.setJobDetails(jobOneDetail(), jobTwoDetail(), jobThreeDetail());
        scheduler.setJobDetails(jobOneDetail(), jobTwoDetail());
        return scheduler;
    }
     
    @Bean
    public Properties quartzProperties() throws IOException
    {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }
}
