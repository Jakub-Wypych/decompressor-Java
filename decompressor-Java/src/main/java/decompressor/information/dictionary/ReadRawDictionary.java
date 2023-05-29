package decompressor.information.dictionary;

import decompressor.bitwork.Bitread;
import decompressor.exceptions.FileIsDamaged;

import java.util.ArrayList;

import static java.lang.Math.pow;

/**
 * Read the raw dictionary from the file,
 * which later will be used to make a tree
 */
public class ReadRawDictionary {
    /**
     * Reads the raw dictionary from the infile
     * @param bitread used for reading bits
     * @param bit per how many bits to read
     * @return arraylist of objects used by {@link Tree Tree} to generate a tree
     * @throws FileIsDamaged end of file
     */
    public static ArrayList<Object> read(Bitread bitread, byte bit) throws FileIsDamaged {
        ArrayList<Object> rawDictionaries = new ArrayList<>();

        ArrayList<Byte> found_symbol = ReadRawDictionary.next_symbol(bitread, bit); // reading first symbol
        int previous_probability = ReadRawDictionary.convert_to_int(bitread.readNbits(4)); // reading first prob
        rawDictionaries.add(new RawDictionary(found_symbol,previous_probability)); // adding first node
        ArrayList<Byte> previous_symbol = found_symbol;

        found_symbol = ReadRawDictionary.next_symbol(bitread, bit); // reading next symbol
        while(!previous_symbol.equals(found_symbol)) { // checking if recently read symbol is the same as the previous one
            int found_probability = ReadRawDictionary.convert_to_int(bitread.readNbits(4)); // reading the prob of the recently read symbol

            if(found_probability > 9) { // it has the same prob as the previous symbol
                rawDictionaries.add(new RawDictionary(found_symbol, previous_probability)); // adding recently read symbol with the previous prob
                for(int i =0; i<found_probability-10;i++) { // there are more symbols with the same prob
                    found_symbol = ReadRawDictionary.next_symbol(bitread, bit); // reading their symbol
                    rawDictionaries.add(new RawDictionary(found_symbol, previous_probability)); // adding them with the "previous" prob
                }
            }
            else { // the recently read symbol doesn't have the same prob as the previous one
                rawDictionaries.add(new RawDictionary(found_symbol, found_probability)); // adding recently read symbol with its different prob
                previous_probability = found_probability; // now the previous prob is the recent prob
            }
            previous_symbol = found_symbol;
            found_symbol = ReadRawDictionary.next_symbol(bitread, bit); // reading next (recently read) symbol
        }
        return rawDictionaries;
    }

    /**
     * @param bitread used forreading bits
     * @param bit how many bits to read
     * @return read symbol from file
     * @throws FileIsDamaged end of file
     */
    private static ArrayList<Byte> next_symbol(Bitread bitread, byte bit) throws FileIsDamaged {
        ArrayList<Byte> found_symbol = bitread.readNbits(bit); // reading symbol
        if(found_symbol.size() != bit) // checking if end of file
            throw new FileIsDamaged("ERROR: Compressed file is damaged!"); // if so stop
        return found_symbol;
    }


    /**
     * Converts 4 bits into int
     * @param byte_form the 4 bits
     * @return values of 4 bits
     * @throws FileIsDamaged end of file
     */
    private static int convert_to_int(ArrayList<Byte> byte_form) throws FileIsDamaged {
        if(byte_form.size() != 4) // checking if end of file
            throw new FileIsDamaged("ERROR: Compressed file is damaged!"); // if so stop
        int int_form = 0;
        for (int i = 3; i >= 0; i--) {
            if (byte_form.get(3-i) == 1)
                int_form += pow(2, i);
        }
        return int_form;
    }
}
