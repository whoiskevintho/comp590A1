package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import io.InputStreamBitSource;
import io.InsufficientBitsLeftException;

public class HuffDecode {
	
	private int _probability;
	//collaborators rdarden1, sumit97
	public static void main(String[] args) throws InsufficientBitsLeftException, IOException {
		String input_file_name = "/Users/kevin_young/eclipse-workspace/comp590A1/src/data/compressed.dat";
		String output_file_name = "/Users/kevin_young/eclipse-workspace/comp590A1/src/data/huffdecodefinal.txt";
		
		FileInputStream fis = new FileInputStream(input_file_name);

		InputStreamBitSource bit_source = new InputStreamBitSource(fis);

		List<SymbolWithCodeLength> symbols_with_length = new ArrayList<SymbolWithCodeLength>();

		// Read in huffman code lengths from input file and associate them with each symbol as a 
		// SymbolWithCodeLength object and add to the list symbols_with_length.
		
		for (int i = 0; i < 256; i++) {
			int length = bit_source.next(8);
			symbols_with_length.add(new SymbolWithCodeLength(i, length));
		}

		
		// Then sort the symbols.
		
		 

		// Now construct the canonical huffman tree

		HuffmanDecodeTree huff_tree = new HuffmanDecodeTree(symbols_with_length);

		int num_symbols = bit_source.next(32);
		
		int bitcnt[] = new int[256];

		// Read in the next 32 bits from the input file  the number of symbols

		try {

			// Open up output file.
			
			FileOutputStream fos = new FileOutputStream(output_file_name);

			for (int i=0; i < num_symbols; i++) {
				int huffDecode = huff_tree.decode(bit_source);
				bitcnt[huffDecode]++;
				fos.write(huffDecode);
				// Decode next symbol using huff_tree and write out to file.
			}

			// Flush output and close files.
			
			fos.flush();
			fos.close();
			fis.close();
			System.out.println("done"	);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//question $2
		double entropy = 0;
		double compressed = 0;
		for ( int i = 0; i < 256; i++) {
			double prob = ((double) bitcnt[i] / (double) num_symbols);
			System.out.println("Prob Answer" + i + "is" + prob);
		//question #3
			if (prob > 0) {
				entropy += ((double) prob * -1 * (Math.log((double) prob)) /Math.log(2));
				compressed += ((double) prob * (double) symbols_with_length.get(i).codeLength());
			}
		}
		System.out.println("question answer");
		System.out.println(" Theo Entropy Answer" + entropy );
		System.out.println("question answer");
		System.out.println("Compressed Entropy Answer" + compressed);
		
	}
}


