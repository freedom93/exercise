package com.freedom.loaderallphotos;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @Description: 文件操作类:主要是对图片Uri的处理过程
 * @author lifen
 *
 */
public class FileUtil {
	public FileUtil() {
	}
	
	/**
     * InputStream to byte
     * @param inStream
     * @return
     * @throws Exception
     */
    public byte[] readInputStream(InputStream inStream) throws Exception { 
        byte[] buffer = new byte[1024]; 
        int len = -1; 
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        
        while ((len = inStream.read(buffer)) != -1) { 
        	outStream.write(buffer, 0, len); 
        }
        
        byte[] data = outStream.toByteArray(); 
        outStream.close(); 
        inStream.close();
        
        return data; 
   } 
    
   /**
    * Byte to bitmap
    * @param bytes
    * @param opts
    * @return
    */
   public Bitmap getBitmapFromBytes(byte[] bytes, BitmapFactory.Options opts) {
	   if (bytes != null){
		   if (opts != null){ 
			   return BitmapFactory.decodeByteArray(bytes, 0, bytes.length,opts); 
		   }
           else{
        	   return BitmapFactory.decodeByteArray(bytes, 0, bytes.length); 
           }
	   }
	   
       return null; 
   } 
}