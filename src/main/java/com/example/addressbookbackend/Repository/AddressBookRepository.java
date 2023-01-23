package com.example.addressbookbackend.Repository;

import com.example.addressbookbackend.model.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressBookRepository extends JpaRepository<AddressBook,Integer> {

    List<AddressBook> findByName(String name);
    List<AddressBook> findByCity(String city);
    List<AddressBook> findByState(String state);
}
