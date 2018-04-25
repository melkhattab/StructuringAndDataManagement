package org.ceri.gestiondonnees.controllers;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.Collection;

import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.*;
import org.apache.commons.io.FilenameUtils;
import org.ceri.gestiondonnees.entities.Corpus;
import org.ceri.gestiondonnees.entities.File;
import org.ceri.gestiondonnees.entities.User;
import org.ceri.gestiondonnees.metier.IUserMetier;
import org.ceri.gestiondonnees.models.CorpusData;
import org.ceri.gestiondonnees.models.FileData;
import org.ceri.gestiondonnees.models.LaboratoryData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@Controller
public class FileController {

	@Autowired
	private IUserMetier metier ;
	
	@RequestMapping(value="files", method = RequestMethod.GET)
    public String displayAllFiles(Model model) {
		Collection<File> files = metier.getAllFiles();
		model.addAttribute("files", files);
		FileData fileData = new FileData() ; 
		fileData.setCorpus(metier.getAllCorpus());
		model.addAttribute("fileData", fileData );
        return "data/files";
    }
	
	@RequestMapping(value="getFiles", method = RequestMethod.POST)
    public String displayFiles(Model model, FileData fileInf ) {
		String name  = fileInf.getFileName();
		Collection<File> files = metier.getFilesByName(name);
		model.addAttribute("files", files);
        return "data/files";
    }
	@RequestMapping(value = "uploadFile", method = RequestMethod.GET)
	public String addCorpusToDB(Model model) {
		// this controller allows to create a new user account
		FileData fileData = new FileData() ; 
		fileData.setCorpus(metier.getAllCorpus());
		model.addAttribute("fileData",fileData);
		return "forms/uploadFile";
	}
	
	
	@RequestMapping(value = "deleteFile/{id}")
	public String deleteCorpus(@PathVariable("id") int id, Model model) {
		// this controller allows to create a new user account
		metier.deleteFile(id) ; 
		return "redirect:/files";
	}	
		
	/**
	 * 
	 * @param name
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/addFile", method = RequestMethod.POST)
	public  String uploadFileHandler(Model model, @RequestParam("file") MultipartFile file, FileData fileData) {
		
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				java.io.File dir = new java.io.File(rootPath + "/documents");
				if (!dir.exists())
					dir.mkdirs();
				
				// Create the file on server
				String path = dir.getAbsolutePath()+ "/"+ file.getOriginalFilename();
				java.io.File serverFile = new java.io.File(path);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				
				String result = addFileToDataBase(file, fileData);
			//	createXmlMetadata(fileData);
				createXmlMetadata(new FileData());
				model.addAttribute("result", "abcde");
				return "redirect:files" ;
			} catch (Exception e) {				
				e.printStackTrace();				
			}
		} 
		
		return "redirect:files" ;
	}
	
	private String addFileToDataBase(MultipartFile file, FileData fileData) {
		String name   = FilenameUtils.getBaseName(file.getOriginalFilename());
		String path   = file.getOriginalFilename();
		Long size   = file.getSize();
		String fileType = FilenameUtils.getExtension(file.getOriginalFilename());
		File existingFile = metier.getFileByName(name);
		if(existingFile!= null && existingFile.getSize().equals(size) && existingFile.getFileType().equals(fileType)) {
			return "le document est déjà existant" ;
		}
		else {
			Corpus selectedCorpus = metier.getCorpusByName(fileData.getSelectedCorpus()) ;
			File document = new File(name, path, fileType , size);
			document.setCorpus(selectedCorpus);
			metier.addFile(document);
			return "le document a été bien ajouté";
		}
	}
	
	private String createXmlMetadata(FileData fileData) {
		
		try {
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("Document");
			doc.appendChild(rootElement);
			
			Element title = doc.createElement("title");
			title.appendChild(doc.createTextNode(fileData.getTitle()));
			rootElement.appendChild(title);
			
			
			Element author = doc.createElement("author");
			title.appendChild(doc.createTextNode(fileData.getAuthor()));
			rootElement.appendChild(author);
			
			Element description = doc.createElement("description");
			title.appendChild(doc.createTextNode(fileData.getDescription()));
			rootElement.appendChild(description);
			
			Element nbrPages = doc.createElement("pages");
			title.appendChild(doc.createTextNode(fileData.getNbr()));
			rootElement.appendChild(nbrPages);
			
			Element date = doc.createElement("date");
			title.appendChild(doc.createTextNode(fileData.getDate()));
			rootElement.appendChild(date);
			
			// set attribute to staff element
			/*
			Attr attr = doc.createAttribute("id");
			attr.setValue("1");
			staff.setAttributeNode(attr);
			*/
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
	//		StreamResult result = new StreamResult(new java.io.File("C:\\file.xml"));

			// Output to console for testing
			 StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}
}