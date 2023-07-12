package udemy.springboot.jwt.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import udemy.springboot.jwt.app.models.services.UploadFileService;

@SpringBootApplication
public class SpringbootJwtApplication implements CommandLineRunner {

	private final UploadFileService uploadFileService;

	private final BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public SpringbootJwtApplication(UploadFileService uploadFileService, BCryptPasswordEncoder passwordEncoder) {
		this.uploadFileService = uploadFileService;
		this.passwordEncoder = passwordEncoder;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJwtApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		uploadFileService.deleteAll();
		uploadFileService.init();
		String password = "12345";
		for (int i = 0; i < 2; i++) {
			String encodedPassword = passwordEncoder.encode(password);
			System.out.println(encodedPassword);
		}
	}
}
