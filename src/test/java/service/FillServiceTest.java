package service;

import dao.Database;
import generator.Generator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FillServiceTest {
    private FillService fillService;
    private ArrayList<Generator.Location> locations;
    private Database database;

    @BeforeEach
    void setUp() {
        fillService = new FillService();
        database = new Database();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Fill Positive")
    void fillPos() {
    }
    @Test
    @DisplayName("Fill Negative")
    void fillNeg() {
    }

    @Test
    @DisplayName("Fill Helper Positive")
    void fillHelperPos() {
    }
    @Test
    @DisplayName("Fill Helper Negative")
    void fillHelperNeg() {
    }

    @Test
    @DisplayName("Make Parents Positive")
    void makeParentsPos() {
    }
    @Test
    @DisplayName("Make Parents Negative")
    void makeParentsNeg() {
    }

    @Test
    @DisplayName("Create Mother Positive")
    void createMotherPos() {
    }
    @Test
    @DisplayName("Create Mother Negative")
    void createMotherNeg() {
    }

    @Test
    @DisplayName("Create Father Positive")
    void createFatherPos() {
    }
    @Test
    @DisplayName("Create Father Negative")
    void createFatherNeg() {
    }

    @Test
    @DisplayName("Create Events Positive")
    void createEventsPos() {
    }
    @Test
    @DisplayName("Create Events Negative")
    void createEventsNeg() {
    }

    @Test
    @DisplayName("Is Integer Positive")
    void isIntegerPos() {
        assertTrue(fillService.isInteger("2"));
    }
    @Test
    @DisplayName("Is Integer Negative")
    void isIntegerNeg() {
        assertFalse(fillService.isInteger("hello"));
    }
}