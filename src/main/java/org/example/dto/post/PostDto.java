package org.example.dto.post;

import lombok.*;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PostDto {
    private int id;
    private String title;
    private String content;
}
