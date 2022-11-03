package TestDrivenSpringBoot.service.dto;

import lombok.NonNull;
import lombok.Value;

@Value
public class Recommendation {
    @NonNull
    String sentence;
}
