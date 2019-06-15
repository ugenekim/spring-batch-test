package test.ujkim.spring.batch.listener;

import org.springframework.batch.core.ItemReadListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleItemReaderListener implements ItemReadListener<String> {

	@Override
	public void beforeRead() {
		log.info("SimpleItemReaderListener - beforeRead()");
	}

	@Override
	public void afterRead(String item) {
		log.info("SimpleItemReaderListener - afterRead()");
	}

	@Override
	public void onReadError(Exception ex) {
		log.info("SimpleItemReaderListener - onReadError()");
	}

}
