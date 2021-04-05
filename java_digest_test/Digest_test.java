import java.security.MessageDigest;
import java.util.Scanner;

public class Digest_test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Scanner in = new Scanner(System.in);
		
		String txt = in.nextLine();
		
		StringBuffer sbuf = new StringBuffer();
		
		//MessageDigest mDigest = MessageDigest.getInstance("MD5");
		//MessageDigest mDigest = MessageDigest.getInstance("SHA-1");
		MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
		mDigest.update(txt.getBytes());
		
		byte[] msgStr = mDigest.digest();
		
		for(int i=0; i < msgStr.length; i++){
	        String tmpEncTxt = Integer.toHexString((int)msgStr[i] & 0x000000ff) ;          
	        sbuf.append(tmpEncTxt);
	    }
		
		System.out.println(sbuf.toString());
	}

}
