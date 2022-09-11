package com.intranet.app.models;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {
    private Long id;
    private String dni;
    private String street;
    private String city;
    private String postalCode;
    private String country;
}
