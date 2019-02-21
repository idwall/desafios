package idwall.desafio.string;

import java.util.List;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public abstract class StringFormatter {

	/**
     * It receives a text and should return it formatted
     *
     * @param text
     * @return
     */
    public abstract List<String> format(String text, int limit);
}
