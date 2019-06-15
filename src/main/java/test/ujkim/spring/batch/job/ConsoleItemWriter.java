package test.ujkim.spring.batch.job;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.batch.item.ItemWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsoleItemWriter<T> implements ItemWriter<T> {

	@Override
	public void write(List<? extends T> items) throws Exception {
		log.info(">>>>>>>>> writer start..");
		for (T item : items) {
			System.out.println(ToStringBuilder.reflectionToString(item));
		}
		log.info(">>>>>>>>> writer done..");
	}

}
