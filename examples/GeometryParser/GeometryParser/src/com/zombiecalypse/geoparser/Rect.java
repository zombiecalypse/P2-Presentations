package com.zombiecalypse.geoparser;

public class Rect {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + xup;
		result = prime * result + y;
		result = prime * result + yup;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rect other = (Rect) obj;
		if (x != other.x)
			return false;
		if (xup != other.xup)
			return false;
		if (y != other.y)
			return false;
		if (yup != other.yup)
			return false;
		return true;
	}

	private final int x;
	private final int y;
	private final int xup;
	private final int yup;

	public Rect(int i, int j, int k, int l) {
		this.x = i;
		this.y = j;
		this.xup = k;
		this.yup = l;
	}
	
}
