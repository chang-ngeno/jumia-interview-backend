package jumia.controller.rest.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jumia.core.utilities.results.DataResult;
import jumia.domain.Contact;
import jumia.domain.CountryInformation;
import jumia.model.PhoneDetailDTO;
import jumia.service.PhoneService;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = {"http://localhost","http://localhost:8480", "http://127.0.0.1","http://localhost:4200","http://127.0.0.1:8480"})
public class PhoneRestController {
	private PhoneService phoneService;

	@Autowired
	public PhoneRestController(PhoneService cService) {
		this.phoneService = cService;
	}

	@GetMapping(path = "/", produces = "application/json")
	public DataResult<List<Contact>> fetchAllContacts() {
		return phoneService.getAllContacts();
	}

	@GetMapping(path = "/countries", produces = "application/json")
	public DataResult<List<CountryInformation>> fetchAllCountries() {
		return phoneService.getCountryInformation();
	}

	@GetMapping(path = "/phones", produces = "application/json")
	public DataResult<List<PhoneDetailDTO>> fetchAllPhones() {
		return phoneService.getPhoneDetails();
	}

	@GetMapping(path = "/phones/pageable", produces = "application/json")
	public DataResult<Page<PhoneDetailDTO>> fetchAllPhonesPageable(
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "10") Integer size, 
			@RequestParam(defaultValue = "cId") String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
		return phoneService.getPhoneDetailsPageable(pageable);
	}
}
