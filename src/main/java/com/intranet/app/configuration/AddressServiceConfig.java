package com.intranet.app.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.SslProvider;

@Getter
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "client.address-service", ignoreInvalidFields = true)
public class AddressServiceConfig {
    private final String url;

    @Bean
    public WebClient getAddressServiceWebClientBean() {
        return WebClient.builder().baseUrl(url)
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create()
                                .secure(spec -> spec.sslContext(SslProvider.defaultClientProvider().getSslContext())).keepAlive(false)
                ))
                .build();
    }
}
