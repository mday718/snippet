package com.backedrum.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Screenshot extends Entity {
    private byte[] image;

    @Builder
    public Screenshot(String title, LocalDateTime dateTime, byte[] image) {
            super(title, dateTime);
            this.image = image;
    }
}
