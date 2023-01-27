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
@RequestMapping("/addressbook")
public class AddressBookController {


    @Autowired
    private IAddressBookService addressService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> saveAddress(@RequestBody @Valid AddressBookDTO addressBookDTO) {
        ResponseDTO responseDTO = new ResponseDTO("Created data Successfully", addressService.savePersonRecord(addressBookDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/fetch/all")
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
    public ResponseEntity<ResponseDTO> deleteDataById(@PathVariable int id) {
        addressService.deletePersonRecordById(id);
        ResponseDTO responseDTO =new ResponseDTO("Updated data for given id: " + id, true);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getbyname/{name}")
    public ResponseEntity<ResponseDTO> getByName(@PathVariable ("name") String name) throws PersonRecordNotFoundException {
        ResponseDTO responseDTO = new ResponseDTO("Got data for given name: " + name, addressService.getPersonRecordByName(name));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
//aqisrmkjfoqowtyr

    @GetMapping("/getbycity/{city}")
    public ResponseEntity<ResponseDTO> getByCity(@PathVariable("city") String city) throws PersonRecordNotFoundException {
        ResponseDTO responseDTO = new ResponseDTO("Got data for given city: " + city, addressService.getPersonRecordByCity(city));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getbystate/{state}")
    public ResponseEntity<ResponseDTO> getByState(@PathVariable("state") String state) throws PersonRecordNotFoundException {
        ResponseDTO responseDTO = new ResponseDTO("Got data for given state: " + state, addressService.getPersonRecordByState(state));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @PostMapping ("/generate")
    public ResponseEntity<ResponseDTO> generate(@Valid @RequestBody AddressBookDTO addressBookDTO) {
        String token=addressService.CreateRecordAndToken(addressBookDTO);
        ResponseDTO responseDTO=new ResponseDTO("address book record created!",token);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @GetMapping("/findByToken/{token}")
    public ResponseEntity<ResponseDTO> getByToken(@PathVariable String token){
        ResponseDTO responseDTO=new ResponseDTO("Details found!",addressService.getRecordByToken(token));
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @GetMapping("/getAllByToken/{token}")
    public ResponseEntity<ResponseDTO> findByToken(@PathVariable String token){
        ResponseDTO responseDTO=new ResponseDTO("Record found for the token!",addressService.getAllRecordsByToken(token));
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @PutMapping("/updateByToken/{token}")
    public ResponseEntity<ResponseDTO> updateByToken(@PathVariable String token,@Valid @RequestBody AddressBookDTO addressBookDTO){
        ResponseDTO responseDTO =new ResponseDTO("Updated data for given token: " + token, addressService.updateRecordsByToken(token, addressBookDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/deleteByToken/{token}")
    public ResponseEntity<ResponseDTO> deleteByToken(@PathVariable String token){
        addressService.deleteRecordsByToken(token);
        ResponseDTO responseDTO =new ResponseDTO("deleted data for given token: " + token, true);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
/*
* emailSenderService.sendEmail(addressBook.getEmail(),"Test Mail", "Hii...."+addressBook.getName()+" your details are added!\n\n Name:  "+addressBook.getName()+"\n Phone number:  "+addressBook.getPhoneNumber()+"\n Email:  "+addressBook.getEmail()+"\n Address:  "+addressBook.getAddress()+"\n City:  "+addressBook.getCity()+"\n State:  "+addressBook.getState()+"\n ZipCode:  "+addressBook.getZipCode());
        return addressBook;
* */
