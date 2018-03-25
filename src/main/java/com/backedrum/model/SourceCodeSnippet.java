package com.backedrum.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.wicket.util.io.IClusterable;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@ToString
public class SourceCodeSnippet implements IClusterable {

    private String title;
    private String sourceCode;
    private LocalDateTime date;
}
