package org.ceri.gestiondonnees.models;

import java.util.Collection;

import org.ceri.gestiondonnees.entities.Corpus;
import org.ceri.gestiondonnees.entities.User;
import org.springframework.web.multipart.MultipartFile;

public class FileData {
	
	/* metadata*/
	private String title ;
	private String author ;
	private String date ;
	private String description ;
	private String nbr ;
	
	private String fileName;
	private String fileType ;
	private String path ;
	private String size ;
	private User owner ;
	private Collection<Corpus> corpus ;
	private String selectedCorpus ;
	private MultipartFile file ; 
	private String errorMessage ; 
	
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAuthor() {
		return author;
	}
	public String getDate() {
		return date;
	}
	public String getNbr() {
		return nbr;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setNbr(String nbr) {
		this.nbr = nbr;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String name) {
		this.fileName = name;
	}
	
	public String getFileType() {
		return fileType;
	}
	public String getPath() {
		return path;
	}
	public String getSize() {
		return size;
	}
	public User getOwner() {
		return owner;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public Collection<Corpus> getCorpus() {
		return corpus;
	}
	public String getSelectedCorpus() {
		return selectedCorpus;
	}
	public void setCorpus(Collection<Corpus> corpus) {
		this.corpus = corpus;
	}
	public void setSelectedCorpus(String selectedCorpus) {
		this.selectedCorpus = selectedCorpus;
	}
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
