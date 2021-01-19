
public class Test {

	public static void main(String[] args) {
		System.out.println(System.getProperty("java.runtime.version"));
		Thread p1 = new Thread(()-> {
			for(int p = 1; p<100; p++) {
				System.out.println(p + " Hej");
			}
		});
		Thread p2 = new Thread(()-> {
			for(int i = 1; i < 100; i++) {
				System.out.println(i + " Tjena");
			}
		});
		p1.start();
		p2.start();
	}

}
