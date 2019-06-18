package test.ujkim.spring.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import test.ujkim.spring.batch.task.SimpleTask1;
import test.ujkim.spring.batch.task.SimpleTask2;

@Configuration // Spring Batch의 모든 Job은 @Configuration으로 등록하여 사용
public class SimpleJobConfiguration {

	private JobBuilderFactory jobBuilderFactory;
	
	private StepBuilderFactory stepBuilderFactory;
	
	// 생성자 형태로 DI
	public SimpleJobConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
	}

	@Bean(name = "simpleJobOne")
	public Job simpleJobOne() {
		return jobBuilderFactory.get("simpleJobOne")
				.start(simpleStep1())
				.next(simpleStep2())
				.build();
	}

	@Bean(name = "simpleJobTwo")
	public Job simpleJobTwo() {
		return jobBuilderFactory.get("simpleJobTwo")
				.flow(simpleStep1())
				.build()
				.build();
	}
	
	@Bean
	public Step simpleStep1() {
		return stepBuilderFactory.get("simpleStep1")
				.tasklet(new SimpleTask1())
				.build();
	}
	
	@Bean
	public Step simpleStep2() {
		return stepBuilderFactory.get("simpleStep2")
				.tasklet(new SimpleTask2())
				.build();
	}

//	@Bean
//	public Step simpleStep3() {
//		return stepBuilderFactory.get("simpleStep3")
//				.tasklet((contribution, chunkContext) -> {
//					log.info(">>>>>>>> simpleStep3 start..");
//					log.info(">>>>>>>> simpleStep3 done..");
//					return RepeatStatus.FINISHED;
//				})
//				.listener(new SimpleItemReaderListener())
//				.build();
//	}

}
