package test.ujkim.spring.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Configuration // Spring Batch의 모든 Job은 @Configuration으로 등록하여 사용
public class SimpleJobConfiguration {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job simpleJob() {
		// simpleJob이라는 이름으로 Batch Job을 생성
		// Job 이름은 별도의 이름을 지정하지 않고, Builder를 통해 지정
		return jobBuilderFactory.get("simpleJob").start(simpleStep1()).build();
	}

	@Bean
	public Step simpleStep1() {
		// simpleStep1이라는 이름으로 Batch Step 을 생성
		// Step 이름은 Builder를 통해 지정
		// Step안에서 수행될 기능을 명시
		// tasklet은 Step안에서 단일로 수행될 커스텀한 기능들을 선언할 때 사용
		return stepBuilderFactory.get("simpleStep1").tasklet((contribution, chunkContext) -> {
			log.info(">>>>>>>> This is Step1");
			return RepeatStatus.FINISHED;
		}).build();
	}

}
