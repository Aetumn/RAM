package com.aetumn.ram.core.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetumn.ram.core.beans.Quote;
import com.aetumn.ram.core.repository.IQuoteRepository;

@Service
public class QuoteService {
	
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	IQuoteRepository quoteRepository;
	
	public Quote create(Quote quote){
		quote.setCreationDate(LocalDateTime.now());
		return save(quote);
	}
	
	public Quote save(Quote quote) {
		try {
			return quoteRepository.save(quote);
		}catch(Exception e) {
			logger.error("Could not save quote : " + e.getMessage());
			return null;
		}
	}
	
	public List<Quote> getQuotesByAuthor(String author){
		if(author == null) {
			return quoteRepository.findAll();
		}
		return quoteRepository.findByAuthor(author);
	}
}
