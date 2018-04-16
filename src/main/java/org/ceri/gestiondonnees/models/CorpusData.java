package org.ceri.gestiondonnees.models;

import org.ceri.gestiondonnees.entities.Laboratory;

public class CorpusData {
	
	private String corpusName;
	private String description ;
	private String laboratory ;
	private String errorMessage ;
	private Long capacity  ;
	public String getCorpusName() {
		return corpusName;
	}
	public void setCorpusName(String corpusName) {
		this.corpusName = corpusName;
	}
	
	public Long getCapacity() {
		return capacity;
	}
	public void setCapacity(Long capacity) {
		this.capacity = capacity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		description = description;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public String getLaboratory() {
		return laboratory;
	}
	public void setLabraory(String laboratory) {
		this.laboratory = laboratory;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
