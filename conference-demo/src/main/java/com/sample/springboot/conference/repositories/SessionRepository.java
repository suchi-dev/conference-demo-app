package com.sample.springboot.conference.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.springboot.conference.models.Session;

public interface SessionRepository extends JpaRepository<Session, Long>{

}
