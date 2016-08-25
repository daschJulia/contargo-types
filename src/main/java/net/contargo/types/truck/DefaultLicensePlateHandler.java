package net.contargo.types.truck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Default implementation for {@link LicensePlateHandler}s.
 *
 * @author  Aljona Murygina - murygina@synyx.de
 * @since  0.2.0
 */
class DefaultLicensePlateHandler implements LicensePlateHandler {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultLicensePlateHandler.class);

    /**
     * Normalizes the given {@link LicensePlate} value by upper casing it and replacing all whitespaces by hyphens.
     *
     * @param  value  to get the normalized value for, never {@code null}
     *
     * @return  the normalized value, never {@code null}
     */
    @Override
    public String normalize(String value) {

        String normalizedValue = value.replaceAll("\\s+", "-").replaceAll("\\-+", "-").toUpperCase();

        LOG.debug("Normalized '{}' to '{}'", value, normalizedValue);

        return normalizedValue;
    }


    /**
     * Validates the given {@link LicensePlate} value.
     *
     * <p>The given {@link LicensePlate} value is considered valid if it contains no special characters except hyphens
     * or whitespaces.</p>
     *
     * @param  value  to be validated, never {@code null}
     *
     * @return  {@code true} if the given {@link LicensePlate} is valid, else {@code false}
     */
    @Override
    public boolean validate(String value) {

        // allowed: any letter or digit, but no special characters except '-' and ' '
        return normalize(value).matches("[\\p{L}0-9\\- ]*");
    }
}