package jumia.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jumia.core.constants.MessageResults;
import jumia.core.utilities.results.DataResult;
import jumia.core.utilities.results.ErrorDataResult;
import jumia.core.utilities.results.SuccessDataResult;
import jumia.domain.Contact;
import jumia.domain.CountryInformation;
import jumia.model.PhoneDetailDTO;
import jumia.repository.ContactRepository;
import jumia.repository.CountryInfoRepository;
import jumia.service.PhoneService;

@Service
public class PhoneServiceImpl implements PhoneService {

	private ContactRepository contactRepository;
	private CountryInfoRepository countryInfoRepository;

	private List<Contact> allContacts = null;
	private List<PhoneDetailDTO> phoneDetails = null;
	private String FIELD = "contact";

	@Autowired
	public PhoneServiceImpl(ContactRepository cRepository, CountryInfoRepository countryRepository) {
		this.contactRepository = cRepository;
		this.countryInfoRepository = countryRepository;
	}

	@Override
	public DataResult<List<Contact>> getAllContacts() {
		FIELD = "contact";
		return new SuccessDataResult<List<Contact>>(contactRepository.findAll(), MessageResults.allDataListed(FIELD));
	}

	@Override
	public DataResult<List<CountryInformation>> getCountryInformation() {
		FIELD = "countryInfo";
		return new SuccessDataResult<List<CountryInformation>>(countryInfoRepository.findAll(),
				MessageResults.allDataListed(FIELD));
	}

	@Override
	public DataResult<List<PhoneDetailDTO>> getPhoneDetails() {
		FIELD = "phoneDetailDTO";
		phoneDetails = new ArrayList<>();
		allContacts = new ArrayList<>();
		try {
			allContacts = contactRepository.findAll();
			Iterator<Contact> it = allContacts.iterator();
			PhoneDetailDTO phoneDetailDTO = new PhoneDetailDTO();
			while (it.hasNext()) {
				Contact cont = it.next();
				phoneDetailDTO = convertContactToPhoneDetail(cont);
				phoneDetails.add(phoneDetailDTO);
			}
		} catch (Exception ex) {
			return new ErrorDataResult<>(MessageResults.error + " " + ex.getMessage());
		}
		return new SuccessDataResult<>(phoneDetails, MessageResults.allDataListed(FIELD));
	}

	@Override
	public DataResult<Page<PhoneDetailDTO>> getPhoneDetailsPageable(Pageable pageable) {
		FIELD = "phoneDetailDTOPaged";
		Page<Contact> contacts = null;
		Page<PhoneDetailDTO> phoneDetailDTOs = null;
		phoneDetails = new ArrayList<>();
		try {
			contacts = contactRepository.findAll(pageable);
			if (contacts.isEmpty()) {
				return new ErrorDataResult<Page<PhoneDetailDTO>>(MessageResults.notFound(FIELD));
			}
			contacts.getContent().forEach(t -> {
				phoneDetails.add(convertContactToPhoneDetail(t));
			});
			phoneDetailDTOs = new PageImpl<PhoneDetailDTO>(phoneDetails, pageable, contacts.getSize());
		} catch (Exception ex) {
			return new ErrorDataResult<>(MessageResults.error + " " + ex.getMessage());
		}
		return new SuccessDataResult<>(phoneDetailDTOs, MessageResults.allDataListed(FIELD));
	}

	private PhoneDetailDTO convertContactToPhoneDetail(Contact contact) {
		PhoneDetailDTO phone = new PhoneDetailDTO();
		phone.setCustomer(contact);
		String code = "+" + contact.getCustomerPhone().substring(1, 4);
		CountryInformation country = countryInfoRepository.findByCountryCode(code).get();
		boolean isValid = false;
		if (!country.equals(null)) {
			Pattern regex = Pattern.compile(country.getPhonePattern());
	        Matcher matcher = regex.matcher(contact.getCustomerPhone());
			isValid = matcher.matches();
			isValid = regex.matcher(contact.getCustomerPhone()).matches();
			System.out.format("Regex: %s, Phone: %s, Valid %s. \n", country.getPhonePattern(), contact.getCustomerPhone(),
					String.valueOf(isValid));
		}
		phone.setValid(isValid);
		phone.setCountry(country);
		return phone;
	}
}
