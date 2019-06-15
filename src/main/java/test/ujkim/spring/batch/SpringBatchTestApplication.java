package test.ujkim.spring.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableBatchProcessing // 배치기능 활성화
@SpringBootApplication
@EnableScheduling
public class SpringBatchTestApplication implements CommandLineRunner {
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	@Qualifier("simpleJob")
	private Job simplJob;
	
	@Autowired
	@Qualifier("validationProcessJob")
	private Job validationProcessJob;

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchTestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		JobParameters jobParameters = new JobParametersBuilder()
//				.addString("JobId", String.valueOf(System.currentTimeMillis()))
//				.toJobParameters();
//		jobLauncher.run(simplJob, jobParameters);
//		jobLauncher.run(validationProcessJob, jobParameters);
	}
	
	@Scheduled(cron = "*/5 * * * * *")
	public void run1() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("JobId", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();
		jobLauncher.run(simplJob, jobParameters);
	}
	
	@Scheduled(cron = "*/5 * * * * *")
	public void run2() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("JobId", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();
		jobLauncher.run(validationProcessJob, jobParameters);
	}
}
