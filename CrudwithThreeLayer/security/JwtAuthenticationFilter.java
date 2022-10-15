package com.hb.crud.CrudwithThreeLayer.security;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.hb.crud.CrudwithThreeLayer.jwtHelper.JwtUtil;
import io.jsonwebtoken.MalformedJwtException;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.persistence.EntityNotFoundException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.IOException;

@Configuration
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    CustomUserDetailService customUserDetailService;

    @Autowired
    JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //get jwt
        //check Bearer
        //validate

        String requestTokenHeader= request.getHeader("Authorization");
        String username= null;
        String jwtToken= null;

        if(requestTokenHeader !=null && requestTokenHeader.startsWith("Bearer ")){
            jwtToken = requestTokenHeader.substring(7);

//            try {
//                username = this.jwtUtil.extractUsername(jwtToken);
//            }catch (MalformedJwtException e){
//                System.out.println(e);
//            }catch (EntityNotFoundException e){
//                System.out.println(e);
//            }

            username = this.jwtUtil.extractUsername(jwtToken);

            UserDetails userDetails=this.customUserDetailService.loadUserByUsername(username);


            //

            //security   SecurityContextHolder.getContext().getAuthentication()==null

            if(username!=null && jwtUtil.validateToken(jwtToken,userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "*");
        response.addHeader("Access-Control-Allow-Headers", "*");
        filterChain.doFilter(request,response);
    }
}
