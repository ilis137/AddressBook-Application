package com.example.addressbookbackend.Services;

import com.example.addressbookbackend.DTO.AddressBookDTO;
import com.example.addressbookbackend.Exceptions.PersonRecordNotFoundException;

import java.util.List;

public interface IAddressBookService {
    AddressBookDTO savePersonRecord(AddressBookDTO addressBookDTO);
    List<AddressBookDTO> getAllPersonRecords();
    AddressBookDTO getPersonRecordById(int id);
    AddressBookDTO updatePersonRecordById(int id, AddressBookDTO addressBookDTO);
    void deletePersonRecordById(int id);
    List<AddressBookDTO> getPersonRecordByName(String name) throws PersonRecordNotFoundException;

}
