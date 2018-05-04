package org.ceri.gestiondonnees.controllers;



import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.ceri.gestiondonnees.models.FileData;
import org.exist.util.FileUtils;
import org.exist.xmldb.XmldbURI;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XQueryService;

/**
 * 
 * @author EL KHATTAB - MAHMOUD
 *
 */
public class FileInExistDB {

	public final static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
					    
	public final static String driver = "org.exist.xmldb.DatabaseImpl";
	public static String collection ="/db/";
	public static String file ;
	
	/**
	 * 
	 * This static method will first create xml metadata file and then add it to an xml 
	 * database collection which has the same name as file's corpus if exists, otherwise 
	 * it will create a new collection and adding the xml file metadata to it
	 * 
	 * @param fileData, information on the file added by contributor, these information will be used to create xml file metadata
	 * @param userName, used to access to xml database 
	 * @param password, the password for xml database access in order to store metadata file
	 * 
	 */
	public static boolean putFile(FileData fileData, String userName, String password) throws Exception{
		
		createXmlMetadataFile(fileData);		
		FileInExistDB.file = System.getProperty("user.dir")+"\\"+fileData.getFileName()+".xml" ;
		try {
			
			Class<?> cl = Class.forName(driver);
			Database database = (Database)cl.newInstance();
			database.setProperty("create-database", "true");
			DatabaseManager.registerDatabase(database);
			
//			 // try to get collection
			Collection col = 
				DatabaseManager.getCollection(URI + collection, userName, password);
			
			if(col == null) {			
				FileInExistDB.collection += fileData.getSelectedCorpus();
	            Collection root = DatabaseManager.getCollection(URI + XmldbURI.ROOT_COLLECTION_URI);
	            CollectionManagementService mgtService = 
	                (CollectionManagementService)root.getService("CollectionManagementService", "1.0");
	            col = mgtService.createCollection(collection.substring((XmldbURI.ROOT_COLLECTION_URI + "/").length()));
	        }
			XMLResource document = (XMLResource)col.createResource(fileData.getFileName()+".xml", XMLResource.RESOURCE_TYPE);
			File content = new File(file);
			document.setContent(content);
			col.storeResource(document);
			content.delete();
			System.out.println("file deleted");
			return true ;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("exception message : "+e.getMessage());
			return false ;
		}			
		       
	}
	
	/**
	 * getFile, search in metadata xml files an provides 
	 * @param xQuery correspond to the xquery for finding data from eXist xml database 
	 */
	public static List<String> getFile( String xQuery) {
		ArrayList<String> xQueryResult = new ArrayList<String>(10);
		try {
			
			Class<?> cl = Class.forName(driver);			
			Database database = (Database)cl.newInstance();
			DatabaseManager.registerDatabase(database);
			
	        // try to get collection
			Collection col = DatabaseManager.getCollection(URI + collection);
			
			//if not exists create a new one 
			if(col == null) {
	            Collection root = DatabaseManager.getCollection(URI + XmldbURI.ROOT_COLLECTION_URI);
	            CollectionManagementService mgtService = 
	                (CollectionManagementService)root.getService("CollectionManagementService", "1.0");
	            col = mgtService.createCollection(collection.substring((XmldbURI.ROOT_COLLECTION_URI + "/").length()));
	        }
			
			XQueryService service = (XQueryService) col.getService("XQueryService","1.0");
			service.setProperty("indent", "yes");
			ResourceSet result = service.query(xQuery);
			ResourceIterator iterator =result.getIterator();
			while(iterator.hasMoreResources()) {
				Resource r = iterator.nextResource();
				String value = (String) r.getContent();
				xQueryResult.add(value);
				System.out.println(value);
			}
			return null ; 
		}
		catch(Exception e ){
			System.out.println("Error : "+e.getMessage());
			return null ; 
		}
		
	}
	/**
	 * This methods create the xml metadata file associated for a file uploaded on the server.
	 * @param tags : represents the Map of the pair (tagName - tagValue) of the xml file 
	 * @return : the created xml file.
	 */
	
	private static String createXmlMetadataFile(FileData fileData) {
		
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
			author.appendChild(doc.createTextNode(fileData.getAuthor()));
			rootElement.appendChild(author);
			
			Element description = doc.createElement("description");
			description.appendChild(doc.createTextNode(fileData.getDescription()));
			rootElement.appendChild(description);
			
			Element nbrPages = doc.createElement("pages");
			nbrPages.appendChild(doc.createTextNode(fileData.getNbr()));
			rootElement.appendChild(nbrPages);
			
			Element date = doc.createElement("date");
			date.appendChild(doc.createTextNode(fileData.getDate()));
			rootElement.appendChild(date);
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			
			String rootPath = System.getProperty("user.dir") ;
			
			java.io.File xmlFile = new java.io.File(rootPath+"/"+fileData.getFileName()+".xml") ;
			StreamResult result = new StreamResult(xmlFile);
			
			transformer.transform(source, result);
			System.out.println("File saved in : "+rootPath);
			System.out.println("======================================================== : "+xmlFile);
			
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
