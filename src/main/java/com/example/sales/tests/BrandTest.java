package com.example.sales.tests;

import com.example.sales.business.BrandBusiness;
import com.example.sales.model.entities.Brand;
import com.example.sales.repository.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BrandTest {
    @InjectMocks
    private BrandBusiness brandBusiness;

    @Mock
    private BrandRepository brandRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateBrand() {
        Brand brand = new Brand();
        brand.setId(1L);
        brand.setName("test");

        when(brandRepository.save(any(Brand.class))).thenReturn(brand);

        Brand result = brandBusiness.createBrand(brand);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("test", result.getName());
    }

    @Test
    public void testListBrand() {
        Brand brand1 = new Brand(1L);
        brand1.setName("test1");

        Brand brand2 = new Brand(2L);
        brand2.setName("test2");

        List<Brand> brands = Arrays.asList(brand1, brand2);

        when(brandRepository.findAll()).thenReturn(brands);

        List<Brand> result = brandBusiness.findAllBrands();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(brand1, result.get(0));
        assertEquals(brand2, result.get(1));
    }

}
