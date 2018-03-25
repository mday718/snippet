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
    @Builder
    public Screenshot(String title, LocalDateTime dateTime) {
            super(title, dateTime);
    }
}
