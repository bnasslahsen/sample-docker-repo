package fr.training.samples.spring.shop.domain.account.event;

import java.util.List;

/**
 * Interface should be implemented by all aggregate roots that handle Domain
 * events.
 */
public interface EventAware {

	List<Event> getEvents();

	void clearEvents();

}
