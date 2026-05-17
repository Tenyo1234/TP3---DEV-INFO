/**
 * Author : Thomas Labbe
 * Ordre de conception : 4e
 */

package tests;

import logique.ResultatExperience;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResultatExperienceTest {

    @Test
    void siGetEtSetExistFonctionnent(){

        ResultatExperience r1 = new ResultatExperience();

        r1.setExiste(true);

        assertTrue(r1.getExiste());
    }

    @Test
    void siGetEtSetSuccessFonctionnent(){

        ResultatExperience r1 = new ResultatExperience();

        r1.setSuccess(true);

        assertTrue(r1.getSuccess());
    }
}