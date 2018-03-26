package org.ceri.gestiondonnees.models;

import org.ceri.gestiondonnees.entities.Laboratory;

public class CorpusData {
	
	private String corpusName;
	private String Description ;
	private String laboratory ;
	private String errorMessage ;
	
	public String getCorpusName() {
		return corpusName;
	}
	public void setCorpusName(String corpusName) {
		this.corpusName = corpusName;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
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
