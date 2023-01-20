package com.example.addressbookbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Entity
@Table(name="addressbook")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class AddressBook {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @NonNull
    private String name;

    @NonNull
    private String type;

    @NonNull
    private String address;

    @NonNull
    private String city;

    @NonNull
    private String state;

    @NonNull
    private long zip;
    @NonNull
    private String phoneNumber;

    @NonNull
    private String email;
}
