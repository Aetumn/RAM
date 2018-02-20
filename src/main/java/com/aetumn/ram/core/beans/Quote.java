package com.aetumn.ram.core.beans;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "quote")
public class Quote {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name="text", unique=true, nullable=false)
	private String text;

	@Column(name="author")
	private String author;

	@Column(name="creation_date")
	private LocalDateTime creationDate;

	@Column(name="last_modif_date")
	private LocalDateTime lastModifDate;
	
	@Column(name="last_modif_user")
	private String lastModifUser;

	public static class QuoteBuilder {

		Quote quote;

		public QuoteBuilder() {
			quote = new Quote();
			quote.creationDate = LocalDateTime.now();
			quote.lastModifDate = LocalDateTime.now();
		}

		public QuoteBuilder text(String text) {
			quote.setText(text);
			return this;
		}

		public QuoteBuilder author(String author) {
			quote.setAuthor(author);
			return this;
		}

		public QuoteBuilder lastModifUser(String lastUpdateUser) {
			quote.setLastModifUser(lastUpdateUser);
			return this;
		}

		public Quote build() {
			return quote;
		}
	}

	public static QuoteBuilder builder() {
		return new QuoteBuilder();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quote other = (Quote) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (id != other.id)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Quote [id=" + id + ", text=" + text + ", author=" + author + ", creationDate=" + creationDate + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDateTime getLastModifDate() {
		return lastModifDate;
	}

	public void setLastModifDate(LocalDateTime lastModifDate) {
		this.lastModifDate = lastModifDate;
	}

	public String getLastModifUser() {
		return lastModifUser;
	}

	public void setLastModifUser(String lastModifUser) {
		this.lastModifUser = lastModifUser;
	}

}
