package com.aetumn.ram.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aetumn.ram.core.beans.Quote;
import com.aetumn.ram.core.service.QuoteService;

@RestController
@RequestMapping("/quote")
public class QuoteRestController {

	
	@Autowired QuoteService quoteService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Quote> getQuotes(@RequestParam(value = "author", required = false) String author) {
		return quoteService.getQuotesByAuthor(author);
	}

	@RequestMapping(path="/add", method = RequestMethod.POST)
	public ResponseEntity<?> add(@RequestBody Quote quote) {
		quoteService.save(quote);
		return null;
	}

	@RequestMapping(path="/addMany", method = RequestMethod.POST)
	public ResponseEntity<?> add(@RequestBody List<Quote> quotes) {
		quotes.stream().forEach(quote -> quoteService.save(quote));
		return null;
	}
}
