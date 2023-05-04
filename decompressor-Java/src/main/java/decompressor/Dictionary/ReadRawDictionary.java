package decompressor.Dictionary;

import decompressor.Bitwork.Bitread;

import java.util.ArrayList;

/* Read the raw dictionary from the file,
which later will be used to make a tree
*/
public class ReadRawDictionary {
    public static ArrayList<RawDictionary> read(Bitread bitread, byte bit) {
        ArrayList<RawDictionary> rawDictionaries = new ArrayList<>();

        ArrayList<Byte> found_symbol = ReadRawDictionary.next_symbol(bitread, bit); // reading first symbol
        int previous_probability = ReadRawDictionary.convert_to_int(bitread.readNbits(4)); // reading first prob
        rawDictionaries.add(new RawDictionary(found_symbol,previous_probability)); // adding first node

        found_symbol = ReadRawDictionary.next_symbol(bitread, bit); // reading next symbol
        while(!rawDictionaries.get(rawDictionaries.size()-1).symbol().equals(found_symbol)) { // checking if recently read symbol is the same as the previous one
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
            found_symbol = ReadRawDictionary.next_symbol(bitread, bit); // reading next (recently read) symbol
        }
        return rawDictionaries;
    }

    private static ArrayList<Byte> next_symbol(Bitread bitread, byte bit) {
        ArrayList<Byte> found_symbol = bitread.readNbits(bit); // reading symbol
        if(found_symbol.size() != bit) // checking if end of file
            ReadRawDictionary.filedamaged(); // if so stop
        return found_symbol;
    }

    private static int convert_to_int(ArrayList<Byte> byte_form) {
        if(byte_form.size() != 4) // checking if end of file
            ReadRawDictionary.filedamaged(); // if so stop
        int int_form = 0;
        if(byte_form.get(3) == 1)
            int_form += 1;
        if(byte_form.get(2) == 1)
            int_form += 2;
        if (byte_form.get(1) == 1)
            int_form += 4;
        if (byte_form.get(0) == 1)
            int_form += 8;
        return int_form;
    }

    private static void filedamaged() {
        throw new RuntimeException(new Exception("ERROR: Compressed file is damaged!"));
    }
}
