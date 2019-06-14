package test.ujkim.spring.batch.task;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleTask1 implements Tasklet {

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		log.info(">>>>>>>> sampleTask1 start..");
		
		// 실행 코드
		
		log.info(">>>>>>>> sampleTask1 done..");
		return RepeatStatus.FINISHED;
	}

}
