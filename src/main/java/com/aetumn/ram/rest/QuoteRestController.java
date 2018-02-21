package com.aetumn.ram.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aetumn.ram.core.beans.Quote;
import com.aetumn.ram.core.service.QuoteService;

@RestController
@RequestMapping("/quote")
public class QuoteRestController {

	
	@Autowired QuoteService quoteService;
	
	@GetMapping
	public List<Quote> getQuotes(@RequestParam(value = "author", required = false) String author) {
		return quoteService.getQuotesByAuthor(author);
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody Quote quote) {
		quoteService.save(quote);
		return null;
	}

	@PostMapping("/addMany")
	public ResponseEntity<?> add(@RequestBody List<Quote> quotes) {
		quotes.stream().forEach(quote -> quoteService.save(quote));
		return null;
	}
}
