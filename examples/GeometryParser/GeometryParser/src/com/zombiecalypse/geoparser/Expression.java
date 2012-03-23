package com.zombiecalypse.geoparser;

public class Expression {
	
	public static class RotateExpression extends Expression {
		
		@Override
		public String toString() {
			return "RotateExpression [angle=" + angle + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + angle;
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
			RotateExpression other = (RotateExpression) obj;
			if (angle != other.angle)
				return false;
			return true;
		}

		private int angle;

		public RotateExpression(int angle) {
			this.angle = angle;
		}
	}

	public static class TranslateExpression extends Expression {
		@Override
		public String toString() {
			return "TranslateExpression [y=" + y + ", x=" + x + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
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
			TranslateExpression other = (TranslateExpression) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}

		private int y;
		private int x;

		public TranslateExpression(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static class RectExpression extends Expression {

		@Override
		public String toString() {
			return "RectExpression [xdown=" + xdown + ", ydown=" + ydown
					+ ", xup=" + xup + ", yup=" + yup + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + xdown;
			result = prime * result + xup;
			result = prime * result + ydown;
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
			RectExpression other = (RectExpression) obj;
			if (xdown != other.xdown)
				return false;
			if (xup != other.xup)
				return false;
			if (ydown != other.ydown)
				return false;
			if (yup != other.yup)
				return false;
			return true;
		}

		private int xdown;
		private int ydown;
		private int xup;
		private int yup;

		public RectExpression(int xdown, int ydown, int xup, int yup) {
			this.xdown = xdown;
			this.ydown = ydown;
			this.xup = xup;
			this.yup = yup;
		}
	}
}
