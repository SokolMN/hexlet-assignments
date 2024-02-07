package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import exercise.model.Contact;
import exercise.repository.ContactRepository;
import exercise.dto.ContactDTO;
import exercise.dto.ContactCreateDTO;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactRepository contactRepository;

    // BEGIN
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO createContact(@RequestBody ContactCreateDTO contactCreateDTODTO){
        var contact = fromContactCreatedDTOToContact(contactCreateDTODTO);
        contactRepository.save(contact);
        return fromContactToContactDTO(contact);
    }

    private Contact fromContactCreatedDTOToContact(ContactCreateDTO contactCreateDTODTO){
        Contact contact = new Contact();
        contact.setFirstName(contactCreateDTODTO.getFirstName());
        contact.setLastName(contactCreateDTODTO.getLastName());
        contact.setPhone(contactCreateDTODTO.getPhone());
        return contact;
    }

    private ContactDTO fromContactToContactDTO(Contact contact ){
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setCreatedAt(contact.getCreatedAt());
        contactDTO.setUpdatedAt(contact.getUpdatedAt());
        contactDTO.setId(contact.getId());
        contactDTO.setPhone(contact.getPhone());
        contactDTO.setLastName(contact.getLastName());
        contactDTO.setFirstName(contact.getFirstName());
        return contactDTO;
    }
    // END
}
