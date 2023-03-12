package com.example.addressbookbackend.Repository;

import com.example.addressbookbackend.model.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AddressBookRepository extends MongoRepository<AddressBook,Integer> {

    List<AddressBook> findByName(String name);
    List<AddressBook> findByCity(String city);
    List<AddressBook> findByState(String state);

    void deleteByRecordId(int id);

    Optional<AddressBook> findByRecordId(int id);
}
