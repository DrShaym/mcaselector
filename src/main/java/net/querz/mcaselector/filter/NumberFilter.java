package net.querz.mcaselector.filter;

public abstract class NumberFilter<T extends Number> extends Filter<T> {

	private static final Comparator[] comparators = new Comparator[] {
		Comparator.EQUAL,
		Comparator.NOT_EQUAL,
		Comparator.LARGER,
		Comparator.SMALLER,
		Comparator.LARGER_EQUAL,
		Comparator.SMALLER_EQUAL
	};

	private Comparator comparator;

	public NumberFilter(FilterType type, Operator operator, Comparator comparator) {
		super(type, operator);
		this.comparator = comparator;
	}

	@Override
	public T getFilterValue() {
		return getFilterNumber();
	}

	@Override
	public Comparator[] getComparators() {
		return comparators;
	}

	@Override
	public Comparator getComparator() {
		return comparator;
	}

	public void setComparator(Comparator comparator) {
		this.comparator = comparator;
	}

	public boolean matches(T value, T data) {
		switch (comparator) {
			case EQUAL:
				return isEqual(data, value);
			case NOT_EQUAL:
				return isNotEqual(data, value);
			case LARGER:
				return isLargerThan(data, value);
			case SMALLER:
				return isSmallerThan(data, value);
			case LARGER_EQUAL:
				return isLargerEqual(data, value);
			case SMALLER_EQUAL:
				return isSmallerEqual(data, value);
		}
		return false;
	}

	@Override
	public boolean matches(FilterData data) {
		return matches(getFilterNumber(), getNumber(data));
	}

	@Override
	public String toString() {
		return getType() + " " + comparator.getQueryString() + " " + getFilterValue();
	}

	public abstract String getFormatText();

	abstract T getFilterNumber();

	abstract void setFilterNumber(T value);

	abstract T getNumber(FilterData data);

	abstract boolean isEqual(T a, T b);

	abstract boolean isNotEqual(T a, T b);

	abstract boolean isLargerThan(T a, T b);

	abstract boolean isSmallerThan(T a, T b);

	abstract boolean isLargerEqual(T a, T b);

	abstract boolean isSmallerEqual(T a, T b);
}
