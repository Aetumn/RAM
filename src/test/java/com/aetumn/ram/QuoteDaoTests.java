package com.aetumn.ram;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.aetumn.ram.core.beans.Quote;
import com.aetumn.ram.core.repository.IQuoteRepository;

@RunWith(SpringRunner.class)
@SpringBootTest()
@Sql(scripts = "classpath:sql/dropQuotes.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class QuoteDaoTests {

	@Autowired
	IQuoteRepository qRepository;

	@Test
	public void saveQuoteTest() {
		String author = "loulou";
		String text = "totot";
		Quote quote = Quote.builder().text(text).author(author).build();

		Quote dbQuote = qRepository.save(quote);

		assertThat(quote.getText()).isEqualTo(dbQuote.getText());
		assertThat(quote.getAuthor()).isEqualTo(dbQuote.getAuthor());
		assertThat(quote.getCreationDate()).isEqualTo(dbQuote.getCreationDate());
		assertThat(dbQuote.getId()).isNotNull();
	}

	@Test
	public void getQuoteByAuthorTest() {
		String author = "lala";
		String author2 = "riri";
		Quote quote1 = Quote.builder().text("totot").author(author).build();
		Quote quote2 = Quote.builder().text("kesdofsi").author(author).build();
		Quote quote3 = Quote.builder().text("soidfjs").author(author).build();
		Quote quote4 = Quote.builder().text("wkjnc").author(author2).build();
		Quote quote5 = Quote.builder().text("kcsnc").author(author2).build();
		Quote quote6 = Quote.builder().text("lsiejfjos").author(author).build();
		qRepository.save(quote1);
		qRepository.save(quote2);
		qRepository.save(quote3);
		qRepository.save(quote4);
		qRepository.save(quote5);
		qRepository.save(quote6);

		List<Quote> listByAuthor = qRepository.findByAuthor(author);

		assertThat(listByAuthor.size()).isEqualTo(4);
	}

	@Test
	public void removeQuoteTest() {
		String author = "lala";
		Quote quote1 = Quote.builder().text("totot").author(author).build();
		Quote quote2 = Quote.builder().text("kesdofsi").author(author).build();
		Quote q1 = qRepository.save(quote1);
		Quote q2 = qRepository.save(quote2);
		qRepository.delete(q1);
		List<Quote> listByAuthor = qRepository.findAll();

		assertThat(listByAuthor.size()).isEqualTo(1);
		
		Quote dbQuote = listByAuthor.get(0);
		
		assertThat(dbQuote).isEqualTo(q2);
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void singleTextTest() {
		String text = "totot";
		Quote quote = Quote.builder().text(text).build();
		Quote quote2 = Quote.builder().text(text).build();
		qRepository.save(quote);
		qRepository.save(quote2);
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void noTextTest() {
		Quote quote = Quote.builder().build();
		qRepository.save(quote);
	}

}
