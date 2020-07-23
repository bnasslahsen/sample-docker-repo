package fr.training.samples.spring.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author bnasslahsen
 *
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "fr.training.samples.spring.shop" }, lazyInit = true)
public class SpringBootAppBase {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringBootAppBase.class, args);
	}
}
