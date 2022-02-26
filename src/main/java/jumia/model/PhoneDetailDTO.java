package jumia.model;

import jumia.domain.Contact;
import jumia.domain.CountryInformation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDetailDTO {
	private Contact customer;
	private CountryInformation country;
	private boolean valid;
}
