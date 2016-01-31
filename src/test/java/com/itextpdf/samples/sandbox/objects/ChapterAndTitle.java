/*

    This file is part of the iText (R) project.
    Copyright (c) 1998-2016 iText Group NV

*/

/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/28431148/itext-chapter-font-overrides-paragraph-font
 */
package com.itextpdf.samples.sandbox.objects;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.test.annotations.type.SampleTest;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.samples.GenericTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Ignore;
import org.junit.experimental.categories.Category;

@Ignore
@Category(SampleTest.class)
public class ChapterAndTitle extends GenericTest {
    public static final String DEST = "./target/test/resources/sandbox/objects/chapter_and_title.pdf";
    public static final String[] ITEMS = {
            "Insurance system", "Agent", "Agency", "Agent Enrollment", "Agent Settings",
            "Appointment", "Continuing Education", "Hierarchy", "Recruiting", "Contract",
            "Message", "Correspondence", "Licensing", "Party"
    };

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ChapterAndTitle().manipulatePdf(DEST);
    }

    public void manipulatePdf(String dest) throws IOException {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(new FileOutputStream(dest)));
        Document doc = new Document(pdfDoc);

        PdfFont chapterFont = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
        PdfFont paragraphFont = PdfFontFactory.createFont(FontConstants.HELVETICA);
        // Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLDITALIC);
        // Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
        Paragraph chunk = new Paragraph("This is the title").setFont(chapterFont);
        // TODO There is no Chapter or Section in itext
        // Chapter chapter = new Chapter(new Paragraph(chunk), 1);
        // chapter.setNumberDepth(0);
        // chapter.add(new Paragraph("This is the paragraph", paragraphFont));
        // doc.add(chapter);

        doc.close();
    }
}
