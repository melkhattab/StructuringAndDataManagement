package org.ceri.gestiondonnees.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.ceri.gestiondonnees.models.FileData;
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
			String collection ="/db/"+fileData.getSelectedCorpus();
			Collection col = 
				DatabaseManager.getCollection(URI + collection, userName, password);
			
			if(col == null) {			
//				FileInExistDB.collection += fileData.getSelectedCorpus();
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
		ArrayList<String> xQueryResult = new ArrayList<String>();
		try {
			
			Class<?> cl = Class.forName(driver);			
			Database database = (Database)cl.newInstance();
			DatabaseManager.registerDatabase(database);
			
	        // try to get collection
			String collection ="/db/";
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
			System.out.println("================================================================================ "+result.toString());
			while(iterator.hasMoreResources()) {
				Resource r = iterator.nextResource();
				String value = (String) r.getContent();
				xQueryResult.add(value);
			}
			return xQueryResult ; 
		}
		catch(Exception e ){
			System.out.println("Error : "+e.getMessage());
			return null ; 
		}
		
	}
	/**
	 * getFile, search in metadata xml files an provides 
	 * @param xQuery correspond to the xquery for finding data from eXist xml database 
	 */
	public static StringBuilder getMoreContext(String id, boolean before, int size) {
		String xQuery ;
		if(before ==true){
			xQuery =  "let $id := "+id+" let $doc := /DOCUMENT for $word in $doc/*/WORD where $word[@id >= -"+size+"+$id and @id < $id] return concat($word,\"\")";
		}
		else {
			xQuery  =   "let $id := "+id+" let $doc := /DOCUMENT for $word in $doc/*/WORD where $word[@id > $id and @id <= $id+"+size+"] return concat($word,\"\")";
		}
		
		StringBuilder context = new StringBuilder();
		try {
			
			Class<?> cl = Class.forName(driver);			
			Database database = (Database)cl.newInstance();
			DatabaseManager.registerDatabase(database);
			
	        // try to get collection
			String collection ="/db/";
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
				context.append(value+" ");
			}
			return context ; 
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
			Element rootElement = doc.createElement("DOCUMENT");
			doc.appendChild(rootElement);
			
			Element title = doc.createElement("TITLE");
			title.appendChild(doc.createTextNode(fileData.getFileName()));
			rootElement.appendChild(title);
			
			Element author = doc.createElement("AUTHOR");
			author.appendChild(doc.createTextNode(fileData.getAuthor()));
			rootElement.appendChild(author);
			
			Element description = doc.createElement("DESCRIPTION");
			description.appendChild(doc.createTextNode(fileData.getDescription()));
			rootElement.appendChild(description);
			
			Element path = doc.createElement("PATH");
			path.appendChild(doc.createTextNode(fileData.getPath()));
			rootElement.appendChild(path);
			
			Element date = doc.createElement("DATE");
			date.appendChild(doc.createTextNode(fileData.getDate()));
			rootElement.appendChild(date);
			
			Element corpus = doc.createElement("CORPUS");
			corpus.appendChild(doc.createTextNode(fileData.getSelectedCorpus()));
			rootElement.appendChild(corpus);
			
			Element content = doc.createElement("CONTENT");
			rootElement.appendChild(content);
			File textFile = new File(fileData.getPath());
			Scanner scanner = new Scanner(textFile);
			Scanner scanner2 = null ;
			int id = 1 ;
			while(scanner.hasNextLine()) {
				scanner2 = new Scanner(scanner.nextLine());
				while (scanner2.hasNext()) {
					String word = scanner2.next();
					Element wordNode = doc.createElement("WORD");
					wordNode.setAttribute("id", ""+id++);
					wordNode.appendChild(doc.createTextNode(word.toString()));
					content.appendChild(wordNode);
				}
			}
			scanner.close();
			scanner2.close();
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			
			String rootPath = System.getProperty("user.dir") ;
			java.io.File xmlFile = new java.io.File(rootPath+"/"+fileData.getFileName()+".xml") ;
			StreamResult result = new StreamResult(xmlFile);
			
			transformer.transform(source, result);
			System.out.println("File saved in : "+rootPath+": file : "+xmlFile);
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}
}
