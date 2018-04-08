package com.backedrum.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
public class Screenshot extends BaseEntity {
    @Column
    private byte[] image;

    @Builder
    public Screenshot(String title, LocalDateTime dateTime, byte[] image) {
            super(title, dateTime);
            this.image = image;
    }
}
