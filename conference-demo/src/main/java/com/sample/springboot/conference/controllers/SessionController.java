package com.sample.springboot.conference.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sample.springboot.conference.models.Session;
import com.sample.springboot.conference.repositories.SessionRepository;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionController {

	
	@Autowired
	private SessionRepository sessionRepository;
	
	
	@GetMapping
	public List<Session> getSessions(){
		return sessionRepository.findAll();
	}
	
	@GetMapping
	@RequestMapping("{id}")
	public Session get(@PathVariable Long id) {
		return sessionRepository.getOne(id);
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Session createSession(@RequestBody final Session session) {
		return sessionRepository.saveAndFlush(session);
		
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		//Also need to check for child records before deleting
		sessionRepository.deleteById(id);
	}
	
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public Session updateSession(@PathVariable Long id, @RequestBody final Session session) {
		// A PUT method expects all the attributes to be present
		// A Patch expects only the attributes which are to be updated.
		
		//TODO: validation if all attributes are present.
		//If not then we need to return a 400 bad payload
		
		Session existingSession = sessionRepository.getOne(id);
		BeanUtils.copyProperties(session, existingSession, "session_id");
		return sessionRepository.saveAndFlush(existingSession);
		
		
		
	}
	
	
}
