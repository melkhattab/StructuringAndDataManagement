package org.ceri.gestiondonnees.controllers;

import java.io.File;
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
 * Add a document to the database.
 * 
 * Call with java -jar start.jar org.exist.examples.xmldb.Put collection docName
 *
 */
public class MedataDataFileManipulation {

	public final static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
	public final static String driver = "org.exist.xmldb.DatabaseImpl";
	public final static String collection = "/db/moliere";
	
	public static void put() throws Exception{
		/*
		if(args.length < 2)
			usage();
		*/
		String collection = "/db/mahmoud"; 
		String file = "exemple1.xml";

        // initialize driver
		String driver = "org.exist.xmldb.DatabaseImpl";
		
		Class<?> cl = Class.forName(driver);			
		Database database = (Database)cl.newInstance();
		database.setProperty("create-database", "true");
		DatabaseManager.registerDatabase(database);
		
        // try to get collection
		Collection col = 
			DatabaseManager.getCollection(URI + collection,"admin","admin");
		if(col == null) {
            // collection does not exist: get root collection and create.
            // for simplicity, we assume that the new collection is a
            // direct child of the root collection, e.g. /db/test.
            // the example will fail otherwise.
			
            Collection root = DatabaseManager.getCollection(URI + XmldbURI.ROOT_COLLECTION_URI);
            CollectionManagementService mgtService = 
                (CollectionManagementService)root.getService("CollectionManagementService", "1.0");
            col = mgtService.createCollection(collection.substring((XmldbURI.ROOT_COLLECTION_URI + "/").length()));
        }
//		Path f = Paths.get(file);
        
		// create new XMLResource
		XMLResource document = (XMLResource)col.createResource(FileUtils.dirname(file), XMLResource.RESOURCE_TYPE);
		File content = new File(file);
		document.setContent(content);
		
		System.out.println("storing document " + content + "...");
		col.storeResource(document);
		System.out.println("ok.");
	}
	
	
	public  static void get() throws Exception{
		
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
			
            Collection root = DatabaseManager.getCollection(URI + XmldbURI.ROOT_COLLECTION_CONFIG_URI);
            CollectionManagementService mgtService = 
                (CollectionManagementService)root.getService("CollectionManagementService", "1.0");
            col = mgtService.createCollection(collection.substring((XmldbURI.ROOT_COLLECTION_URI + "/").length()));
        }
		
		String xquery = "for $x in /bookstores/book/author[1]/text() return $x" ;
		XQueryService service = (XQueryService) col.getService("XQueryService","1.0");
		service.setProperty("indent", "yes");
		ResourceSet result = service.query(xquery);
		ResourceIterator iterator =result.getIterator();
		System.out.println("ok."+result.getSize());
		while(iterator.hasMoreResources()) {
			Resource r = iterator.nextResource();
			String value = (String) r.getContent();
			System.out.println(value);
		}
		System.out.println("search finish");
	}
}

