package com.ved_api.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ved_api.entity.ContactNotebook;
import com.ved_api.repository.ContactNotebookRepository;

@Service
public class ContactNotebookService {

	@Autowired
	ContactNotebookRepository contactRepository;
	private static final Random random = new Random();
	
	public List<ContactNotebook> getAllContacts() {
        return contactRepository.findAll();
    }

    public ContactNotebook getContactById(int id) {
        return contactRepository.findById(id).orElse(null);
    }

    public ContactNotebook addContact(ContactNotebook contact) {
        contact.setId(getNextId());
        return contactRepository.save(contact);
    }

//    public ContactNotebook updateContact(int id, ContactNotebook updatedContact) {
//    	ContactNotebook contact = contactRepository.findById(id).orElse(null);
//        if (contact != null) {
//            updatedContact.setId(id);
//            return contactRepository.save(updatedContact);
//        }
//        return null;
//    }
    
    public ContactNotebook updateContact(int id, ContactNotebook updatedContact) {
        ContactNotebook existingContact = contactRepository.findById(id).orElse(null);
        if (existingContact != null) {
            // Only update the fields that are not null in the updatedContact object
            if (updatedContact.getName() != null) {
                existingContact.setName(updatedContact.getName());
            }
            if (updatedContact.getMobileNumber() != null) {
                existingContact.setMobileNumber(updatedContact.getMobileNumber());
            }
            if (updatedContact.getEmail() != null) {
                existingContact.setEmail(updatedContact.getEmail());
            }
            if (updatedContact.getRelation() != null) {
                existingContact.setRelation(updatedContact.getRelation());
            }
            if (updatedContact.getInstagramId() != null) {
                existingContact.setInstagramId(updatedContact.getInstagramId());
            }
            if (updatedContact.getWhatsappNumber() != null) {
                existingContact.setWhatsappNumber(updatedContact.getWhatsappNumber());
            }
            if (updatedContact.getPhoto() != null) {
                existingContact.setPhoto(updatedContact.getPhoto());
            }

            // Save the updated contact
            return contactRepository.save(existingContact);
        }
        return null; // Return null if contact not found
    }


    public void deleteContact(int id) {
        contactRepository.deleteById(id);
    }

    private int getNextId() {
        List<ContactNotebook> contacts = contactRepository.findAll();
        return random.nextInt(1_000_000_000) + 1 +  contacts.size() + 1;
    }
}
