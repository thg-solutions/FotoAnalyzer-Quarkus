package de.thg.quarkus.fotoanalyzer.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@EqualsAndHashCode
@Getter
@Setter
@ToString
public class Image implements Comparable<Image> {

    private String id;

    private LocalDateTime creationDate;

    private Double latitude;

    private Double longitude;

    private LocalDateTime lastModified;

    private String filename;

    public Image() {

    }

    @Override
    public int compareTo(Image o) {
        return this.creationDate.compareTo(o.creationDate);
    }
}
