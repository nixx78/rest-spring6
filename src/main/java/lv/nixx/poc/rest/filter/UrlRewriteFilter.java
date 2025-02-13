package lv.nixx.poc.rest.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UrlRewriteFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(UrlRewriteFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        if (uri.contains("/v1")) {
            String newUri = uri.replace("v1", "v2");
            ((HttpServletResponse) response).sendRedirect(newUri);

            log.info("Redirect from [{}] to [{}]", uri, newUri);

            return;
        }
        chain.doFilter(request, response);
    }

}
