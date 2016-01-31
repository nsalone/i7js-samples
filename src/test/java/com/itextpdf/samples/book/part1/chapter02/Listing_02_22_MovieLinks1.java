/*

    This file is part of the iText (R) project.
    Copyright (c) 1998-2016 iText Group NV

*/

package com.itextpdf.samples.book.part1.chapter02;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Property;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Link;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.samples.GenericTest;
import com.itextpdf.test.annotations.type.SampleTest;
import com.lowagie.database.DatabaseConnection;
import com.lowagie.database.HsqldbConnection;
import com.lowagie.filmfestival.Movie;
import com.lowagie.filmfestival.PojoFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.experimental.categories.Category;

@Category(SampleTest.class)
public class Listing_02_22_MovieLinks1 extends GenericTest {
    public static final String DEST = "./target/test/resources/book/part1/chapter02/Listing_02_22_MovieLinks1.pdf";

    protected PdfFont bold;

    public static void main(String args[]) throws IOException, SQLException {
        new Listing_02_22_MovieLinks1().manipulatePdf(DEST);
    }

    public void manipulatePdf(String destination) throws IOException, SQLException {
        //Initialize writer
        FileOutputStream fos = new FileOutputStream(destination);
        PdfWriter writer = new PdfWriter(fos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document doc = new Document(pdfDoc);

        bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);

        // Make the connection to the database
        DatabaseConnection connection = new HsqldbConnection("filmfestival");
        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery(
                "SELECT DISTINCT mc.country_id, c.country, count(*) AS c "
                        + "FROM film_country c, film_movie_country mc "
                        + "WHERE c.id = mc.country_id "
                        + "GROUP BY mc.country_id, country ORDER BY c DESC");
        Link imdb;
        // loop over the countries
        while (rs.next()) {
            Paragraph anchor = new Paragraph(rs.getString("country"));
            anchor.setFont(bold);
            // TODO Revise Table 3.33
            anchor.setProperty(Property.DESTINATION, rs.getString("country_id"));

            doc.add(anchor);
            // loop over the movies
            for(Movie movie : PojoFactory.getMovies(connection, rs.getString("country_id"))) {
                // the movie title will be an external link
                imdb = new Link(movie.getMovieTitle(),
                        PdfAction.createURI(String.format("http://www.imdb.com/title/tt%s/", movie.getImdb())));
                doc.add(new Paragraph().add(imdb));
                doc.add(new Paragraph("\n"));
            }
            doc.add(new AreaBreak());
        }
        // Create an internal link to the first page
        Link toUS = new Link("Go back to the first page.", PdfAction.createGoTo("US"));
        doc.add(new Paragraph(toUS));
        doc.close();
        stm.close();
        connection.close();
    }
}
