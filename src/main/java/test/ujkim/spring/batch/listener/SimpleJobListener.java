package test.ujkim.spring.batch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleJobListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		log.info("-------------- Called beforeJob() --------------");

	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		log.info("-------------- Called afterJob() --------------");

	}

}
