import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			HashMap<String, Double> hm = new HashMap<String, Double>(4000, 1,
					4000);
			System.out.println("Test1 HashMap Size: " + hm.capacity
					+ ", modulus, " + hm.modulus + ", multiplier: "
					+ hm.multiplier);
			exploreData("sectiona.txt", hm);
			hm.resetStatistics();
			//System.out.println("==================================");

			hm = new HashMap<String, Double>(4000, 5, 4000);
			System.out.println("Test2 HashMap Size: " + hm.capacity
					+ ", modulus, " + hm.modulus + ", multiplier: "
					+ hm.multiplier);
			exploreData("sectiona.txt", hm);
			hm.resetStatistics();
			
			hm = new HashMap<String, Double>(4000, 10, 4000);
			System.out.println("Test2 HashMap Size: " + hm.capacity
					+ ", modulus, " + hm.modulus + ", multiplier: "
					+ hm.multiplier);
			exploreData("sectiona.txt", hm);
			hm.resetStatistics();
			//System.out.println("==================================");

			hm = new HashMap<String, Double>(4000, 1, 4271);
			System.out.println("Test3 HashMap Size: " + hm.capacity
					+ ", modulus, " + hm.modulus + ", multiplier: "
					+ hm.multiplier);
			exploreData("sectiona.txt", hm);
			hm.resetStatistics();
			//System.out.println("==================================");

			hm = new HashMap<String, Double>(4000, 5, 4271);
			System.out.println("Test4 HashMap Size: " + hm.capacity
					+ ", modulus, " + hm.modulus + ", multiplier: "
					+ hm.multiplier);
			exploreData("sectiona.txt", hm);
			hm.resetStatistics();
			
			hm = new HashMap<String, Double>(4000, 10, 4271);
			System.out.println("Test4 HashMap Size: " + hm.capacity
					+ ", modulus, " + hm.modulus + ", multiplier: "
					+ hm.multiplier);
			exploreData("sectiona.txt", hm);
			hm.resetStatistics();
			//System.out.println("==================================");

			hm = new HashMap<String, Double>(2000, 1, 4271);
			System.out.println("Test5 HashMap Size: " + hm.capacity
					+ ", modulus, " + hm.modulus + ", multiplier: "
					+ hm.multiplier);
			exploreData("sectiona.txt", hm);
			hm.resetStatistics();
			System.out.println("==================================");

			// ////////////////////////////////////////////////////////////////////////

			hm = new DoubleHashMap<String, Double>(2000, 1, 4271, 1);
			System.out.println("Test1 DoubleHashMap Size: " + hm.capacity
					+ ", modulus, " + hm.modulus + ", multiplier: "
					+ hm.multiplier + ", secondaryMod: "
					+ ((DoubleHashMap) hm).secondaryMod);
			exploreData("sectiona.txt", hm);
			hm.resetStatistics();
			//System.out.println("==================================");

			hm = new DoubleHashMap<String, Double>(2000, 1, 4271, 223);
			System.out.println("Test2 DoubleHashMap Size: " + hm.capacity
					+ ", modulus, " + hm.modulus + ", multiplier: "
					+ hm.multiplier + ", secondaryMod: "
					+ ((DoubleHashMap) hm).secondaryMod);
			exploreData("sectiona.txt", hm);
			hm.resetStatistics();
			//System.out.println("==================================");

			hm = new DoubleHashMap<String, Double>(2000, 1, 4271, 647);
			System.out.println("Test3 DoubleHashMap Size: " + hm.capacity
					+ ", modulus, " + hm.modulus + ", multiplier: "
					+ hm.multiplier + ", secondaryMod: "
					+ ((DoubleHashMap) hm).secondaryMod);
			exploreData("sectiona.txt", hm);
			hm.resetStatistics();
			//System.out.println("==================================");

			hm = new DoubleHashMap<String, Double>(4000, 1, 4271, 1);
			System.out.println("Test4 DoubleHashMap Size: " + hm.capacity
					+ ", modulus, " + hm.modulus + ", multiplier: "
					+ hm.multiplier + ", secondaryMod: "
					+ ((DoubleHashMap) hm).secondaryMod);
			exploreData("sectiona.txt", hm);
			hm.resetStatistics();
			//System.out.println("==================================");

			hm = new DoubleHashMap<String, Double>(4000, 1, 4271, 223);
			System.out.println("Test5 DoubleHashMap Size: " + hm.capacity
					+ ", modulus, " + hm.modulus + ", multiplier: "
					+ hm.multiplier + ", secondaryMod: "
					+ ((DoubleHashMap) hm).secondaryMod);
			exploreData("sectiona.txt", hm);
			hm.resetStatistics();
			//System.out.println("==================================");

			hm = new DoubleHashMap<String, Double>(4000, 1, 4271, 647);
			System.out.println("Test6 DoubleHashMap Size: " + hm.capacity
					+ ", modulus, " + hm.modulus + ", multiplier: "
					+ hm.multiplier + ", secondaryMod: "
					+ ((DoubleHashMap) hm).secondaryMod);
			exploreData("sectiona.txt", hm);
			hm.resetStatistics();
			System.out.println("==================================");
			
			
			///////////////////////////////////////////////////////////////
			
			
			hm = new ChainingHashMap<String, Double>(4000, 1, 4000);
			System.out.println("Test1 ChainingHashMap Size: " + hm.capacity
					+ ", modulus, " + hm.modulus + ", multiplier: "
					+ hm.multiplier);
			exploreData2("sectiona.txt", hm);
			hm.resetStatistics();
			//System.out.println("==================================");
			
			hm = new ChainingHashMap<String, Double>(4000, 10, 4000);
			System.out.println("Test2 ChainingHashMap Size: " + hm.capacity
					+ ", modulus, " + hm.modulus + ", multiplier: "
					+ hm.multiplier);
			exploreData2("sectiona.txt", hm);
			hm.resetStatistics();
			//System.out.println("==================================");
			
			hm = new ChainingHashMap<String, Double>(4000, 1, 4271);
			System.out.println("Test3 ChainingHashMap Size: " + hm.capacity
					+ ", modulus, " + hm.modulus + ", multiplier: "
					+ hm.multiplier);
			exploreData2("sectiona.txt", hm);
			hm.resetStatistics();
			//System.out.println("==================================");
			
			hm = new ChainingHashMap<String, Double>(4000, 5, 4271);
			System.out.println("Test4 ChainingHashMap Size: " + hm.capacity
					+ ", modulus, " + hm.modulus + ", multiplier: "
					+ hm.multiplier);
			exploreData2("sectiona.txt", hm);
			hm.resetStatistics();
			//System.out.println("==================================");
			
			hm = new ChainingHashMap<String, Double>(2000, 1, 4271);
			System.out.println("Test5 ChainingHashMap Size: " + hm.capacity
					+ ", modulus, " + hm.modulus + ", multiplier: "
					+ hm.multiplier);
			exploreData2("sectiona.txt", hm);
			hm.resetStatistics();
			//System.out.println("==================================");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void exploreData(String pathToFile, HashMap<String, Double> hm)
			throws FileNotFoundException, IOException {

		BufferedReader br = new BufferedReader(new FileReader(pathToFile));
		try {
			String line = br.readLine();
			while (line != null) {
				String[] pieces = line.trim().split("\\s+");
				if (pieces.length == 4) {
					hm.put(pieces[0], Double.valueOf(pieces[1]));
				}
				line = br.readLine();
			}
		} finally {
			br.close();
		}

		System.out.println("Size:" + hm.size());
		System.out.println("Max collision:" + hm.maxCollisions());
		System.out.println("Put collision:" + hm.putCollisions());
		System.out.println("Total collision:" + hm.totalCollisions());
	}

	public static void exploreData2(String pathToFile,
			HashMap<String, Double> hm) throws FileNotFoundException,
			IOException {

		BufferedReader br = new BufferedReader(new FileReader(pathToFile));
		try {
			String line = br.readLine();
			while (line != null) {
				String[] pieces = line.trim().split("\\s+");
				if (pieces.length == 4) {
					hm.put(pieces[0], Double.valueOf(pieces[1]));
				}
				line = br.readLine();
			}
		} finally {
			br.close();
		}

		int[] result = ((ChainingHashMap) hm).getFullestBuckets();
		System.out.println("Fullest bucket:" + result[0] + "," + result[1]);
	}
}
