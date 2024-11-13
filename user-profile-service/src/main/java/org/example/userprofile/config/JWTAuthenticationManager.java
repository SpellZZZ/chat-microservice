package org.example.userprofile.config;

import lombok.RequiredArgsConstructor;
import org.example.userprofile.dto.request.ValidRequest;
import org.example.userprofile.repository.UserRepo;
import org.example.userprofile.util.JwtUtil;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtUtil jwtUtil;
    private final UserRepo userRepo;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getCredentials().toString();
        String username = jwtUtil.extractUsername(token);

        var v =  userRepo.findUserProfileByUsername(username)
                .map(userDetails -> {
                    ValidRequest validRequest = ValidRequest.builder()
                            .username(userDetails.getUsername())
                            .token(token)
                            .build();

                    if (jwtUtil.validateToken(validRequest)) {
                        // Tworzymy Optional z wynikiem, gdy autoryzacja przebiegła pomyślnie
                        return authentication;
                    } else {
                        throw new AuthenticationException("Invalid JWT token") {};
                    }
                });

        Mono<Optional<Authentication>> monoWithOptional = Mono.just(v);
        Mono<Authentication> monoWithoutOptional = monoWithOptional.flatMap(Mono::justOrEmpty);

        return monoWithoutOptional;
    }

    public ServerAuthenticationConverter authenticationConverter() {
        return new ServerAuthenticationConverter() {
            @Override
            public Mono<Authentication> convert(ServerWebExchange exchange) {
                String token = exchange.getRequest().getHeaders().getFirst("Authorization");
                if (token != null && token.startsWith("Bearer ")) {
                    token = token.substring(7);
                    return Mono.just(SecurityContextHolder.getContext().getAuthentication());
                }
                return Mono.empty();
            }
        };
    }
}
