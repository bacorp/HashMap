import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimplePasswordManager {

	private PasswordHashMap<String, Long> store;

	public SimplePasswordManager() {
		// construct a SimplePasswordManager with 4000 places and default hash
		// parameters multiplier = 1 and modulus = 4271
		store = new PasswordHashMap<String, Long>(4000, 1, 4271);
	}

	public SimplePasswordManager(int size, int multiplier, int modulus) {
		// construct a SimplePasswordManager with size number of places
		store = new PasswordHashMap<String, Long>(size, multiplier, modulus);
	}

	// hashing - djb2 function
	public Long hash(String password) {
		long hash = 5381;
		char[] chars = password.toCharArray();
		for(char c: chars){
			hash = hash * 33 + c;
		}

		return hash;
	}

	// interface methods
	public List<String> listUsers() {
		return store.keys();
	}

	public boolean authenticate(String username, String password) {
		Object value = store.get(username);
		if (value != null) {
			// Long pssd = Long.valueOf((String) value);
			if (value.equals(this.hash(password))) {
				return true;
			} else {
				System.out.println("Failed to authenticate user.");
				return false;
			}
		} else {
			System.out.println("No such user exists.");
			return false;
		}
	}

	public void addNewUser(String username, String password) {
		Object value = store.get(username);
		if (value != null) {
			System.out.println("User already exists.");
			return;
		}
		Long pssd = this.hash(password);
		store.put(username, pssd);
	}

	public void deleteUser(String username, String password) {
		boolean isValid = this.authenticate(username, password);
		if (isValid) {
			//store.remove(username);
			System.out.println(store.remove(username));
		}
	}

	public void resetPassword(String username, String oldPassword,
			String newPassword) {
		boolean isValid = this.authenticate(username, oldPassword);
		if (isValid) {
			store.remove(username);
			store.put(username, this.hash(newPassword));
		}
	}

	public void printHashCollisions(String pathToFile)
			throws FileNotFoundException, IOException {

		HashMap<Long, List<String>> a = new HashMap<Long, List<String>>(50000,
				1, 56897);
		SimplePasswordManager s = new SimplePasswordManager();
		BufferedReader br = new BufferedReader(new FileReader(pathToFile));
		try {
			String line = br.readLine();
			while (line != null) {
				String password = line.trim();
				Long passwordHash = s.hash(password); // if passwordHash in a,
														// add password to its
														// list value
				Object value = a.get(passwordHash);
				if(value != null){
					((List<String>)value).add(password);
				}else{
					ArrayList<String> newOne = new ArrayList<String>();
					newOne.add(password);
					a.put(passwordHash, newOne);
				}
				// else, instantiate a new ArrayList and add password to it
				line = br.readLine();
			}
		} finally {
			br.close();
		}
		List<Long> hashes = a.keys();
		for (Long hash : hashes) {
			List<String> passwords = a.get(hash);
			if (passwords.size() > 1) { // all passwords in this list have the
										// same hash representation
				for(String password: passwords){
					System.out.print(password + "|");
				}
				System.out.println();
			}
		}
	}
	
	public int numberUsers(){
		return store.keys().size();
	}

	public static void main(String... args) throws FileNotFoundException, IOException {

		SimplePasswordManager spm = new SimplePasswordManager();
		spm.addNewUser("danny", "12345678");
		spm.addNewUser("tony", "325436457");
		spm.addNewUser("angel", "33333333");
		spm.addNewUser("happy", "567899000");
		System.out.println(spm.numberUsers());
//
//		spm.addNewUser("danny", "1234567844");
		 spm.resetPassword("danny", "12345678","dfdfdf");
		 System.out.println(spm.numberUsers());
		// spm.deleteUser("angel", "frresfewfew");
		//		
		// System.out.println(spm.authenticate("danny", "12345678"));
		// System.out.println(spm.authenticate("happy", "567899000"));
		// System.out.println(spm.authenticate("tony", "325fd457"));
		// System.out.println(spm.authenticate("tony", "3fd436457"));
		//		
		// spm.deleteUser("tony", "325fd457");
		// spm.deleteUser("tony", "325436457");
		// System.out.println(spm.authenticate("tony", "325fd457"));

		//spm.resetPassword("danny", "123456789", "77777777");
		//spm.resetPassword("danny", "12345678", "77777777");
		//System.out.println(spm.numberUsers());
		
		System.out.println(spm.authenticate("danny", "12345678"));
		System.out.println(spm.authenticate("danny", "dfdfdf"));
		
		
		// test for collision of password
		spm.printHashCollisions("sectionb.txt");
	}

}
