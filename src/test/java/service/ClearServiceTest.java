package service;

import dao.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClearServiceTest {
    private ClearService clearService;

    @BeforeEach
    void setUp() {
       clearService = new ClearService();
    }


    @Test
    @DisplayName("Clear")
    void clear() {
        assertEquals("Clear succeeded.",clearService.clear().getMessage());
    }

}