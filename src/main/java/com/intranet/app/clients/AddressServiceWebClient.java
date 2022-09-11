package com.intranet.app.clients;

import com.intranet.app.models.AddressResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class AddressServiceWebClient {
    private final WebClient addressServiceWebClient;

    @Autowired
    public AddressServiceWebClient(WebClient addressServiceWebClient) {
        this.addressServiceWebClient = addressServiceWebClient;
    }

    public Mono<AddressResponse> fetchAddress(String dni) {
        return addressServiceWebClient.get()
                .uri("/api/addresses/by-dni/{dni}", dni)
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals,
                        response -> Mono.error(ChangeSetPersister.NotFoundException::new))
                .bodyToMono(AddressResponse.class);
    }
}
