package com.orastays.authserver.helper;


import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.blob.BlobContainerPublicAccessType;
import com.microsoft.azure.storage.blob.BlobRequestOptions;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.microsoft.azure.storage.blob.ListBlobItem;

@Component
public class AzureApp  {

	@Value("${azure.key}")
	private String azureKey;
	
	@Value("${azure.container}")
	private String azureContainer;

	public String uploadFile(MultipartFile multipartFile, String name) throws Exception {
		
		File sourceFile = new File(multipartFile.getOriginalFilename());
		sourceFile.createNewFile(); 
	    FileOutputStream fos = new FileOutputStream(sourceFile); 
	    fos.write(multipartFile.getBytes());
	    fos.close(); 
	    
		CloudStorageAccount storageAccount;
		CloudBlobClient blobClient = null;
		CloudBlobContainer container= null;

		// Parse the connection string and create a blob client to interact with Blob storage
		storageAccount = CloudStorageAccount.parse(azureKey);
		blobClient = storageAccount.createCloudBlobClient();
		container = blobClient.getContainerReference(azureContainer);

		// Create the container if it does not exist with public access.
		System.out.println("Creating container: " + container.getName());
		container.createIfNotExists(BlobContainerPublicAccessType.CONTAINER, new BlobRequestOptions(), new OperationContext());		    

		//Getting a blob reference
		CloudBlockBlob blob = container.getBlockBlobReference(name);

		//Creating blob and uploading file to it
		System.out.println("Uploading the sample file ");
		blob.uploadFromFile(sourceFile.getAbsolutePath());

		//Listing contents of container
		for (ListBlobItem blobItem : container.listBlobs()) {
			System.out.println("URI of blob is: " + blobItem.getUri());
		}

		return azureContainer;
	}
}
