package com.baoke.common.util.api;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public abstract class GZip {

	
    public static final int BUFFER = 1024;
    public static final String EXT = ".gz";

    private static String encode = "utf-8";// "ISO-8859-1"
    
    /*
     * 字符串压缩为字节数组
     */
    public static byte[] compressToByte(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes(encode));
            gzip.close();
        } catch (IOException e) {
//        	log.error("compressToByte error ",e)  ;
        }
        return out.toByteArray();
    }

    /*
     * 字符串压缩为字节数组
     */
    public static byte[] compressToByte(String str, String encoding) {
        if (str == null || str.length() == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes(encoding));
            gzip.close();
        } catch (IOException e) {
//        	log.error("compressToByte error ",e)  ;
        }
        return out.toByteArray();
    }

    /*
     * 字节数组解压缩后返回字符串
     */
    public static String uncompressToString(byte[] b) {
        if (b == null || b.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(b);

        try {
            GZIPInputStream gunzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = gunzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
          //  gunzip.close();
            return out.toString();
        } catch (IOException e) {
//        	log.error("compressToByte error ",e)  ;
        }finally{
        	if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
//					log.error("close  ByteArrayOutputStream ",e)  ;
				}
        	}
        	if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
//					log.error("close  ByteArrayInputStream ",e)  ;
				}
        	}
        	
        	
        }
        return null;
        
    }

    /*
     * 字节数组解压缩后返回字符串
     */
    public static String uncompressToString(byte[] b, String encoding) {
    	if (b == null || b.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(b);

        try {
            GZIPInputStream gunzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = gunzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
            gunzip.close();
            return out.toString(encoding);
        } catch (IOException e) {
//        	log.error("compressToByte error ",e)  ;
        }finally{
        	if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
//					log.error("close  ByteArrayOutputStream ",e)  ;
				}
        	}
        	if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
//					log.error("close  ByteArrayInputStream ",e)  ;
				}
        	}
        	
        	
        }
        return null;
    }

}
