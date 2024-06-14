package de.thg.quarkus.fotoanalyzer.api;

import de.thg.quarkus.fotoanalyzer.model.Image;
import de.thg.quarkus.fotoanalyzer.services.FotoAnalyzerService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Path("/")
public class FotoAnalyzerResource {

    private final FotoAnalyzerService service;

    public FotoAnalyzerResource(FotoAnalyzerService service) {
        this.service = service;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "FotoAnalyzer Quarkus";
    }

    @POST
    @Path("/analyze_image")
    @Consumes({ "multipart/form-data" })
    @Produces({ "application/json" })
    public Response analyzeImage(@RestForm("file") FileUpload file) throws IOException {

        try(InputStream stream = new FileInputStream(file.uploadedFile().toFile())) {
            Optional<Image> result = service.analyseImage(stream, file.fileName());
            if(result.isPresent()) {
                return Response.ok(result, MediaType.APPLICATION_JSON_TYPE).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }
    }

    @POST
    @Path("/rename_images")
    @Consumes( MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> renameImages(@RestForm("files") List<FileUpload> files) throws IOException {
        Map<String, String> result = new HashMap<>();
        for (FileUpload file : files) {
            try(InputStream inputStream = new FileInputStream(file.uploadedFile().toFile())) {
                Optional<Map<String, String>> mapEntry = service.createNameByCreationDate(inputStream, file.fileName());
                mapEntry.ifPresent(result::putAll);
            }
        }
        return result;
    }

}
