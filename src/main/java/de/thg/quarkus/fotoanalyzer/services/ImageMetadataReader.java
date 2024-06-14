package de.thg.quarkus.fotoanalyzer.services;

import de.thg.quarkus.fotoanalyzer.model.Image;

import java.io.InputStream;

public interface ImageMetadataReader {

    Image readImageMetadata(InputStream inputStream, String originalName);

}
