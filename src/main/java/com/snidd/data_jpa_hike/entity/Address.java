package com.snidd.data_jpa_hike.entity;

import jakarta.persistence.*;

@Entity(name = "Address")
@Table(name = "address")
public class Address {

    // PK
    @Id
    // Set the name of id sequence.
    @SequenceGenerator(
            name = "address_sequence",
            // The name from where to fetch PK values.
            sequenceName = "address_sequence",
            // Increment id by 1 (allocated in memory).
            allocationSize = 1
    )
    @GeneratedValue(
            // .SEQUENCE: generates incremented PK value id.
            strategy = GenerationType.SEQUENCE,
            // link GeneratedValue to custom SequenceGenerator
            generator = "address_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;


    @Column(
            name = "street",
            nullable = false
    )
    private String street;


    @Column(
            name = "city",
            nullable = false
    )
    private String city;


    @Column(
            name = "zip_code",
            nullable = false
    )
    private String zipCode;


    // cascade propagates operations from a parent to its associated child entities.
    // .ALL includes all cascade operations.
    // CascadeType.ALL saves a Hiker (child) automatically when saving an Address (parent).
    @OneToOne(
            cascade = CascadeType.ALL,
            // EAGER: when fetching the parent (Address), also the child (Hiker) is fetched.
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "hiker_id", // column name, holds id's from hikers.
            referencedColumnName = "id", // the id of hikers.
            foreignKey = @ForeignKey(
                    name = "hiker_id_fk"
            )
    )
    private Hiker hiker;


    /**
     * No args constructor
     */
    public Address() {}


    /**
     * Constructor to create Address associated to Hiker.
     * @param street street.
     * @param city city.
     * @param zipCode zip code.
     * @param hiker hiker.
     */
    public Address(String street, String city, String zipCode, Hiker hiker) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.hiker = hiker;
    }

    /**
     * All args constructor
     * @param street street.
     * @param city city.
     * @param zipCode zip code.
     */
    public Address(String street, String city, String zipCode) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    public Hiker getHiker() {
        return hiker;
    }

    public void setHiker(Hiker hiker) {
        this.hiker = hiker;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }



}
