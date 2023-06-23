package cat.itacademy.barcelonactiva.Debon.Miquel.s05.t01.n02.S05T01N02DebonMiquel;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "API Rest Swagger"),
		tags = @Tag(name = "IT-Academy", description = "Main methods Documentation")
)

public class  S05T01N02DebonMiquelApplication {


	public static void main(String[] args) {
		SpringApplication.run(S05T01N02DebonMiquelApplication.class, args);
	}

}
