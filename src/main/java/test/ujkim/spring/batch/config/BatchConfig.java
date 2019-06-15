package test.ujkim.spring.batch.config;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class BatchConfig {

//	@Bean
//	public JobLauncher jobLauncher(ThreadPoolTaskExecutor taskExecutor, JobRepository jobRepository) {
//		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
//		jobLauncher.setTaskExecutor(taskExecutor);
//		jobLauncher.setJobRepository(jobRepository);
//		return jobLauncher;
//	}
	
	@Bean
	public JobLauncher jobLauncher() throws Exception {
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
		jobLauncher.afterPropertiesSet();
		return jobLauncher;
	}
	
	@Bean
	public ThreadPoolTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(10);
		taskExecutor.setMaxPoolSize(20);
		taskExecutor.setQueueCapacity(30);
		return taskExecutor;
	}
}
