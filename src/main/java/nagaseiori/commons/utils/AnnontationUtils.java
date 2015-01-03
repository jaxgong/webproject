package nagaseiori.commons.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.springframework.web.method.HandlerMethod;

public class AnnontationUtils {

	public static <T extends Annotation> T getFromMethed(Class<T> annotationClazz, Object o,
			String methodName) {
		if (o == null) {
			return null;
		}
		Class<? extends Object> objectClass = o.getClass();
		try {
			Method method = objectClass.getDeclaredMethod(methodName);
			return method.getAnnotation(annotationClazz);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	public static <T extends Annotation> T getFromMethedOrType(Class<T> annotationClazz, Object o,
			String methodName) {
		if (o == null) {
			return null;
		}
		Class<? extends Object> objectClass = o.getClass();
		T annotation;
		try {
			Method method = objectClass.getDeclaredMethod(methodName);
			annotation = method.getAnnotation(annotationClazz);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
		if (annotation != null) {
			return annotation;
		}
		return objectClass.getAnnotation(annotationClazz);
	}
	
	public static <T extends Annotation> T getFromMethedOrType(Class<T> annotationClazz, HandlerMethod method) {
		Object controller = method.getBean();
		if (controller == null) {
			return null;
		}
		Class<? extends Object> objectClass = controller.getClass();
		T annotation;
		try {
			annotation = method.getMethod().getAnnotation(annotationClazz);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		}
		if (annotation != null) {
			return annotation;
		}
		return objectClass.getAnnotation(annotationClazz);
	}
}
