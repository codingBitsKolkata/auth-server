/**
 * @formatter:off
 *
 */
package com.orastays.authserver.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
@RequestMapping("/download")
public class DownloadController {
	
	@RequestMapping("/{fileName:.+}")
    public void downloadPDFResource( HttpServletRequest request, HttpServletResponse response, @PathVariable("fileName") String fileName) {
		
		try{
         
		// get absolute path of the application
        ServletContext context = request.getServletContext();
        //String appPath = context.getRealPath("");
        //System.out.println("appPath = " + appPath);
			
        String workingDir = System.getProperty("user.dir");
 	    //System.out.println("Current working directory : " + workingDir);
 	    
        // construct the complete absolute path of the file
        String fullPath = workingDir + File.separator + "logs" + File.separator + fileName; 
        //System.out.println("fullPath = " + fullPath);
        //Authorized user will download the file
        File downloadFile = new File(fullPath);
        FileInputStream inputStream = new FileInputStream(downloadFile);
	         
	        // get MIME type of the file
	        String mimeType = context.getMimeType(fullPath);
	        if (mimeType == null) {
	            // set to binary type if MIME mapping not found
	            mimeType = "application/octet-stream";
	        }
	        //System.out.println("MIME type: " + mimeType);
	 
	        // set content attributes for the response
	        response.setContentType(mimeType);
	        response.setContentLength((int) downloadFile.length());
	 
	        // set headers for the response
	        String headerKey = "Content-Disposition";
	        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
	        response.setHeader(headerKey, headerValue);
	 
	        // get output stream of the response
	        OutputStream outStream = response.getOutputStream();
	 
	        byte[] buffer = new byte[(int) downloadFile.length()];
	        int bytesRead = -1;
	 
	        // write bytes read from the input stream into the output stream
	        while ((bytesRead = inputStream.read(buffer)) != -1) {
	            outStream.write(buffer, 0, bytesRead);
	        }
	        
	        inputStream.close();
	        outStream.flush();
	        outStream.close();
	        
		} catch(Exception e) {
			
			e.printStackTrace();
		}
    }
}
