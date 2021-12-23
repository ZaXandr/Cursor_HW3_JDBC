package entity;

import lombok.*;

import java.lang.annotation.Target;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Worker {

    private int idWorker;
    private String firstName;
    private String secondName;
    private int age;
    private String address;
}
