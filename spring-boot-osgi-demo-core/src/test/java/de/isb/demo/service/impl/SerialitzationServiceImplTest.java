package de.isb.demo.service.impl;

import de.isb.demo.Fixtures;
import de.isb.demo.model.Visa;
import de.isb.demo.service.api.SerializationService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SerialitzationServiceImplTest {

    private SerializationService serializationService = new SerializationServiceImpl();

    @Test
    public void testSerialization() {
        final Visa visa = Fixtures.createRandomVisa();
        final String json = serializationService.toJson(visa);
        final Visa deserialized = serializationService.fromJson(json, Visa.class);

        assertNotNull(deserialized.getType());
        assertNotNull(deserialized.getApplicant());
        assertTrue(deserialized.getApplicant().getFirstNames().length() > 0);
        assertTrue(deserialized.getApplicant().getLastName().length() > 0);
        assertEquals(visa.getApplicant().getFirstNames(), deserialized.getApplicant().getFirstNames());
        assertEquals(visa.getApplicant().getLastName(), deserialized.getApplicant().getLastName());

        System.out.println(serializationService.toJson(visa));
    }
}
