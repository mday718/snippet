package com.backedrum;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.wicket.util.io.IClusterable;

import java.time.LocalDateTime;
import java.util.Calendar;

/**
 * Created by Andrii Zablodskyi (Andrii.Zablodskyi@gmail.com) on 2/10/18.
 */
@Builder
@Getter
@Setter
@ToString
public class SourceCodeSnippet implements IClusterable {

    private String title;
    private String sourceCode;
    private LocalDateTime date;
}
