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
public class SourceCodeSnippet extends BaseEntity {
    @Column
    private String sourceCode;

    @Builder
    public SourceCodeSnippet(String title, LocalDateTime dateTime, String sourceCode) {
        super(title, dateTime);
        this.sourceCode = sourceCode;
    }
}
