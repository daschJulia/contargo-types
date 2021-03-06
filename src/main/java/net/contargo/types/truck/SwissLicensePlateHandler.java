package net.contargo.types.truck;

import java.util.Arrays;
import java.util.List;


/**
 * Can handle Swiss {@link LicensePlate}s.
 *
 * <p>Examples of Swiss license plates:</p>
 *
 * <ul>
 * <li>FR 24539</li>
 * <li>SZ 65726</li>
 * <li>ZH 445789</li>
 * <li>GR 123</li>
 * </ul>
 *
 * <p>Further information: <a href="https://de.wikipedia.org/wiki/Kontrollschild_(Schweiz)">License plates of
 * Switzerland</a></p>
 *
 * @author  Aljona Murygina - murygina@synyx.de
 * @since  0.2.0
 */
class SwissLicensePlateHandler implements LicensePlateHandler {

    /**
     * The cantons of Switzerland.
     *
     * <p>For further information see the <a
     * href="https://de.wikipedia.org/wiki/Kanton_(Schweiz)#Liste_der_Schweizer_Kantone_mit_ihren_Eckdaten">list of
     * cantons</a></p>
     */
    private static final List<String> CANTONS = Arrays.asList("AG", "AR", "AI", "BL", "BS", "BE", "FR", "GE", "GL",
            "GR", "JU", "LU", "NE", "NW", "OW", "SH", "SZ", "SO", "SG", "TI", "TG", "UR", "VD", "VS", "ZG", "ZH");

    private static final int CANTON_CODE_INDEX_START = 0;
    private static final int CANTON_CODE_INDEX_END = 2;

    /**
     * Normalizes the given {@link LicensePlate} value by upper casing it and separating the canton code with a
     * whitespace from the rest of the value.
     *
     * @param  value  to get the normalized value for, never {@code null}
     *
     * @return  the normalized value, never {@code null}
     */
    @Override
    public String normalize(String value) {

        // remove whitespaces and hyphens
        String normalizedValue = LicensePlateHandler.trim(value).replaceAll("\\s", "").replaceAll("\\-", "");

        if (normalizedValue.length() > CANTON_CODE_INDEX_END) {
            normalizedValue = normalizedValue.substring(CANTON_CODE_INDEX_START, CANTON_CODE_INDEX_END) + " "
                + normalizedValue.substring(CANTON_CODE_INDEX_END, normalizedValue.length());
        }

        return normalizedValue;
    }


    /**
     * Validates the given {@link LicensePlate} value.
     *
     * <p>A Swiss license plate consists of two parts:</p>
     *
     * <ul>
     * <li>two letter code for the canton</li>
     * <li>up to six digits</li>
     * </ul>
     *
     * <p>Structure: XX 123456</p>
     *
     * <p>There are license plates that can have one or two letters after the number. These license plates are for
     * special uses, e.g. dealer license plate. Note that these special cases are not covered by this validator! Also
     * this validator does not consider license plates for official use, such as for military vehicles.</p>
     *
     * @param  value  to be validated, never {@code null}
     *
     * @return  {@code true} if the given {@link LicensePlate} is valid, else {@code false}
     */
    @Override
    public boolean validate(String value) {

        String normalizedValue = normalize(value);

        if (!normalizedValue.matches("[A-Z]{2}\\s[0-9]{1,6}")) {
            return false;
        }

        String cantonCode = normalizedValue.substring(CANTON_CODE_INDEX_START, CANTON_CODE_INDEX_END);

        return CANTONS.contains(cantonCode);
    }
}
