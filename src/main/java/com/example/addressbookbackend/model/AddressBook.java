package com.example.addressbookbackend.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="addressbook")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class AddressBook {
    @Nullable
    private int recordId;

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
    private String zip;
    @NonNull
    private String phoneNumber;

    @NonNull
    private String email;
}
