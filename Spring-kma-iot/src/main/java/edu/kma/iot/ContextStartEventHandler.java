package edu.kma.iot;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

public class ContextStartEventHandler implements ApplicationListener<ContextStartedEvent> {
	private static final Logger LOGGER = Logger.getLogger(ContextStartEventHandler.class);
	@Override
	public void onApplicationEvent(ContextStartedEvent csv) {
		LOGGER.info("WELCOME TO KMA IOT!");
	}

}
