package umc.nteam.auth;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.server.ResponseStatusException;
import umc.nteam.domain.User;
import umc.nteam.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class AuthUserResolver implements HandlerMethodArgumentResolver {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasAnnotation = parameter.hasParameterAnnotation(AuthUser.class);
        boolean isMemberType = User.class.isAssignableFrom(parameter.getParameterType());

        return hasAnnotation && isMemberType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String bearer = webRequest.getHeader("Authorization");
        String token = bearer.substring(7);
        Claims claims = jwtProvider.parseJwtToken(token);
        Long userId = Integer.toUnsignedLong((Integer) claims.get("userId"));

        return userRepository.findById(userId).orElseThrow(
                () -> new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "잘못된 토큰입니다."
                )
        );
    }
}