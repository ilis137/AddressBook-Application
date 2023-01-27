package com.example.addressbookbackend.Services;

import com.example.addressbookbackend.DTO.AddressBookDTO;
import com.example.addressbookbackend.Exceptions.PersonRecordNotFoundException;
import com.example.addressbookbackend.Repository.AddressBookRepository;
import com.example.addressbookbackend.Util.EmailSenderService;
import com.example.addressbookbackend.Util.JWTUtil;
import com.example.addressbookbackend.model.AddressBook;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AddressBookService implements IAddressBookService {
    private static final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    AddressBookRepository addressBookRepository;

    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    JWTUtil jwtUtil;
    @Override
    public AddressBookDTO savePersonRecord(AddressBookDTO addressBookDTO) {
        AddressBook record = modelMapper.map(addressBookDTO, AddressBook.class);
        record = addressBookRepository.save(record);
        emailSenderService.sendEmail(record.getEmail(),"Test Mail", "Hii...."+record.getName()+" your details are added!\n\n Name:  "+record.getName()+"\n Phone number:  "+record.getPhoneNumber()+"\n Email:  "+record.getEmail()+"\n Address:  "+record.getAddress()+"\n City:  "+record.getCity()+"\n State:  "+record.getState()+"\n ZipCode:  "+record.getZip());
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
            Optional<AddressBook> record=addressBookRepository.findById(id);
            addressBookRepository.deleteById(id);
            emailSenderService.sendEmail(record.get().getEmail(), "Test Mail","Hii....! Your details has been deleted!");
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

    @Override
    public String CreateRecordAndToken(AddressBookDTO addressBookDTO) {
        AddressBook record = modelMapper.map(addressBookDTO, AddressBook.class);
        record = addressBookRepository.save(record);
        emailSenderService.sendEmail(record.getEmail(),"Test Mail", "Hii...."+record.getName()+" your details are added!\n\n Name:  "+record.getName()+"\n Phone number:  "+record.getPhoneNumber()+"\n Email:  "+record.getEmail()+"\n Address:  "+record.getAddress()+"\n City:  "+record.getCity()+"\n State:  "+record.getState()+"\n ZipCode:  "+record.getZip());
        String token=jwtUtil.createToken(record.getId());
        return token;
    }
    @Override
    public AddressBookDTO getRecordByToken(String token){
        int id=jwtUtil.decodeToken(token);
        Optional<AddressBook> record=addressBookRepository.findById(id);
        if(record.isPresent()){
            return modelMapper.map(record.get(), AddressBookDTO.class);
        }else {
            throw new PersonRecordNotFoundException("Sorry! cannot find the token: "+token);
        }
    }


    @Override
    public List<AddressBookDTO> getAllRecordsByToken(String token){
        int id=jwtUtil.decodeToken(token);
        Optional<AddressBook> record=addressBookRepository.findById(id);
        if(record.isPresent()){
          return getAllPersonRecords();
        }else {
            throw new PersonRecordNotFoundException("Sorry! cannot find the token: "+token);
        }
    }

    @Override
    public AddressBookDTO updateRecordsByToken(String token, AddressBookDTO addressBookDTO){
        int id=jwtUtil.decodeToken(token);
        AddressBook addressBookData = addressBookRepository.findById(id).orElseThrow(() -> new PersonRecordNotFoundException("Person record not found for  token " + token));
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
    public void deleteRecordsByToken(String token){
        int id=jwtUtil.decodeToken(token);

        try {
            Optional<AddressBook> record=addressBookRepository.findById(id);
            addressBookRepository.deleteById(id);
            emailSenderService.sendEmail(record.get().getEmail(), "Test Mail","Hii....! Your details has been deleted!");
        } catch (RuntimeException ex) {
            throw new PersonRecordNotFoundException("Person record not found for token " + token);
        }
    }

}
