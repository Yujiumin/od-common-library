package cn.fullstack.od.common.core.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static cn.fullstack.od.common.core.config.BeanConditions.ENABLE_HTTP_CORS;

@Slf4j
@Component
@ConditionalOnProperty(name = ENABLE_HTTP_CORS, havingValue = "true")
public final class CorsFilter extends HttpFilter {

    private final List<HttpMethod> ACCESS_CONTROL_ALLOW_METHODS = new LinkedList<>();
    private final List<String> ACCESS_CONTROL_ALLOW_HEADERS = new LinkedList<>();

    public CorsFilter() {
        ACCESS_CONTROL_ALLOW_METHODS.add(HttpMethod.GET);
        ACCESS_CONTROL_ALLOW_METHODS.add(HttpMethod.POST);
        ACCESS_CONTROL_ALLOW_METHODS.add(HttpMethod.PUT);
        ACCESS_CONTROL_ALLOW_METHODS.add(HttpMethod.DELETE);
        ACCESS_CONTROL_ALLOW_METHODS.add(HttpMethod.OPTIONS);

        ACCESS_CONTROL_ALLOW_HEADERS.add(HttpHeaders.AUTHORIZATION);
        ACCESS_CONTROL_ALLOW_HEADERS.add(HttpHeaders.CONTENT_TYPE);
        ACCESS_CONTROL_ALLOW_HEADERS.add(HttpHeaders.ACCESS_CONTROL_MAX_AGE);
        ACCESS_CONTROL_ALLOW_HEADERS.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN);
        ACCESS_CONTROL_ALLOW_HEADERS.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS);
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, ACCESS_CONTROL_ALLOW_METHODS.stream().map(HttpMethod::name).collect(Collectors.joining(",")));
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, String.join(",", ACCESS_CONTROL_ALLOW_HEADERS));
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        response.setHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3600");
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        chain.doFilter(request, response);
    }
}
