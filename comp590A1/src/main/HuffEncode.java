package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import io.OutputStreamBitSink;

public class HuffEncode {

	public static void main(String[] args) throws IOException {
		String input_file_name =  "/Users/kevin_young/eclipse-workspace/comp590A1/src/data/huffdecodefinal.txt";
		String output_file_name =  "/Users/kevin_young/eclipse-workspace/comp590A1/src/data/huffdecodefinalcompressed.txt";

		FileInputStream fis = new FileInputStream(input_file_name);

		int[] bitcnt = new int[256];
		int num_symbols = 0;
		
		// Read in each symbol (i.e. byte) of input file and 
		// update appropriate count value in bitcnt
		// Should end up with total number of symbols 
		// (i.e., length of file) as num_symbols
		
		int next = fis.read();
		while (next != -1) {
				bitcnt[next]++;
				num_symbols++;
				next = fis.read();
		}

		// Close input file
		fis.close();

		// Create array of symbol values
		
		int[] symbols = new int[256];
		for (int i=0; i<256; i++) {
			symbols[i] = i;
		}
		
		// Create encoder using symbols and their associated counts from file.
		
		HuffmanEncoder encoder = new HuffmanEncoder(symbols, bitcnt);
		
		// Open output stream.
		FileOutputStream fos = new FileOutputStream(output_file_name);
		OutputStreamBitSink bit_sink = new OutputStreamBitSink(fos);

		// Write out code lengths for each symbol as 8 bit value to output file.
		for (int i = 0; i < 256; i++) {
			bit_sink.write(encoder.getCode(i).length(), 8);
		}
		
		// Write out total number of symbols as 32 bit value.
		bit_sink.write(num_symbols, 8);
		
		// Reopen input file.
		fis = new FileInputStream(input_file_name);
		
		next = fis.read();
		while (next != -1) {
				encoder.encode(next, bit_sink);
				next = fis.read();
		}

		// Go through input file, read each symbol (i.e. byte),
		// look up code using encoder.getCode() and write code
        // out to output file.
		

		// Pad output to next word.
		bit_sink.padToWord();

		// Close files.
		fis.close();
		fos.close();
		System.out.println("Final file has been encoded");
		
		//question #6
				//double entropy = 0;
				double compressed = 0;
				for ( int i = 0; i < 256; i++) {
					double prob = ((double) bitcnt[i] / (double) num_symbols);
					System.out.println("Prob Answer" + i + "is" + prob);
				//question #6
					if (prob > 0) {
						//entropy += ((double) prob * -1 * (Math.log((double) prob)) /Math.log(2));
						compressed += ((double) prob * (double) encoder.getCode(i).length());
					}
				}
				//System.out.println("question answer");
				//System.out.println(" Theo Entropy Answer" + entropy );
				System.out.println("question answer");
				System.out.println("Compressed Entropy Answer" + compressed);
	}
}

