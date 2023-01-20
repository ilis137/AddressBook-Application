package com.example.addressbookbackend.Controller;

import com.example.addressbookbackend.DTO.AddressBookDTO;
import com.example.addressbookbackend.DTO.ResponseDTO;
import com.example.addressbookbackend.Exceptions.PersonRecordNotFoundException;
import com.example.addressbookbackend.Services.IAddressBookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
@RestController
public class AddressBookController {


    @Autowired
    private IAddressBookService addressService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> saveAddress(@RequestBody @Valid AddressBookDTO addressBookDTO) {
        ResponseDTO responseDTO = new ResponseDTO("Created data Successfully", addressService.savePersonRecord(addressBookDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<ResponseDTO> getAddresses() {
        ResponseDTO responseDTO = new ResponseDTO("Got all the data", addressService.getAllPersonRecords());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<ResponseDTO> getDataById(@PathVariable int id){
        ResponseDTO responseDTO = new ResponseDTO("Got data for Id: " + id, addressService.getPersonRecordById(id));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateDataById(@PathVariable int id, @RequestBody @Valid AddressBookDTO addressBookDTO) {
        ResponseDTO responseDTO =new ResponseDTO("Updated data for given id: " + id, addressService.updatePersonRecordById(id, addressBookDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDataById(@PathVariable int id) {
        addressService.deletePersonRecordById(id);
        return new ResponseEntity<>("Deleted data for id: "+ id , HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseDTO> getByName(@RequestParam ("name") String name) throws PersonRecordNotFoundException {
        ResponseDTO responseDTO = new ResponseDTO("Got data for given name: " + name, addressService.getPersonRecordByName(name));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteByName(@RequestParam ("name") String name) throws PersonRecordNotFoundException {
        addressService.deletePersonRecordByName(name);
        return new ResponseEntity<>("Deleted data for name: "+ name , HttpStatus.OK);
    }
}
