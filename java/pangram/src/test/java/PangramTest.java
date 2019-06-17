import org.junit.Test;
import sun.text.normalizer.UTF16;

import java.text.Normalizer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class PangramTest {

    @Test
    public void emptySentence() {
        assertFalse(Pangrams.isPangram(""));
    }

    @Test
    public void testLowercasePangram() {
        assertTrue(Pangrams.isPangram("the quick brown fox jumps over the lazy dog"));
    }

    @Test
    public void missingCharacterX() {
        assertFalse(Pangrams.isPangram("a quick movement of the enemy will jeopardize five gunboats"));
    }

    @Test
    public void mixedCaseAndPunctuation() {
        assertTrue(Pangrams.isPangram("\"Five quacking Zephyrs jolt my wax bed.\""));
    }

    @Test
    public void nonAsciiCharacters() {
        String b_z = "bcdefghijklmnopqrstuvwxyz";

        assertTrue(Pangrams.isPangram(new String("a\u0308") + b_z));
        assertTrue(Pangrams.isPangram(new String("ä") + b_z));
    }

}
