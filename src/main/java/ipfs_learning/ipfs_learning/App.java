package ipfs_learning.ipfs_learning;

import java.io.File;
import java.io.IOException;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		IPFS ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001");
		try {
			ipfs.refs.local();
			// NamedStreamable.FileWrapper file = new NamedStreamable.FileWrapper(new
			// File(""));
			// MerkleNode addResult = ipfs.add(file).get(0);

			// NamedStreamable.ByteArrayWrapper file = new
			// NamedStreamable.ByteArrayWrapper("hello.txt",
			// "G'day world! IPFS rocks!".getBytes());
			NamedStreamable.FileWrapper file = new NamedStreamable.FileWrapper(new File("hello.txt"));
			MerkleNode addResult = ipfs.add(file).get(0);

			Multihash filePointer = Multihash.fromBase58(addResult.hash.toBase58());
			byte[] fileContents = ipfs.cat(filePointer);
			String a = new String(fileContents, "UTF-8");
			System.out.println(a);

		} catch (IOException e) {
			e.printStackTrace();
		}

		// NamedStreamable.FileWrapper file = new NamedStreamable.FileWrapper(new
		// File("hello.txt"));
		// MerkleNode addResult = ipfs.add(file).get(0);
	}
}
