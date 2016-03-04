/*

    This file is part of the iText (R) project.
    Copyright (c) 1998-2016 iText Group NV

*/

package com.itextpdf.samples.book.part4.chapter15;


import com.itextpdf.io.image.ImageFactory;
import com.itextpdf.kernel.parser.EventData;
import com.itextpdf.kernel.parser.EventListener;
import com.itextpdf.kernel.parser.EventType;
import com.itextpdf.kernel.parser.ImageRenderInfo;
import com.itextpdf.kernel.pdf.xobject.PdfImageXObject;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;


public class Listing_15_31_MyImageRenderListener implements EventListener {

    protected String path;

    protected String extension;
    protected int i = 1;

    public Listing_15_31_MyImageRenderListener(String path) {
        this.path = path;
    }

    public void eventOccurred(EventData data, EventType type) {
        switch (type) {
            case RENDER_IMAGE:
                try {
                    String filename;
                    FileOutputStream os;
                    ImageRenderInfo renderInfo = (ImageRenderInfo) data;
                    PdfImageXObject image = renderInfo.getImage();
                    if (image == null) {
                        return;
                    }
                    byte[] imageByte = null;
                    try {
                        imageByte = image.getImageBytes(true);
                        int imageType = ImageFactory.getImage(imageByte).getOriginalType();
                        switch (imageType) {
                            case 1:
                                extension = "jpg";
                                break;
                            case 2:
                                extension = "png";
                                break;
                            case 3:
                                extension = "gif";
                                break;
                            case 4:
                                extension = "bmp";
                                break;
                            case 5:
                                extension = "tif";
                                break;
                            case 6:
                                extension = "wmf";
                                break;
                            case 8:
                                extension = "jp2";
                                break;
                            case 9:
                                extension = "jbig2";
                                break;

                            default:
                                extension = "jpg";
                        }
                    } catch (com.itextpdf.io.IOException e) {
                        System.out.println(e.getMessage());
                    }
                    if (imageByte == null) {
                        return;
                    }
                    filename = String.format(path, i++, extension);
                    os = new FileOutputStream(filename);
                    os.write(imageByte);
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;

            default:
                break;
        }
    }

    public Set<EventType> getSupportedEvents() {
        return null;
    }
}
