package ro.ase.acs.classes;

@FunctionalInterface
public interface DataSeriesOperation {
	public Double doOperation(Integer[] values);
	
	default String transformArray(Integer[] values) {
		String transformation;
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(values[0]);
		for (int i = 1; i < values.length; i ++) {
			stringBuilder.append(", ");
			stringBuilder.append(values[i]);
		}
		transformation = stringBuilder.toString();
		return transformation;
	}
}
