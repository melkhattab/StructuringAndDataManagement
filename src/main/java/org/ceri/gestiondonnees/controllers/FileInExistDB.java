package org.ceri.gestiondonnees.controllers;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.exist.util.FileUtils;
import org.exist.xmldb.XmldbURI;
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

	public final static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db/";
	public final static String driver = "org.exist.xmldb.DatabaseImpl";
	public static String collection ; 
	public static String file ;
	
	/**
	 * 
	 * @return true if the file is added to eXist xml database, false otherwise 
	 */
	public static boolean putFile(String fileName, String collection) {
		FileInExistDB.collection = collection;
		file = fileName ;
		try {
			Class<?> cl = Class.forName(driver);
			Database database = (Database)cl.newInstance();
			database.setProperty("create-database", "true");
			DatabaseManager.registerDatabase(database);
			 // try to get collection
			Collection col = 
				DatabaseManager.getCollection(URI + collection);
			if(col == null) {
	            // collection does not exist: get root collection and create.
	            // for simplicity, we assume that the new collection is a
	            // direct child of the root collection, e.g. /db/test.
	            // the example will fail otherwise.
				
	            Collection root = DatabaseManager.getCollection(URI + XmldbURI.ROOT_COLLECTION);
	            CollectionManagementService mgtService = 
	                (CollectionManagementService)root.getService("CollectionManagementService", "1.0");
	            col = mgtService.createCollection(collection.substring((XmldbURI.ROOT_COLLECTION + "/").length()));
	        }
			Path f = Paths.get(file);
	        // create new XMLResource
			XMLResource document = (XMLResource)col.createResource(FileUtils.fileName(f), XMLResource.RESOURCE_TYPE);
			document.setContent(f);
			System.out.print("storing document " + document.getId() + "...");
			col.storeResource(document);
			System.out.println("ok.");
			return true ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false ;
		}					
       
	}
	
	/**
	 * 
	 * @param xQuery correspond to the xquery for finding data from eXist xml database 
	 */
	public void getFile( String xQuery) {
		try {
		Class<?> cl = Class.forName(driver);			
		Database database = (Database)cl.newInstance();
		DatabaseManager.registerDatabase(database);
		
        // try to get collection
		Collection col = DatabaseManager.getCollection(URI + collection);
		if(col == null) {
            // collection does not exist: get root collection and create.
            // for simplicity, we assume that the new collection is a
            // direct child of the root collection, e.g. /db/test.
            // the example will fail otherwise.
            Collection root = DatabaseManager.getCollection(URI + XmldbURI.ROOT_COLLECTION);
            CollectionManagementService mgtService = 
                (CollectionManagementService)root.getService("CollectionManagementService", "1.0");
            col = mgtService.createCollection(collection.substring((XmldbURI.ROOT_COLLECTION + "/").length()));
        }
		
		XQueryService service = (XQueryService) col.getService("XQueryService","1.0");
		service.setProperty("indent", "yes");
		ResourceSet result = service.query(xQuery);
		ResourceIterator iterator =result.getIterator();
		System.out.println("ok."+result.getSize());
		while(iterator.hasMoreResources()) {
			Resource r = iterator.nextResource();
			String value = (String) r.getContent();
			System.out.println(value);
		}
		}catch(Exception e ) {
			System.out.println("Error : "+e.getMessage());
		}
	}
	/**
	 * This methods create the xml metadata file associated for a file uploaded on the server.
	 * @param tags : represents the Map of the pair (tagName - tagValue) of the xml file 
	 * @return : the created xml file.
	 */
	
	public File createXMLMetadataFile(Map<String, String> tags) {
		return null ; 
	}
}
