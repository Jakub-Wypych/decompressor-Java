package decompressor;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class IdentTest {
    private Ident ident;

    @BeforeEach
    void setUp() {
        System.out.println("STARITNGAS");
        ArrayList<Byte> raw_ident = new ArrayList<>();
        for(int i=0; i<8; i++)
            raw_ident.add((byte) 1);
        ident = new Ident(raw_ident);
    }

    @AfterEach
    void tearDown() {
        System.out.println("Finished!");
    }

    @Test
    void bit_read() {
        System.out.println("PEARA");
        assertEquals(7, ident.stray_bits(), "Not the same :(");
    }
}