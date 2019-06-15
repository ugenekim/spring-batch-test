package test.ujkim.spring.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import test.ujkim.spring.batch.listener.SimpleItemReaderListener;
import test.ujkim.spring.batch.listener.SimpleJobListener;
import test.ujkim.spring.batch.listener.SimpleStepListener;
import test.ujkim.spring.batch.task.SimpleTask1;
import test.ujkim.spring.batch.task.SimpleTask2;

@Slf4j
//@RequiredArgsConstructor
@Configuration // Spring Batch의 모든 Job은 @Configuration으로 등록하여 사용
public class SimpleJobConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean(name = "simpleJob")
	public Job simpleJob() {
		return jobBuilderFactory.get("simpleJob")
				.incrementer(new RunIdIncrementer())
				.start(simpleStep1())
				.next(simpleStep2())
				.next(simpleStep3())
				.listener(new SimpleJobListener())
				.build();
	}

	@Bean(name = "simpleStep1")
	public Step simpleStep1() {
		return stepBuilderFactory.get("simpleStep1")
				.tasklet(new SimpleTask1())
				.listener(new SimpleStepListener())
				.build();
	}
	
	@Bean(name = "simpleStep2")
	public Step simpleStep2() {
		return stepBuilderFactory.get("simpleStep2")
				.tasklet(new SimpleTask2())
				.listener(new SimpleStepListener())
				.build();
	}

	@Bean(name = "simpleStep3")
	public Step simpleStep3() {
		return stepBuilderFactory.get("simpleStep3")
				.tasklet((contribution, chunkContext) -> {
					log.info(">>>>>>>> simpleStep3 start..");
					log.info(">>>>>>>> simpleStep3 done..");
					return RepeatStatus.FINISHED;
				})
				.listener(new SimpleItemReaderListener())
				.build();
	}

}
