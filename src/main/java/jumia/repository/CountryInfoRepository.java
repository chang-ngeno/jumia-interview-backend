package jumia.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jumia.domain.CountryInformation;

public interface CountryInfoRepository extends JpaRepository<CountryInformation, String> {
	public Optional<CountryInformation> findByCountryCode(String code);
}
