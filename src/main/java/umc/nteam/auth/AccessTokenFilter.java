package umc.nteam.auth;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RequiredArgsConstructor
public class AccessTokenFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String bearer = request.getHeader("Authorization");
        if (bearer != null) {
            parseToken(bearer);
        }
        filterChain.doFilter(request, response);
    }

    private void parseToken(String bearer) {
        String token = bearer.substring(7);
        try {
            Claims claims = jwtProvider.parseJwtToken(token);
            Long userId = Integer.toUnsignedLong((Integer) claims.get("userId"));
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userId, ""));
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "잘못된 토큰입니다."
            );
        }
    }
}
