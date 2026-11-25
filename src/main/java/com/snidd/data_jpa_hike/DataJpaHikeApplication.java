package com.snidd.data_jpa_hike;

import com.github.javafaker.Faker;
import com.snidd.data_jpa_hike.entity.Address;
import com.snidd.data_jpa_hike.entity.Hike;
import com.snidd.data_jpa_hike.entity.Hiker;
import com.snidd.data_jpa_hike.entity.HikingGroup;
import com.snidd.data_jpa_hike.repository.AddressRepository;
import com.snidd.data_jpa_hike.repository.HikerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class DataJpaHikeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataJpaHikeApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(
			HikerRepository hikerRepository, AddressRepository addressRepository) {
		return args -> {

			// Call generation methods here:


		};
	}



	/**
	 * generateHikerHikesAndJoinedGroups();
	 * _
	 * generate hiker, hikes and joined groups.
	 */
	private static void generateHikerHikesAndJoinedGroups(HikerRepository hikerRepository) {
		Faker faker = new Faker();

		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();

		Hiker hiker = new Hiker(
				firstName,
				lastName,
				firstName.toLowerCase() + "_" + lastName.toLowerCase() + "@mail.com",
				LocalDate.of(
						faker.number().numberBetween(2020, 2026),
						faker.number().numberBetween(1, 12),
						faker.number().numberBetween(1, 28) ),
				faker.options().option(50.0, 55.0, 60.0, 65.0, 70.0, 75.0, 80.0, 85.0, 90.0, 95.0, 100.0, 110.0, 120.0, 130.0, 140.0, 150.0, 160.0, 170.0, 180.0, 190.0, 200.0)
		);


		hiker.setHike(new Hike("Prestkona", LocalDate.of(2026, 7, 13), 12));
		hiker.setHike(new Hike("Sætertind", LocalDate.of(2026, 8, 6), 11));
		hiker.setHike(new Hike("Liland", LocalDate.of(2026, 8, 7), 15));

		hiker.joinGroup(new HikingGroup("Fjelltur enkel"));
		hiker.joinGroup(new HikingGroup("Fiske fjellvann"));
			hikerRepository.save(hiker);
	}



	/**
	 * generateHikerWithHikes(hikerRepository)
	 * _
	 * Generate Hiker with hikes
	 * @param hikerRepository hikerRepository.
	 */
	private static void generateHikerWithHikes(HikerRepository hikerRepository) {
		Faker faker = new Faker();

		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();

		Hiker hiker = new Hiker(
				firstName,
				lastName,
				firstName.toLowerCase() + "_" + lastName.toLowerCase() + "@mail.com",
				LocalDate.of(
						faker.number().numberBetween(2020, 2026),
						faker.number().numberBetween(1, 12),
						faker.number().numberBetween(1, 28) ),
				faker.options().option(50.0, 55.0, 60.0, 65.0, 70.0, 75.0, 80.0, 85.0, 90.0, 95.0, 100.0, 110.0, 120.0, 130.0, 140.0, 150.0, 160.0, 170.0, 180.0, 190.0, 200.0)
		);


		hiker.setHike(new Hike("Vesthornet", LocalDate.of(2026, 7, 10), 7));
		hiker.setHike(new Hike("Liland", LocalDate.of(2026, 7, 2), 13));
		hiker.setHike(new Hike("Lakså", LocalDate.of(2026, 7, 2), 5));

		hikerRepository.save(hiker);
	}



	/**
	 * generateHikers(hikerRepository);
	 * _
	 * Generate hikers with Faker library.
	 * @param hikerRepository hikerRepository.
	 */
	private static void generateHikers(HikerRepository hikerRepository) {

		Faker faker = new Faker();

		for (int i = 0; i < 100; i++) {
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();

			Hiker hiker = new Hiker(
					firstName,
					lastName,
					firstName.toLowerCase() + "_" + lastName.toLowerCase() + "@mail.com",
					LocalDate.of(
							faker.number().numberBetween(2020, 2026),
							faker.number().numberBetween(1, 12),
							faker.number().numberBetween(1, 28) ),
					faker.options().option(50.0, 55.0, 60.0, 65.0, 70.0, 75.0, 80.0, 85.0, 90.0, 95.0, 100.0, 110.0, 120.0, 130.0, 140.0, 150.0, 160.0, 170.0, 180.0, 190.0, 200.0)
			);
			hikerRepository.save(hiker);
		}
	}


	/**
	 * generateHikersWithAddress(addressRepository);
	 * _
	 * Generate hikers with address using Faker library.
	 * @param addressRepository addressRepository.
	 */
	private static void generateHikersWithAddress(AddressRepository addressRepository) {

		Faker faker = new Faker();

		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();

		Hiker hiker = new Hiker(
				firstName,
				lastName,
				firstName.toLowerCase() + "_" + lastName.toLowerCase() + "@mail.com",
				LocalDate.of(
						faker.number().numberBetween(2020, 2026),
						faker.number().numberBetween(1, 12),
						faker.number().numberBetween(1, 28) ),
				faker.options().option(50.0, 55.0, 60.0, 65.0, 70.0, 75.0, 80.0, 85.0, 90.0, 95.0, 100.0, 110.0, 120.0, 130.0, 140.0, 150.0, 160.0, 170.0, 180.0, 190.0, 200.0)
		);

		Address address = new Address(
				faker.address().streetAddress(),
				faker.address().city(),
				faker.address().zipCode(),
				hiker
		);
		addressRepository.save(address);

		addressRepository.findById(3L).ifPresent(System.out::println);

	}


}
