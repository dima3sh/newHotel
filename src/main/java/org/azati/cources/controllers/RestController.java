package org.azati.cources.controllers;

import com.sun.star.beans.PropertyValue;
import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.frame.XComponentLoader;
import com.sun.star.frame.XDesktop;
import com.sun.star.frame.XStorable;
import com.sun.star.lang.XComponent;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.style.XStyleFamiliesSupplier;
import com.sun.star.text.XText;
import com.sun.star.text.XTextCursor;
import com.sun.star.text.XTextDocument;
import com.sun.star.uno.Exception;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;
import ooo.connector.BootstrapSocketConnector;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public byte[] addRoom(@RequestParam MultipartFile file) throws Exception, BootstrapException, IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        String oooExeFolder = "C:\\Program Files\\LibreOffice\\program";
        XComponentContext xContext = BootstrapSocketConnector.bootstrap(oooExeFolder);
        XMultiComponentFactory xMCF = xContext.getServiceManager();

        Object oDesktop = xMCF.createInstanceWithContext(
                "com.sun.star.frame.Desktop", xContext);

        XDesktop xDesktop = (XDesktop) UnoRuntime.queryInterface(
                XDesktop.class, oDesktop);
        XComponentLoader xCompLoader = (XComponentLoader) UnoRuntime
                .queryInterface(com.sun.star.frame.XComponentLoader.class, xDesktop);

        PropertyValue[] propertyValues = new PropertyValue[0];

        propertyValues = new PropertyValue[1];
        propertyValues[0] = new PropertyValue();
        propertyValues[0].Name = "Hidden";
        propertyValues[0].Value = Boolean.TRUE;

        XComponent xComp = xCompLoader.loadComponentFromURL("File:///C:/Users/Shisha_DS_20/Downloads/text.txt", "_blank", 0, propertyValues);

        XTextDocument xTextDocument = (XTextDocument) UnoRuntime
                .queryInterface(XTextDocument.class, xComp);

        XText m_xText = xTextDocument.getText();

        //-------for txt files-----------
//        File f = new File("C:\\Users\\Shisha_DS_20\\Downloads\\text1.txt");
//        f.createNewFile();
//        XComponent xComp = xCompLoader.loadComponentFromURL("File:///C:/Users/Shisha_DS_20/Downloads/text1.txt", "_blank", 0, propertyValues);
//
//
//        XTextDocument xTextDocument = (XTextDocument) UnoRuntime
//                .queryInterface(XTextDocument.class, xComp);
//
//        XText m_xText = xTextDocument.getText();
//
//        com.sun.star.text.XTextRange xTextRange = m_xText.createTextCursor();
//        com.sun.star.beans.XPropertySet xPropertySet =
//                (com.sun.star.beans.XPropertySet)UnoRuntime.queryInterface(
//                        com.sun.star.beans.XPropertySet.class, xTextRange);
//
//        xPropertySet.setPropertyValue("CharHeight", 8);
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(file.getBytes()), StandardCharsets.UTF_8));
//                String strLine;
//                while ((strLine = br.readLine()) != null) {
//                    m_xText.insertString(xTextRange, strLine + "\n", false);
//                }

        XStorable xStorable = (XStorable) UnoRuntime
                .queryInterface(XStorable.class, xComp);

        propertyValues = new PropertyValue[2];
        propertyValues[0] = new PropertyValue();
        propertyValues[0].Name = "Overwrite";
        propertyValues[0].Value = Boolean.TRUE;
        propertyValues[1] = new PropertyValue();
        propertyValues[1].Name = "FilterName";
        propertyValues[1].Value = "writer_pdf_Export";

// Appending the favoured extension to the origin document name
        String myResult = "letterOutput123.pdf";
        xStorable.storeToURL("file:///C:/Users/Shisha_DS_20/Downloads/" + myResult, propertyValues);

        System.out.println("Saved " + myResult);
        xDesktop.terminate();
//        //spire.doc
//        Document document = new Document();
//        document.loadFromStream(new ByteArrayInputStream(file.getBytes()), FileFormat.Docx);
////save the document to a PDF file.
//        document.saveToStream(out, FileFormat.PDF);


        //PSPDFKit.initializeTrial();


        //Aspose
//        Document doc = new Document(new ByteArrayInputStream(file.getBytes()));
//
//// Save the document in PDF format.
//        PdfSaveOptions pso = new PdfSaveOptions();
//        pso.setCompliance(PdfCompliance.PDF_17);
//
//        doc.save(out, pso);

        //xDocReport
//        try {
//            long start = System.currentTimeMillis();
//
//            // 1) Load DOCX into XWPFDocument
//            InputStream is = new ByteArrayInputStream(file.getBytes());
//            XWPFDocument document = new XWPFDocument(is);
//
//            PdfOptions options = PdfOptions.create();
//            PdfConverter.getInstance().convert(document, out, options);
//
//            System.err.println("Generate pdf/HelloWorld.pdf with "
//                    + (System.currentTimeMillis() - start) + "ms");
//
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }


        //docx4j
//        try {
//            long start = System.currentTimeMillis();
//
//            // 1) Load DOCX into WordprocessingMLPackage
//            InputStream is = new ByteArrayInputStream(file.getBytes());
//            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(is);
//
//            Docx4J.toPDF(wordMLPackage, out);
//            out.flush();
//            out.close();
//
//            System.err.println("Generate pdf/HelloWorld.pdf with "
//                    + (System.currentTimeMillis() - start) + "ms");
//
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }


        //apache poi
//        XWPFDocument doc = new XWPFDocument( new ByteArrayInputStream(file.getBytes()));// docx
//        PdfOptions options = PdfOptions.create();
//        PdfConverter.getInstance().convert(doc, out, options);// pdf

        // pdfBox
//        try (PDDocument doc = new PDDocument()) {
//
//            PDPage myPage = new PDPage();
//            doc.addPage(myPage);
//            try (PDPageContentStream cont = new PDPageContentStream(doc, myPage)) {
//
//                cont.beginText();
//                PDFont font = PDType0Font.load(doc, new File("C:\\Users\\Shisha_DS_20\\Downloads\\ubuntumono (1)\\UbuntuMono-R.ttf"));
//                cont.setFont(font, 12);
//                cont.setLeading(9);
//                cont.newLineAtOffset(25, 700);
//                BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(file.getBytes()), StandardCharsets.UTF_8));
//                String strLine;
//                while ((strLine = br.readLine()) != null) {
//                    cont.showText(strLine);
//                    cont.newLine();
//                }
//                cont.newLine();
//                cont.endText();
//            }
//
//            doc.save(out);
//        }

//        Document wpd = new Document();
//        DocumentBuilder builder = new DocumentBuilder(wpd);
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        builder.getFont().setName("Courier new");
//        builder.getFont().setSize(9);
//        builder.getParagraphFormat().setLineSpacing(10);
//        BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(file.getBytes())));
//        String strLine;
//        while ((strLine = br.readLine()) != null) {
//            builder.write(strLine + "\n");
//        }
//// Convert DOC to DOCX
//        wpd.save(out, SaveFormat.PDF);
        return Base64.getEncoder().encode(out.toByteArray());

    }
}
