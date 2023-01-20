package com.example.addressbookbackend.Repository;

import com.example.addressbookbackend.model.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressBookRepository extends JpaRepository<AddressBook,Long> {
}
