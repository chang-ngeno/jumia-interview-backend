package jumia.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "countries")
public class CountryInformation {

	@Id
	@NaturalId
	@Column(unique = true, nullable = false)
	@NonNull
	private String countryName;

	@Column(unique = true, nullable = false)
	@NonNull
	private String countryCode;

	@Column(nullable = false)
	@NonNull
	private String phonePattern;

}
