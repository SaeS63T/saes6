package sae.semestre.six;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	static {
		System.setProperty("java.vm.args", "--add-opens java.base/java.lang=ALL-UNNAMED");
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
