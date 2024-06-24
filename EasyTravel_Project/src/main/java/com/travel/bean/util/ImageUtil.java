package com.travel.bean.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageUtil {


	private static final int MAX_IMAGE_SIZE = 1024; 

    public static byte[] compressImage(byte[] imageData) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
        BufferedImage image = ImageIO.read(bis);
        
        // Compress the image based on your requirements
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", bos); // You can change "jpg" to other formats as needed
        
        return bos.toByteArray();
    }
    
    public static byte[] decompressImage(byte[] compressedImageData) throws IOException {
        if (compressedImageData == null || compressedImageData.length == 0) {
            throw new IllegalArgumentException("Input data is empty or null");
        }
        // Read the compressed image data
        ByteArrayInputStream bis = new ByteArrayInputStream(compressedImageData);
        BufferedImage image = null;
        try {
            image = ImageIO.read(bis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Failed to read image from input data", e);
        } finally {
            bis.close(); 
        }
        if (image == null) {
            throw new IOException("Failed to read image from input data");
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", bos);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Failed to write image data to output", e);
        } finally {
            bos.close();
        }
        return bos.toByteArray();
    }

}
