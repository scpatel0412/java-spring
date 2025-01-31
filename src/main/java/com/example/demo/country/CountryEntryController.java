package com.example.demo.country;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import jakarta.transaction.Transactional;



@RestController
@RequestMapping("/countries")
public class CountryEntryController {

    private final CountryRepository countryRepository;
     private final RestTemplate restTemplate = new RestTemplate();

    public CountryEntryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @GetMapping("/entry")
    @Transactional
    public List<Country> getMethodName() {
        String url = "https://restcountries.com/v3.1/all";

        List<Country> countries1 = countryRepository.findAll();
        if(countries1.size() > 0){
            return countries1;
        }

        List<CountryDTO> countryList = fetchCountryData(url);  // Assuming this method fetches the data from the API
        if (countryList == null || countryList.isEmpty()) {
            return Collections.emptyList();
        }

        System.err.println("Fetched country data: " + countryList);  // Log the data

        try {
            // Convert the DTO list to Entity list (Country objects)
            List<Country> countries = countryList.stream()
                                                .map(this::mapDTOToCountry)  // Convert each DTO to a Country entity
                                                .collect(Collectors.toList());

                                                System.out.print(countries);

            // Save all countries in a single batch
            countryRepository.saveAll(countries);

            return countries;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


    private List<CountryDTO> fetchCountryData(String url) {
        try {
            CountryDTO[] countryArray = restTemplate.getForObject(url, CountryDTO[].class);
            return List.of(countryArray);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Country mapDTOToCountry(CountryDTO countryDTO) {
        Country country = new Country();

        country.setName(countryDTO.getName().getCommon()); // Setting common name
        country.setOfficialName(countryDTO.getName().getOfficial()); // Setting official name
        country.setCca2Code(countryDTO.getCca2());
        country.setCcn3Code(countryDTO.getCcn3() !=null ? countryDTO.getCcn3():"");
        country.setCurrencies(countryDTO.getCurrencies() != null ? countryDTO.getCurrencies().toString() : "");
        country.setCapital(countryDTO.getCapital()!= null ? countryDTO.getCapital().toString() :"");
        country.setRegion(countryDTO.getRegion());
        country.setAreaCode(countryDTO.getArea());
        country.setLanguages(countryDTO.getLanguages()!=null ? countryDTO.getLanguages().toString():""); // Store JSON as string
        country.setLatlng(countryDTO.getLatlng().toString()); // Store JSON as string
        country.setPopulation(countryDTO.getPopulation());
        country.setFlag(countryDTO.getFlag());
        country.setGooglemaps(countryDTO.getMaps().getGoogleMaps());
        country.setTimezone(String.join(", ", countryDTO.getTimezones()));
        country.setContinent(String.join(", ", countryDTO.getContinents()));
        country.setFlag(countryDTO.getFlags().getSvg());
        country.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        country.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        return country;
    }
    
}
