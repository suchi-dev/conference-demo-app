package com.sample.springboot.conference.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.springboot.conference.models.Speaker;

public interface SpeakerRepository extends JpaRepository<Speaker, Long>{

}
