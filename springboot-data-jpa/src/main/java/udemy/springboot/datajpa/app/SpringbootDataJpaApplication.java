package udemy.springboot.datajpa.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import udemy.springboot.datajpa.app.models.services.UploadFileService;

@SpringBootApplication
public class SpringbootDataJpaApplication implements CommandLineRunner {

	private final UploadFileService uploadFileService;

	@Autowired
	public SpringbootDataJpaApplication(UploadFileService uploadFileService) {
		this.uploadFileService = uploadFileService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDataJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		uploadFileService.deleteAll();
		uploadFileService.init();
	}
}
