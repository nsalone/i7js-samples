/**
 * These examples are written by Bruno Lowagie in the context of an article about fonts.
 */
package com.itextpdf.samples.sandbox.fonts.tutorial;

import com.itextpdf.core.font.PdfFont;
import com.itextpdf.core.pdf.PdfDocument;
import com.itextpdf.core.pdf.PdfWriter;
import com.itextpdf.core.testutils.annotations.type.SampleTest;
import com.itextpdf.model.Document;
import com.itextpdf.model.element.Paragraph;
import com.itextpdf.samples.GenericTest;

import java.io.File;
import java.io.FileOutputStream;

import org.junit.experimental.categories.Category;

@Category(SampleTest.class)
public class F03_Embedded extends GenericTest {
    public static final String DEST = "./target/test/resources/sandbox/fonts/tutorial/f03_embedded.pdf";
    public static final String FONT = "./src/test/resources/sandbox/fonts/tutorial/FreeSans.ttf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new F03_Embedded().manipulatePdf(DEST);
    }

    @Override
    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(new FileOutputStream(DEST)));
        Document doc = new Document(pdfDoc);
        PdfFont font = PdfFont.createFont(pdfDoc, FONT, "Cp1250", true);
        doc.add(new Paragraph("Odkud jste?").setFont(font));
        doc.add(new Paragraph("Uvid\u00edme se za chvilku. M\u011bj se.").setFont(font));
        doc.add(new Paragraph("Dovolte, abych se p\u0159edstavil.").setFont(font));
        doc.add(new Paragraph("To je studentka.").setFont(font));
        doc.add(new Paragraph("V\u0161echno v po\u0159\u00e1dku?").setFont(font));
        doc.add(new Paragraph("On je in\u017een\u00fdr. Ona je l\u00e9ka\u0159.").setFont(font));
        doc.add(new Paragraph("Toto je okno.").setFont(font));
        doc.add(new Paragraph("Zopakujte to pros\u00edm.").setFont(font));
        pdfDoc.close();
    }
}