package com.aetumn.ram.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aetumn.ram.core.beans.Quote;

public interface IQuoteRepository extends JpaRepository<Quote, Long> {

	List<Quote> findByAuthor(String Author);
}
