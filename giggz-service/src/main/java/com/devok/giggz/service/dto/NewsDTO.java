package com.devok.giggz.service.dto;

import java.util.List;

import com.devok.giggz.service.dto.standup.StandupDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NewsDTO {
    List<StandupDTO> standups;
    List<NewsContentDTO> youtube;
    List<NewsContentDTO> podcasts;
}
