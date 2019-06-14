package test.ujkim.spring.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing // 배치기능 활성화
@SpringBootApplication
public class SpringBatchTestApplication implements CommandLineRunner {
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job job;

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchTestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("JobId", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();
		jobLauncher.run(job, jobParameters);
	}
}
