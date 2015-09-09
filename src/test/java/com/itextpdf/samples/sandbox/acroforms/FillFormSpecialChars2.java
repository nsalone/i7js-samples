/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/31335715/cannot-show-special-character-in-acro-form-field
 *
 * Sometimes you need to change the font of a field.
 */
package com.itextpdf.samples.sandbox.acroforms;

import com.itextpdf.basics.Utilities;
import com.itextpdf.basics.font.PdfEncodings;
import com.itextpdf.basics.font.TrueTypeFont;
import com.itextpdf.core.font.PdfFont;
import com.itextpdf.core.font.PdfType0Font;
import com.itextpdf.core.pdf.PdfDocument;
import com.itextpdf.core.pdf.PdfReader;
import com.itextpdf.core.pdf.PdfWriter;
import com.itextpdf.core.testutils.annotations.type.SampleTest;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.samples.GenericTest;
import org.junit.Ignore;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

// TODO problems with src-file to open. Temporary change src
@Ignore
@Category(SampleTest.class)
public class FillFormSpecialChars2 extends GenericTest {
    public static final String SRC = "./src/test/resources/sandbox/acroforms/test.pdf";
    public static final String DEST = "./target/test/resources/sandbox/acroforms/fill_form_special_chars2.pdf";
    public static final String FONT = "./src/test/resources/sandbox/acroforms/FreeSans.ttf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new FillFormSpecialChars2().manipulatePdf(DEST);
    }

    @Override
    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(
                new FileInputStream(SRC)), new PdfWriter(new FileOutputStream(DEST)));
        PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);
        form.setGenerateAppearance(true);
        PdfFont font = new PdfType0Font(
                pdfDoc,
                new TrueTypeFont("Free Sans",
                        PdfEncodings.IDENTITY_H,
                        Utilities.inputStreamToArray(new FileInputStream(FONT))),
                "Identity-H");
        form.getFormFields().get("test").setFont(font);
        form.getFormFields().get("test").setValue("\u04e711111");
        form.flatFields();
        pdfDoc.close();
    }
}
