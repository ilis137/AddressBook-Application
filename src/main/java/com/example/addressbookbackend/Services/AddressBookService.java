package com.example.addressbookbackend.Services;

import com.example.addressbookbackend.DTO.AddressBookDTO;
import com.example.addressbookbackend.Exceptions.PersonRecordNotFoundException;
import com.example.addressbookbackend.model.AddressBook;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressBookService implements IAddressBookService{
    @Override
    public AddressBook savePersonRecord(AddressBookDTO addressBookDTO) {
        return null;
    }

    @Override
    public List<AddressBook> getAllPersonRecords() {
        return null;
    }

    @Override
    public AddressBook getPersonRecordById(int id) {
        return null;
    }

    @Override
    public AddressBook updatePersonRecordById(int id, AddressBookDTO addressBookDTO) {
        return null;
    }

    @Override
    public void deletePersonRecordById(int id) {

    }

    @Override
    public List<AddressBook> getPersonRecordByName(String name) throws PersonRecordNotFoundException {
        return null;
    }


}
