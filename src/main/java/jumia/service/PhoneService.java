package jumia.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jumia.core.utilities.results.DataResult;
import jumia.domain.Contact;
import jumia.domain.CountryInformation;
import jumia.model.PhoneDetailDTO;

public interface PhoneService {
	public DataResult<List<Contact>> getAllContacts();

	public DataResult<List<CountryInformation>> getCountryInformation();

	public DataResult<List<PhoneDetailDTO>> getPhoneDetails();

	public DataResult<Page<PhoneDetailDTO>> getPhoneDetailsPageable(Pageable pageable);
}
