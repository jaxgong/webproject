package nagaseiori.commons.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

public class CookieUtils {
	
	public static final String VALUE_ENCODE = "UTF-8";
	public static final String DEFAULT_PATH = "/";
	public static final int DEFAULT_MAX_AGE = 60 * 60 * 24 * 365;
	public static final String DOMAIN=".laiyuehui.com";
	
	public static final void addCookie(HttpServletResponse response, String key, String value) {
		String encodedValue = "";
		try {
			encodedValue = value == null ? "" : URLEncoder.encode(value, VALUE_ENCODE);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		Cookie c = new Cookie(key, encodedValue);
		c.setPath(DEFAULT_PATH);
		c.setMaxAge(DEFAULT_MAX_AGE);
		c.setDomain(DOMAIN);
		response.addCookie(c);
	}
	
	public static final void removeCookie(HttpServletResponse response, String key) {
		Cookie c = new Cookie(key, "");
		c.setPath(DEFAULT_PATH);
		c.setMaxAge(0);
		response.addCookie(c);
	}

	public static final String getCookie(HttpServletRequest request, String key) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return null;
		}
		for (Cookie c : cookies) {
			if (StringUtils.equals(key, c.getName())) {
				try {
					return URLDecoder.decode(c.getValue(), VALUE_ENCODE);
				} catch (UnsupportedEncodingException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param response
	 * @param key
	 * @param value
	 * @param domain
	 * @param path
	 * @param maxAge
	 *            an integer specifying the maximum age of the cookie in
	 *            seconds; if negative, means the cookie is not stored; if zero,
	 *            deletes the cookie
	 */
	public static final void addCookie(final HttpServletResponse response, final String key,
			final String value, final String domain, final String path, final int maxAge) {
		String encodedValue;
		try {
			encodedValue = value == null ? "" : URLEncoder.encode(value, VALUE_ENCODE);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		Cookie c = new Cookie(key, encodedValue);
		if (domain != null) {
			c.setDomain(domain);
		}else
		{
			c.setDomain(DOMAIN);
		}
		c.setPath(path);
		if(maxAge!=0){
			c.setMaxAge(maxAge);
		}
		response.addCookie(c);
	}
	
	public static final void addCookie(final HttpServletResponse response, final String key,
			final String value, final String path, final int maxAge) {
		addCookie(response, key, value, null, path, maxAge);
	}
	
	public static final void addCookie(final HttpServletResponse response, final String key,
			final String value, final int maxAge) {
		addCookie(response, key, value, null, DEFAULT_PATH, maxAge);
	}
}
