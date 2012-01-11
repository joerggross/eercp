/*
 * Copyright by Jörg Groß.
 */
package de.jgros.eercp.core.aspect;

import javax.persistence.Id;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Tests the {@link UniqueAspect}.
 * 
 * @author Jörg Groß
 * 
 */
public class UniqueAspectTest {

	/**
	 * Tests the unique implementation by simple class extension.
	 */
	@Test
	public void testUniqueByExtensionOfImplementation() throws Exception {

		UniqueEntityExtendsUnique ue = new UniqueEntityExtendsUnique();
		ue.setUniqueId("AAA");
		Assert.assertEquals("AAA", ue.getUniqueId());

		Assert.assertFalse(ue.getClass().getSuperclass()
				.getDeclaredField("uniqueId").isAnnotationPresent(Id.class));

		// test equality
		UniqueEntityExtendsUnique ue2 = new UniqueEntityExtendsUnique();
		ue2.setUniqueId("AAA");
		Assert.assertEquals(ue, ue2);

		// test not same
		ue2.setUniqueId("AAB");
		Assert.assertNotSame(ue, ue2);

	}

	/**
	 * Tests the unique implementation by annotation implementation.
	 */
	@Test
	public void testUniqueByAnnotation() throws Exception {

		UniqueEntityByAnnotation ue = new UniqueEntityByAnnotation();
		ue.setUniqueId("AAA");
		Assert.assertEquals("AAA", ue.getUniqueId());

		Assert.assertFalse(ue.getClass().getSuperclass()
				.getDeclaredField("uniqueId").isAnnotationPresent(Id.class));

		// test equality
		UniqueEntityByAnnotation ue2 = new UniqueEntityByAnnotation();
		ue2.setUniqueId("AAA");
		Assert.assertEquals(ue, ue2);

		// test not same
		ue2.setUniqueId("AAB");
		Assert.assertNotSame(ue, ue2);
	}

	/**
	 * Tests the unique aspect by annotation implementation and override of
	 * methods.
	 */
	@Test
	public void testUniqueByAnnotationOverride() throws Exception {

		UniqueEntityByAnnotationOverride ue = new UniqueEntityByAnnotationOverride();
		ue.setUniqueId("AAA");
		Assert.assertEquals("AAAA", ue.getUniqueId());

		Assert.assertTrue(ue.getClass().getSuperclass()
				.getDeclaredField("uniqueId").isAnnotationPresent(Id.class));

		// test equality
		UniqueEntityByAnnotationOverride ue2 = new UniqueEntityByAnnotationOverride();
		ue2.setUniqueId("AAA");
		Assert.assertEquals(ue, ue2);

		// test not same
		ue2.setUniqueId("AAB");
		Assert.assertEquals("AABA", ue2.getUniqueId());

		Assert.assertNotSame(ue, ue2);

		Assert.assertEquals("BBB".hashCode(), ue2.hashCode());

	}

}
