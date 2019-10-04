package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface GuestbookRepository extends CrudRepository<Guest, Long>{}
