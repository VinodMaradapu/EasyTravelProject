package com.travel.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.bean.ContactUs;
import com.travel.repository.ContactRepository;
import com.travel.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService{

	@Autowired
	ContactRepository contactRepository;
	@Override
	  public void saveContactMessage(ContactUs message) {
		contactRepository.save(message);
    }
}
