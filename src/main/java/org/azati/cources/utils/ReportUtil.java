package org.azati.cources.utils;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import org.azati.cources.entity.Guest;

import java.util.*;

public class ReportUtil {

    public static void createPDFReport(Guest guest, String path) throws JRException {
        // Fetching the .jrxml file from the resources folder.
        JasperReport jasperReport = JasperCompileManager.compileReport("src/main/resources/reports/guest.jrxml");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Websparrow.org");

        List<Guest> guests = new ArrayList<>(Arrays.asList(guest));
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(guests);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);

        JRPdfExporter exporter = new JRPdfExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(
                new SimpleOutputStreamExporterOutput("employeeReport.pdf"));

        SimplePdfReportConfiguration reportConfig
                = new SimplePdfReportConfiguration();
        reportConfig.setSizePageToContent(true);
        reportConfig.setForceLineBreakPolicy(false);

        SimplePdfExporterConfiguration exportConfig
                = new SimplePdfExporterConfiguration();
        exportConfig.setMetadataAuthor("baeldung");
        exportConfig.setEncrypted(true);
        exportConfig.setAllowedPermissionsHint("PRINTING");

        exporter.setConfiguration(reportConfig);
        exporter.setConfiguration(exportConfig);

        exporter.exportReport();

    }
}
