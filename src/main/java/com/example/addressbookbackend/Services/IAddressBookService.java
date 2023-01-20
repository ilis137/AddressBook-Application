package com.example.addressbookbackend.Services;

import com.example.addressbookbackend.DTO.AddressBookDTO;
import com.example.addressbookbackend.Exceptions.PersonRecordNotFoundException;
import com.example.addressbookbackend.model.AddressBook;

import java.util.List;

public interface IAddressBookService {
    AddressBook savePersonRecord(AddressBookDTO addressBookDTO);
    List<AddressBook> getAllPersonRecords();
    AddressBook getPersonRecordById(int id);
    AddressBook updatePersonRecordById(int id, AddressBookDTO addressBookDTO);
    void deletePersonRecordById(int id);
    List<AddressBook> getPersonRecordByName(String name) throws PersonRecordNotFoundException;

}
