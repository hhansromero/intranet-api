package com.intranet.app.models;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private Long id;
    private String code;
    private String name;
    private String dni;
    private AddressResponse address;
}
