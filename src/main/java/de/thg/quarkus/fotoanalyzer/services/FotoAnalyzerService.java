package de.thg.quarkus.fotoanalyzer.services;

import de.thg.quarkus.fotoanalyzer.model.Image;
import de.thg.quarkus.fotoanalyzer.util.LocalDateTimeConverter;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@RequestScoped
public class FotoAnalyzerService {

    private final ImageMetadataReader imageMetadataReader;
    private final LocalDateTimeConverter localDateTimeConverter;

    @Inject
    public FotoAnalyzerService(ImageMetadataReader imageMetadataReader, LocalDateTimeConverter localDateTimeConverter) {
        this.imageMetadataReader = imageMetadataReader;
        this.localDateTimeConverter = localDateTimeConverter;
    }

    public Optional<Image> analyseImage(InputStream inputStream, String originalName) {
        return Optional.ofNullable(imageMetadataReader.readImageMetadata(inputStream, originalName));
    }

    public Optional<Map<String, String>> createNameByCreationDate(InputStream inputStream, String originalName) {
        Optional<Image> image = analyseImage(inputStream, originalName);
        return image.map(value -> Map.of(value.getFilename(), localDateTimeConverter.toFilename(value.getCreationDate())));
    }

}
