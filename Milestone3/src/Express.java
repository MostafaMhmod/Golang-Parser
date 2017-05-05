
/**
 * @author moustafa
 *
 */
public class Express {
	public int address;

	public int getAddress() {
		return address;
	}

	public void setAddress(int address) {
		this.address = address;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String code;

	static int counter = 0;

	public Express(Express e, String code, Express f) {
		// TODO Auto-generated constructor stub
		setCode(e.code + code + f.code);

	}

	public Express() {
		// TODO Auto-generated constructor stub
	}

	public static int Temp() {

		return counter++;
	}

}
