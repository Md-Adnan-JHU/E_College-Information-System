package com.adnan.security.jwt;

import com.adnan.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
	

    private static final String SECRET_KEY = "bithapier_token";

    private static final int TOKEN_VALIDITY = Integer.MAX_VALUE;

    @Autowired
    private UserDetailService userDetailService;
    
    public String getUsernameFromToken(String token) {
    	System.out.println("getting claim");
    	String claim = getClaimFromToken(token, Claims::getSubject);
    	System.out.println("getting");
    	
        return claim;
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    	System.out.println("getting claim  11");
        final Claims claims = getAllClaimsFromToken(token);
        System.out.println(claims.getSubject());
        System.out.println("getting claim  22");
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
    	
    	Claims claims;
    	try {
    		claims =  Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			System.out.println("jgcg");
			e.printStackTrace();
		}
    	
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String generateToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();

        Calendar cal = Calendar.getInstance();
        		cal.add(Calendar.YEAR, 1);
        
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                //.setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
                .setExpiration(cal.getTime())
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
    
    public String generateToken(User user) {

        Map<String, Object> claims = new HashMap<>();

        UserDetails userDetails = userDetailService.loadUserByUsername(user.getId()+"");
        
        return generateToken(userDetails);
        
    }
    
    
}
