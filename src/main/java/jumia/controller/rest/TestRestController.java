package jumia.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

	@GetMapping("/")
	public String testApiInterface() {
		return "'message':'It works!!'";
	}
}
