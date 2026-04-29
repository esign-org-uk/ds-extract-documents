package uk.org.esig.docusign.model;

import com.docusign.esign.model.Document;
import com.docusign.esign.model.EnvelopeTemplate;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import org.apache.commons.io.FileUtils;

public class FileExtractor {
    public static final String PDF_EXTENSION = ".pdf";
    public static void extract(File file) {
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            String json = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            EnvelopeTemplate template = mapper.readValue(json, EnvelopeTemplate.class);
            if (template != null) {
                List<Document> documents = template.getDocuments();
                for (Document document: documents) {
                    String documentBase64 = document.getDocumentBase64();
                    byte[] decoded = decoder.decode(documentBase64);
                    File output = new File(getName(document));
                    FileUtils.writeByteArrayToFile(output, decoded);
                }
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static String getName(Document document) {
        String name = "No-Name";
        if (document != null) {
            name = document.getName();
            if (name != null) {
                if (!name.toLowerCase().endsWith(PDF_EXTENSION)) {
                    name = name + PDF_EXTENSION;
                }
            }
            else {
                name = document.getDocumentId() + PDF_EXTENSION;
            }
        }
        return name;
    }
}