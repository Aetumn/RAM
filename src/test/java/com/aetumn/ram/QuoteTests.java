package com.aetumn.ram;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.aetumn.ram.core.beans.Quote;

public class QuoteTests {

	@Test
	public void newQuoteTest() {
		String quoteString = "This is a new quote.";
		String authorString = "Aetumn";
		Quote quote = Quote.builder().text(quoteString).author(authorString).build();
		
		Assertions.assertThat(quote.getText()).isEqualTo(quoteString);
		Assertions.assertThat(quote.getAuthor()).isEqualTo(authorString);
		Assertions.assertThat(quote.getCreationDate()).isNotNull();
	}

}
