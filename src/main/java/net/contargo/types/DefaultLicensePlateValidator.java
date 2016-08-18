package net.contargo.types;

/**
 * Default implementation for validation of {@link LicensePlate}s.
 *
 * @author  Aljona Murygina - murygina@synyx.de
 */
class DefaultLicensePlateValidator implements LicensePlateValidator {

    /**
     * Validates the given {@link LicensePlate}.
     *
     * <p>If the given {@link LicensePlate} contains no special characters except '-', it is considered valid.</p>
     *
     * @param  licensePlate  to be validated, never {@code null}
     *
     * @return  {@code true} if the given {@link LicensePlate} is valid, else {@code false}
     */
    @Override
    public boolean isValid(LicensePlate licensePlate) {

        String value = licensePlate.getValue();
        String normalizedValue = value.toUpperCase().replaceAll("\\s", "");

        // allowed: any letter or digit, but no special characters except '-'
        return normalizedValue.matches("[\\p{L}0-9\\-]*");
    }
}