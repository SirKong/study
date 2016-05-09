import org.springframework.beans.factory.InitializingBean;

public class TestBean implements InitializingBean {

	private String str;

	public TestBean() {
		System.out.println("constructor");
	}

	public void init() {
		System.out.println("init");
	}

	public void setStr(String str) {
		System.out.println("set");
		this.str = str;
	}

	private String getStr() {
		return this.str;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("after");
	}
}
