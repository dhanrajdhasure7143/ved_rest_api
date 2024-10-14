package com.ved_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ved_api.entity.ContactNotebook;
import com.ved_api.responseTemplate.ResponseTemplate;
import com.ved_api.service.ContactNotebookService;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/api/v1/contacts")
public class ContactNotebookController {

	@Autowired
	ContactNotebookService contactService;

	@GetMapping
	public ResponseEntity<ResponseTemplate<List<ContactNotebook>>> getAllContacts() {
		List<ContactNotebook> contacts = contactService.getAllContacts();
		return ResponseEntity.ok(new ResponseTemplate<>("success", "Fetched all contacts", contacts));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseTemplate<ContactNotebook>> getContactById(@PathVariable int id) {
		ContactNotebook contact = contactService.getContactById(id);
		if (contact != null) {
			return ResponseEntity.ok(new ResponseTemplate<>("success", "Contact found", contact));
		}
		return ResponseEntity.status(404).body(new ResponseTemplate<>("error", "Contact not found", null));
	}

	@PostMapping("/create")
	public ResponseEntity<ResponseTemplate<ContactNotebook>> addContact(@RequestBody ContactNotebook contact) {
		ContactNotebook savedContact = contactService.addContact(contact);
		return ResponseEntity.status(201).body(new ResponseTemplate<>("success", "Contact added", savedContact));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseTemplate<ContactNotebook>> updateContact(@PathVariable int id,
			@RequestBody ContactNotebook contact) {
		ContactNotebook updatedContact = contactService.updateContact(id, contact);
		if (updatedContact != null) {
			return ResponseEntity.ok(new ResponseTemplate<>("success", "Contact updated", updatedContact));
		}
		return ResponseEntity.status(404).body(new ResponseTemplate<>("error", "Contact not found", null));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseTemplate<Void>> deleteContact(@PathVariable int id) {
		contactService.deleteContact(id);
		return ResponseEntity.status(204).body(new ResponseTemplate<>("success", "Contact deleted", null));
	}
}
