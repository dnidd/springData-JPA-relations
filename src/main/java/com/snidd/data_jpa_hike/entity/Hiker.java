package com.snidd.data_jpa_hike.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Hiker entity/table.
 * Email is unique for every hiker.
 */
@Entity(name = "Hiker")
@Table(
        name = "hiker",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "hiker_email_unique",
                        columnNames = "email"
                )
        }
)
public class Hiker {


    // PK
    @Id
    // Set the name of id sequence.
    @SequenceGenerator(
            name = "hiker_sequence",
            // The name from where to fetch PK values.
            sequenceName = "hiker_sequence",
            // Increment id by 1 (allocated in memory).
            allocationSize = 1
    )
    @GeneratedValue(
            // .SEQUENCE: generates incremented PK value id.
            strategy = GenerationType.SEQUENCE,
            // link GeneratedValue to custom SequenceGenerator
            generator = "hiker_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;


    @Column(
            name = "first_name",
            nullable = false,
            length = 100
    )
    private String firstName;


    @Column(
            name = "last_name",
            nullable = false,
            length = 100
    )
    private String lastName;


    @Column(
            name = "email",
            nullable = false
    )
    private String email;


    @Column(
            name = "join_date",
            nullable = false
    )
    private LocalDate joinDate;


    @Column(
            name = "monthly_goal_km",
            nullable = false
    )
    private double monthlyGoalKm;


    // When orphanRemoval = true,
    // the associated Address (child) will be removed when Hiker (parent) is removed.
    @OneToOne(
            mappedBy = "hiker",
            orphanRemoval = true
    )
    private Address address;


    // orphanRemoval = true:
    // when deleting a Hiker its associated hikes will be deleted.
    @OneToMany(
            mappedBy = "hiker",
            orphanRemoval = true,
            cascade = {
                    CascadeType.PERSIST, // Save hikes when Hiker is saved.
                    CascadeType.REMOVE // Remove hikes when Hiker is deleted.
            }
    )
    private Set<Hike> hikes = new HashSet<>();


    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST, // Save hikingGroups when Hiker is saved.
                    CascadeType.REMOVE // Remove hikingGroups when Hiker is deleted.
            }
    )
    @JoinTable(
            name = "hiker_hiking_groups",
            joinColumns = @JoinColumn(
                    name = "hiker_id",
                    foreignKey = @ForeignKey(
                            name = "hiker_hiking-group_hiker_id_fk"
                    )
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "hiking_group_id",
                    foreignKey = @ForeignKey(
                            name = "hiker_hiking-group_hiker-group_id_fk"
                    )
            )

    )
    private Set<HikingGroup> hikingGroups = new HashSet<>();


    /**
     * No args constructor.
     */
    public Hiker() {}


    /**
     * All args constructor.
     * @param firstName firstName.
     * @param lastName lastName.
     * @param email email.
     * @param joinDate joinDate.
     */
    public Hiker(String firstName, String lastName, String email, LocalDate joinDate, double monthlyGoalKm) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.joinDate = joinDate;
        this.monthlyGoalKm = monthlyGoalKm;
    }


    // *****************
    // Getters & setters


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public double getMonthlyGoalKm() {return monthlyGoalKm;}

    public void setMonthlyGoalKm(double monthlyGoalKm) {this.monthlyGoalKm = monthlyGoalKm;}


    public Set<HikingGroup> getHikingGroups() {
        return hikingGroups;
    }

    public void setHikingGroups(Set<HikingGroup> hikingGroups) {
        this.hikingGroups = hikingGroups;
    }

    public Set<Hike> getHikes() {
        return hikes;
    }

    public void setHikes(Set<Hike> hikes) {
        this.hikes = hikes;
    }


    /**
     * Update both sides of the relationship!
     * _
     * Add a Hike to a Hiker.
     * Set Hiker reference in Hike - owner of Hike.
     * @param hike hike.
     */
    public void setHike(Hike hike) {
        this.hikes.add(hike);
        hike.setHiker(this);
    }


    /**
     * Update both sides of the relationship!
     * _
     * Delete Hike from hikes.
     * Set hiker to null in Hike.
     *  When a Hike is deleted, the owner Hiker of the hike is removed.
     * @param hike hike.
     */
    public void deleteHike(Hike hike) {
        this.hikes.remove(hike);
        hike.setHiker(null);
    }


    /**
     * Update both sides of the relationship!
     * _
     * Add a hikingGroup to a Hiker.
     * Set Hiker reference in hikingGroup.
     * @param hikingGroup hikingGroup.
     */
    public void joinGroup(HikingGroup hikingGroup) {
        this.hikingGroups.add(hikingGroup);
        hikingGroup.getHikers().add(this);
    }


    /**
     * Update both sides of the relationship!
     * _
     * Delete hikingGroup from hiker.
     * Remove hiker from hikingGroup.
     * @param hikingGroup hikingGroup.
     */
    public void deleteHikingGroup(HikingGroup hikingGroup) {
        this.hikingGroups.remove(hikingGroup);
        hikingGroup.getHikers().remove(this);
    }


    @Override
    public String toString() {
        return "Hiker{" +
                "monthlyGoalKm=" + monthlyGoalKm +
                ", joinDate=" + joinDate +
                ", email='" + email + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", id=" + id +
                '}';
    }
}
