package test.sync;

public abstract class Stu implements StuI{
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void init()
	{
		System.out.println("init success !!");
	}
}
