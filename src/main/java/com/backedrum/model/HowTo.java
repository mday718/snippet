package com.backedrum.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class HowTo extends Entity {

    private String text;

    @Builder
    public HowTo(String title, LocalDateTime dateTime, String text) {
        super(title, dateTime);
        this.text = text;
    }
}
