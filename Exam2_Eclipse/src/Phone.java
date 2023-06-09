import java.io.Serializable;

public class Phone implements ElectronicDevices, Serializable, Cloneable {
	private float weight;
	private double diagonal;
	private String producer;

	public Phone() {
		weight = 0;
		diagonal = 0;
		producer = "";
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) throws Exception {
		if (weight <= 0) {
			throw new Exception();
		} else {
			this.weight = weight;
		}
	}

	public double getDiagonal() {
		return diagonal;
	}

	public void setDiagonal(double diagonal) throws Exception {
		if (diagonal <= 0) {
			throw new Exception();
		} else {
			this.diagonal = diagonal;
		}
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) throws Exception {
		if (producer.equals(null) || producer.length() <= 1) {
			throw new Exception();
		} else {
			this.producer = producer;
		}
	}

	@Override
	public String infoDevice() {
		return producer;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

}
