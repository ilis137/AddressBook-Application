package com.example.addressbookbackend.DTO;

import jakarta.annotation.Nullable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressBookDTO {
    @Nullable
    private int id;

    @NonNull
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "employee name invalid")
    private String name;

    @NotBlank(message = "type should not be empty")
    private String type;

    @NotBlank(message = "address should not be empty")
    private String address;

    @NotBlank(message = "city should not be empty")
    private String city;

    @NotBlank(message = "state should not be empty")
    private String state;


    @Pattern(regexp="^[1-9]{1}[0-9]{5}$" ,message = "zip should be 6 digits")
    private String zip;

    @Pattern(regexp="^[6-9][0-9]{9}$",message = "phone number should be 10 digits")
    private String phoneNumber;

    @NotBlank(message = "email should not be empty")
    @Pattern(regexp= "^[a-zA-Z0-9]{3,}([\\\\.\\\\+\\\\-]?[a-zA-Z0-9]{3,})?[@][A-Za-z0-9]{1,}[.][A-Za-z]{2,4}[,]?([.][A-Za-z]{2,4}[.]?)?$",message="email should not be empty")
    private String email;

}
