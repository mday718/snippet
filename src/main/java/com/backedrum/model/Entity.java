package com.backedrum.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.wicket.util.io.IClusterable;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Entity implements IClusterable {

    private String title;
    private LocalDateTime dateTime;
}
