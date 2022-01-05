package org.azati.cources.controllers;


import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.FontSettings;
import com.aspose.words.NodeType;
import com.aspose.words.Paragraph;
import com.aspose.words.SaveFormat;
import com.aspose.words.Style;
import com.aspose.words.StyleIdentifier;
import com.aspose.words.StyleType;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.awt.*;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public byte[] addRoom(@RequestParam MultipartFile file) throws Exception {
        Document wpd = new Document();
        DocumentBuilder builder = new DocumentBuilder(wpd);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        builder.getFont().setName("Courier new");
        builder.getFont().setSize(8);
        BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(file.getBytes())));
        String strLine;
        while ((strLine = br.readLine()) != null) {
            builder.write(strLine + "\n");
        }
// Convert DOC to DOCX
        wpd.save(out, SaveFormat.PDF);
        return Base64.getEncoder().encode(out.toByteArray());
    }
}
