package kz.akvelon.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Data
@Builder
public class Data {
    private Long id;
    private String execute;
    private String status;
    private Time time;
    private String testName;
}
