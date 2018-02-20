package com.aetumn.ram;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.aetumn.ram.core.beans.Quote;
import com.aetumn.ram.core.repository.IQuoteRepository;
import com.aetumn.ram.core.service.QuoteService;

@RunWith(SpringRunner.class)
@SpringBootTest()
@Sql(scripts = "classpath:sql/dropQuotes.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class QuoteServiceTests {

	@Mock
	IQuoteRepository quoteRepository;

	@InjectMocks
	QuoteService qService;

	@Test
	public void singleTextTest() {
		String text = "totot";
		String author = "skdwldkv";
		Quote quote2 = Quote.builder().text(text).author(author).build();
		when(quoteRepository.save(quote2)).thenThrow(new DataIntegrityViolationException("double exception"));
		Quote dbq2 = qService.save(quote2);

		assertThat(dbq2).isNull();
	}

	@Test
	public void noTextTest() {
		when(quoteRepository.save(any(Quote.class))).thenThrow(new DataIntegrityViolationException("null exception"));
		Quote quote = Quote.builder().build();
		Quote dbq = qService.save(quote);

		assertThat(dbq).isNull();
	}

	@Test
	public void getQuoteByAuthorTest() {
		String author = "toto";
		qService.getQuotesByAuthor(author);
		Mockito.verify(quoteRepository).findByAuthor(author);
		
		qService.getQuotesByAuthor(null);
		Mockito.verify(quoteRepository).findAll();
	}

}
