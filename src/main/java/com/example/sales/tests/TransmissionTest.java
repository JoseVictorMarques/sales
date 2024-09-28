package com.example.sales.tests;

import com.example.sales.business.TransmissionBusiness;
import com.example.sales.model.entities.Transmission;
import com.example.sales.model.enums.TransmissionEnum;
import com.example.sales.repository.TransmissionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransmissionTest {

    @InjectMocks
    private TransmissionBusiness transmissionBusiness;

    @Mock
    private TransmissionRepository transmissionRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTransmission() {
        Transmission transmission = new Transmission(1L);
        transmission.setGears(6);
        transmission.setType(TransmissionEnum.MANUAL);

        when(transmissionRepository.save(any(Transmission.class))).thenReturn(transmission);

        Transmission result = transmissionBusiness.createTransmission(transmission);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(6, result.getGears());
        assertEquals(TransmissionEnum.MANUAL, result.getType());
    }

    @Test
    public void testListTransmission() {
        Transmission transmission1 = new Transmission(1L);
        transmission1.setGears(6);
        transmission1.setType(TransmissionEnum.MANUAL);

        Transmission transmission2 = new Transmission(2L);
        transmission2.setGears(8);
        transmission2.setType(TransmissionEnum.AUTOMATIC);

        List<Transmission> transmissions = Arrays.asList(transmission1, transmission2);

        when(transmissionRepository.findAll()).thenReturn(transmissions);

        List<Transmission> result = transmissionBusiness.listTransmission();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(transmission1, result.get(0));
        assertEquals(transmission2, result.get(1));
    }
}
