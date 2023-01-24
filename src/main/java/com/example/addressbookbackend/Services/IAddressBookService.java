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

    List<AddressBookDTO> getPersonRecordByCity(String city);

    List<AddressBookDTO>  getPersonRecordByState(String state);



    String CreateRecordAndToken(AddressBookDTO addressBookDTO);

    AddressBookDTO getRecordByToken(String token);

    List<AddressBookDTO> getAllRecordsByToken(String token);

    AddressBookDTO updateRecordsByToken(String token, AddressBookDTO addressBookDTO);

    void deleteRecordsByToken(String token);
}
