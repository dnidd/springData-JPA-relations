
### Architecture/structure
Application based on entities with relations OneToONe, OneToMany, ManyToOne and ManyToMany.

### Database

Create the database and Docker configuration, docker-compose.yml file is created.
Database connection and JPA configurations for Spring Boot application in application.properties.

In terminal:
- Create Docker container:<br>
```docker compose up -d```
- check container:<br>
```docker ps```
- Remove volume and data
- ````docker compose down -v````<br>
Data is saved in volume ```pgdata```

Connect to for example DBeaver (or Database extension/DBMS in IntelliJ IDEA).


### Execution & database seed
In
````
@SpringBootApplication
public class DataJpaHikeApplication
````

````
@Bean
public CommandLineRunner commandLineRunner()
````


#### Seeding data to database
To seed/populate the PostgreSQL database, a CommandLineRunner is used in main.
The Faker library is used for generating most of the mock data: ````https://github.com/dius/java-faker````<br>
The CommandLineRunner is used for insertions, updates and other queries/operations to DB in Java code.
This makes it convenient to use as a complement to Postman or Bruno.

In Address a constructor also takes Hiker to associate Address to Hiker in creation of Address:
````
    public Address(String street, String city, String zipCode, Hiker hiker) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.hiker = hiker;
    }
````
In CommandLineRunner, create/seed Address associated to Hiker:
````
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
					faker.options().option(50.0, 55.0, 60.0, 65.0, 70.0, 75.0,
					80.0, 85.0, 90.0, 95.0, 100.0, 110.0, 120.0, 130.0, 140.0,
					150.0, 160.0, 170.0, 180.0, 190.0, 200.0)
			);

			Address address = new Address(
					faker.address().streetAddress(),
					faker.address().city(),
					faker.address().zipCode(),
					hiker
			);
			addressRepository.save(address);
````
Run in DBMS (DBeaver/DB extension in IntelliJ IDEA or the likes),
hiker id & hiker_id in address:
````
select * from hiker h
join address a on h.id = a.hiker_id
````
````
|id |email                |first_name|join_date |last_name|monthly_goal_km|id |city          |street        |zip_code|hiker_id|
|---|---------------------|----------|----------|---------|---------------|---|--------------|--------------|--------|--------|
|101|jaime_maggio@mail.com|Jaime     |2022-02-03|Maggio   |65             |1  |New Marlinside|927 Joe Greens|24701   |101     |

````

Create a Hiker with associated Hike:
Hiker 1 -- n Hike.
When saving the Hiker, the associated hikes are saved as well,
thanks to the cascade settings (PERSIST) in Hiker.
````
hiker.setHike(new Hike("Vesthornet", LocalDate.of(2026, 7, 10), 7));
hiker.setHike(new Hike("Liland", LocalDate.of(2026, 7, 2), 13));
hiker.setHike(new Hike("Lakså", LocalDate.of(2026, 7, 2), 5));

hikerRepository.save(hiker);
````



## Relations
### Hiker 1 -- 1 Address
@OneToOne<br>
Each hiker has an Address. Each Address belongs to a single Hiker.
A bidirectional relationship.
AddressMapperImpl has a HikerMapper.
Address has Hiker.
When mapping from Address to AddressDto:
````
public AddressDto fromAddressToAddressDto(Address address) {
````
a Hiker needs to be mapped to a HikerDto to be held by AddressDto:<br>
`````
addressDto.setHikerDto(hikerMapper.fromHikerToHikerDto(address.getHiker()));
`````


Nested Dto's for relational mapping, HikerAddressDto, AddressHikerDto.<br>
get hiker by id including address, Postman:
````
http://localhost:8080/api/hiker/103/address
````
get address by id including hiker, Postman:
````
http://localhost:8080/api/address/1
````

When deleting a Hiker who is associated with an address, the address will be deleted as well.<br>
This is thanks to orphanRemoval in Hiker entity.
````
    // When orphanRemoval = true,
    // the associated Address (child) will be removed when Hiker (parent) is removed.
    @OneToOne(
            mappedBy = "hiker",
            orphanRemoval = true
    )
    private Address address;
````
If orphanRemoval = true in Address, deleting Address will also delete Hiker.

### Hiker 1 -- n Hike <--> Hike n -- 1 Hiker
@OneToMany, @ManyToOne <br>
A Hiker can have many hikes and many hikes belongs to a hiker.
HikerHikeDto is a nested Dto. HikerMapperImpl includes a helper method for mapping from Hiker to HikerDto.
HikerHikeDto uses StringBuilder to bind a hikers different hikes:
````
HikerHikeDto {
  id=105
  firstName='Betsy'
  lastName='Johnson'
  email='betsy_johnson@mail.com'
  joinDate=2023-10-27
  monthlyGoalKm=130.0
  Hikes:
    HikeDto{id=2, name='Liland', date=2026-07-02, distanceKm=13.0}
    HikeDto{id=3, name='Lakså', date=2026-07-02, distanceKm=5.0}
    HikeDto{id=1, name='Vesthornet', date=2026-07-10, distanceKm=7.0}
````

HikeHikerDto has nested HikerDto. This makes it possible to search for hikes and include Hiker data.
A Hiker also has Set<Hike>.

### Hiker n -- n HikingGroup <--> HikingGroup n -- n Hiker
@ManyToMany, @ManyToMany <br>
One hiker can be a part of multiple hiking groups. At the same time, multiple hiking groups
can involve the same hiker.

A join/bridge table holds FK to hiker id and FK to hiking group id.
The join/bridge table enables the definition of many-to-many relationship between hikers and hiking groups.

Example:<br>

<b>Find hiking group by id and its hikers.
````
Find hiking group by id and its hikers.
http://localhost:8080/api/hikingGroup/1/hikingGroupHikers
````
````
{
    "hikerDtos": [
        {
            "email": "ruben_frami@mail.com",
            "firstName": "Ruben",
            "id": 109,
            "joinDate": "2021-04-24",
            "lastName": "Frami",
            "monthlyGoalKm": 140.0
        }
    ],
    "id": 1,
    "name": "Fjelltur enkel"
}
````
<b>Find hiker by id and its hiking groups.
````
http://localhost:8080/api/hikingGroup/109/hikerHikingGroups
````
````
{
    "email": "ruben_frami@mail.com",
    "firstName": "Ruben",
    "hikingGroupDtos": [
        {
            "Id": 2,
            "name": "Fiske fjellvann"
        },
        {
            "Id": 1,
            "name": "Fjelltur enkel"
        }
    ],
    "id": 109,
    "joinDate": "2021-04-24",
    "lastName": "Frami",
    "monthlyGoalKm": 140.0
}
````

<span style="color:white">––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––</span>

🔴🟠🟡🟢🔵🟣🟤⚫⚪