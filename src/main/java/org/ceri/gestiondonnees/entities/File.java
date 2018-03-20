package org.ceri.gestiondonnees.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class File implements Serializable

{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_file")
	private int idFile ; 
	private String name ;
	private String path ;
	private String fileType;
	private Long size ; 
	
	@ManyToOne
	@JoinColumn(name="id_corpus")
	private Corpus corpus ;
	
	public File() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public File(String name, String path, String fileType, Long size) {
		super();
		this.name = name;
		this.path = path;
		this.fileType = fileType;
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Corpus getCorpus() {
		return corpus;
	}

	public void setCorpus(Corpus corpus) {
		this.corpus = corpus;
	}
	
}
