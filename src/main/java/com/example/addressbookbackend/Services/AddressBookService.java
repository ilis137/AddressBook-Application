package com.example.addressbookbackend.Services;

import com.example.addressbookbackend.DTO.AddressBookDTO;
import com.example.addressbookbackend.Exceptions.PersonRecordNotFoundException;
import com.example.addressbookbackend.Repository.AddressBookRepository;
import com.example.addressbookbackend.model.AddressBook;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AddressBookService implements IAddressBookService {
    private static final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    AddressBookRepository addressBookRepository;

    @Override
    public AddressBookDTO savePersonRecord(AddressBookDTO addressBookDTO) {
        AddressBook record = modelMapper.map(addressBookDTO, AddressBook.class);
        record = addressBookRepository.save(record);
        return modelMapper.map(record, AddressBookDTO.class);
    }

    @Override
    public List<AddressBookDTO> getAllPersonRecords() {
        List<AddressBook> addressBook = addressBookRepository.findAll();
        List<AddressBookDTO> addressBookDTO = addressBook.stream().map(record -> modelMapper.map(record, AddressBookDTO.class)).collect(Collectors.toList());
        return addressBookDTO;
    }

    @Override
    public AddressBookDTO getPersonRecordById(int id) {
        AddressBook addressBookData = addressBookRepository.findById(id).orElseThrow(() -> new PersonRecordNotFoundException("Person record not found for  id " + id));
        log.debug(String.valueOf(addressBookData));
        return modelMapper.map(addressBookData, AddressBookDTO.class);
    }

    @Override
    public AddressBookDTO updatePersonRecordById(int id, AddressBookDTO addressBookDTO) {
        AddressBook addressBookData = addressBookRepository.findById(id).orElseThrow(() -> new PersonRecordNotFoundException("Person record not found for  id " + id));
        addressBookData.setName(addressBookDTO.getName());
        addressBookData.setAddress(addressBookDTO.getAddress());
        addressBookData.setCity(addressBookDTO.getCity());
        addressBookData.setState(addressBookDTO.getState());
        addressBookData.setType(addressBookDTO.getType());
        addressBookData.setZip(addressBookDTO.getZip());
        addressBookData.setPhoneNumber(addressBookDTO.getPhoneNumber());
        addressBookData.setEmail(addressBookDTO.getEmail());
        addressBookData = addressBookRepository.save(addressBookData);
        return modelMapper.map(addressBookData, AddressBookDTO.class);
    }

    @Override
    public void deletePersonRecordById(int id) {
        try {
            addressBookRepository.deleteById(id);
        } catch (RuntimeException ex) {
            throw new PersonRecordNotFoundException("Person record not found for id " + id);
        }

    }

    @Override
    public List<AddressBookDTO> getPersonRecordByName(String name) throws PersonRecordNotFoundException {
        try {
            List<AddressBook> employeePayrollList = addressBookRepository.findByName(name);
            List<AddressBookDTO> employeePayrollDTOList = employeePayrollList.stream().map(record -> modelMapper.map(record, AddressBookDTO.class)).collect(Collectors.toList());
            return employeePayrollDTOList;
        } catch (RuntimeException e) {
            throw new PersonRecordNotFoundException("Person record not found for name " + name);
        }
    }

    @Override
    public List<AddressBookDTO> getPersonRecordByCity(String city) {
        try {
            List<AddressBook> employeePayrollList = addressBookRepository.findByCity(city);
            List<AddressBookDTO> employeePayrollDTOList = employeePayrollList.stream().map(record -> modelMapper.map(record, AddressBookDTO.class)).collect(Collectors.toList());
            return employeePayrollDTOList;
        } catch (RuntimeException e) {
            throw new PersonRecordNotFoundException("Person record not found for city " + city);
        }
    }

    @Override
    public List<AddressBookDTO> getPersonRecordByState(String state) {
        try {
            List<AddressBook> employeePayrollList = addressBookRepository.findByState(state);
            List<AddressBookDTO> employeePayrollDTOList = employeePayrollList.stream().map(record -> modelMapper.map(record, AddressBookDTO.class)).collect(Collectors.toList());
            return employeePayrollDTOList;
        } catch (RuntimeException e) {
            throw new PersonRecordNotFoundException("Person record not found for state " + state);
        }
    }


}
