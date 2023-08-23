package com.laboratory.rack.query.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RackQueryDto {

    private String rackId;

    private int capacity;

    private List<String> samples;

}
