package testnosql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import testnosql.domain.Journal;
import testnosql.repository.JournalRepository;

@SpringBootApplication
public class DemoApplication {
	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner start(JournalRepository repo) {
		return args->{
			log.info("> Deleting existing data");
			repo.deleteAll();
			log.info("> Inserting new data...");
			repo.save(new Journal("Get to know Spring Boot","Today I will learn Spring Boot","01/02/2016"));
			repo.save(new Journal("Simple Spring Boot Project","I will do my first Spring Boot Project","01/03/2016"));
			repo.save(new Journal("Spring Boot Reading","Read more about Spring Boot","02/02/2016"));
			repo.save(new Journal("Spring Boot in the Cloud","Spring Boot using Cloud Foundry","03/01/2016"));
			log.info("> Getting all data...");
			repo.findAll().forEach(entry->log.info(entry.toString()));
			log.info("> Getting data using like...");
			repo.findByTitleLike("Cloud").forEach(entry->log.info(entry.toString()));
		};
	}
}
