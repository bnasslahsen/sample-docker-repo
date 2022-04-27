package fr.training.samples.spring.shop;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Badr NASS
 *
 */
@SpringBootTest
public abstract class SpringBootAppTest {

	@SpringBootApplication
	static class SpringTestApp {}

}
