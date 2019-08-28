package uk.ac.ebi.ddi.ws.configuration;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 *
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class SimpleCORSFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getHeader("Access-Control-Request-Method") != null
                || ("GET".equals(request.getMethod()))
                || ("POST".equals(request.getMethod()))
                || ("OPTION".equals(request.getMethod()))
                || ("PUT".equals(request.getMethod()))) {
            // CORS "pre-flight" request
            //response.addHeader("Access-Control-Allow-Origin","http://ves-hx-43.ebi.ac.uk:8080");
            //response.addHeader("Access-Control-Allow-Origin", "http://www.omicsdi.org");
            //response.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE,OPTIONS");
            //response.addHeader("Access-Control-Allow-Headers", "X-Requested-With,Origin,Content-Type, Accept");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With,accept,Origin,"
                    + "Access-Control-Request-Method,Access-Control-Request-Headers,x-auth-token");
            response.addHeader("cors.support.credentials", "true");
            response.addHeader("cors.exposed.headers", "Access-Control-Allow-Origin,"
                    + "Access-Control-Allow-Credentials");
            //response.addHeader("cors.preflight.maxage","10");
        }
        /*if("PUT".equals(request.getMethod()) || "OPTIONS".equals(request.getMethod())){
            response.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        }*/
        filterChain.doFilter(request, response);
    }
}
