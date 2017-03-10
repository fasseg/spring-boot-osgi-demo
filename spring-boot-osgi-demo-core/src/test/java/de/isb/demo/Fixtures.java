package de.isb.demo;

import de.isb.demo.model.Applicant;
import de.isb.demo.model.Visa;
import de.isb.demo.model.VisaType;
import org.apache.commons.lang3.RandomStringUtils;

public class Fixtures {
    private Fixtures() {
    }

    public static Visa createRandomVisa() {
        final Applicant applicant = new Applicant();
        applicant.setFirstNames(RandomStringUtils.randomAlphabetic(16));
        applicant.setLastName(RandomStringUtils.randomAlphabetic(16));

        final Visa visa = new Visa();
        visa.setType(VisaType.CVISUM);
        visa.setApplicant(applicant);

        return visa;
    }
}
