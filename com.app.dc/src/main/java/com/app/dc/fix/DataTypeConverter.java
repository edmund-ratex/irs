package com.app.dc.fix;

import java.math.BigDecimal;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * bbox
 * 
 */
public class DataTypeConverter {

	private static Logger logger = LoggerFactory.getLogger(DataTypeConverter.class);
	public static Map<?, ?> toMap(Map<String, Object> values, String key) {
		return (Map<?, ?>) values.get(key);
	}

	public static List<Map<?, ?>> toList(Map<String, Object> values, String key) {
		return (List<Map<?, ?>>) values.get(key);
	}

	public static float toFloat(Map<String, Object> values, String key,
			float def) {
		Float v = null;
		try {
			v = toFloat(values, key);
		} catch (RuntimeException e) {
			logger.error(e.getMessage());
		}
		if (v == null) {
			v = def;
		}
		values.put(key, def);
		return v;
	}

	public static double toDouble(Map<String, Object> values, String key,
			double def) {
		Double v = null;
		try {
			v = toDouble(values, key);
		} catch (RuntimeException e) {
			logger.error(e.getMessage());
		}
		if (v == null) {
			v = def;
		}
		values.put(key, def);
		return v;
	}

	public static byte toByte(Map<String, Object> values, String key, byte def) {
		Byte v = null;
		try {
			v = toByte(values, key);
		} catch (RuntimeException e) {
			logger.error(e.getMessage());
		}
		if (v == null) {
			v = def;
		}
		values.put(key, def);
		return v;
	}

	public static int toInt(Map<String, Object> values, String key, int def) {
		Integer v = null;
		try {
			v = toInt(values, key);
		} catch (RuntimeException e) {
			logger.error(e.getMessage());
		}
		if (v == null) {
			v = def;
		}
		values.put(key, def);
		return v;
	}

	public static long toLong(Map<String, Object> values, String key, long def) {
		Long v = null;
		try {
			v = toLong(values, key);
		} catch (RuntimeException e) {
			logger.error(e.getMessage());
		}
		if (v == null) {
			v = def;
		}
		values.put(key, def);
		return v;
	}

	public static short toShort(Map<String, Object> values, String key,
			short def) {
		Short v = null;
		try {
			v = toShort(values, key);
		} catch (RuntimeException e) {
			logger.error(e.getMessage());
		}
		if (v == null) {
			v = def;
		}
		values.put(key, def);
		return v;
	}

	public static boolean toBoolean(Map<String, Object> values, String key,
			boolean def) {
		Boolean v = null;
		try {
			v = toBoolean(values, key);
		} catch (RuntimeException e) {
			logger.error(e.getMessage());
		}
		if (v == null) {
			v = def;
		}
		values.put(key, def);
		return v;
	}

	public static BigDecimal toBigDecimal(Map<String, Object> values,
			String key, BigDecimal def) {
		BigDecimal v = null;
		try {
			v = toBigDecimal(values, key);
		} catch (RuntimeException e) {
			logger.error(e.getMessage());
		}
		if (v == null) {
			v = def;
		}
		values.put(key, def);
		return v;
	}

	public static String toString(Map<String, Object> values, String key,
			String def) {
		String v = null;
		try {
			v = toString(values, key);
		} catch (RuntimeException e) {
			logger.error(e.getMessage());
		}
		if (v == null) {
			v = def;
		}
		values.put(key, def);
		return v;
	}

	public static String toString(Map<String, Object> values, String key) {
		Object objValue = values.get(key);
		String value = Utils.toString(objValue);
		if (value != null) {
			values.put(key, value);
		}
		return value;
	}

	public static Float toFloat(Map<String, Object> values, String key) {
		Object objValue = values.get(key);
		Float value = Utils.toFloat(objValue);
		if (value != null) {
			values.put(key, value);
		}
		return value;
	}

	public static Double toDouble(Map<String, Object> values, String key) {
		Object objValue = values.get(key);
		Double value = Utils.toDouble(objValue);
		if (value != null) {
			values.put(key, value);
		}
		return value;
	}

	public static Byte toByte(Map<String, Object> values, String key) {
		Object objValue = values.get(key);
		Byte value = Utils.toByte(objValue);
		if (value != null) {
			values.put(key, value);
		}
		return value;
	}

	public static Integer toInt(Map<String, Object> values, String key) {
		Object objValue = values.get(key);
		Integer value = Utils.toInt(objValue);
		if (value != null) {
			values.put(key, value);
		}
		return value;
	}

	public static Long toLong(Map<String, Object> values, String key) {
		Object objValue = values.get(key);
		Long value = Utils.toLong(objValue);
		if (value != null) {
			values.put(key, value);
		}
		return value;
	}

	public static Short toShort(Map<String, Object> values, String key) {
		Object objValue = values.get(key);
		Short value = Utils.toShort(objValue);
		if (value != null) {
			values.put(key, value);
		}
		return value;
	}

	public static Character toChar(Map<String, Object> values, String key) {
		Object objValue = values.get(key);
		Character value = Utils.toChar(objValue);
		if (value != null) {
			values.put(key, value);
		}
		return value;
	}

	public static Boolean toBoolean(Map<String, Object> values, String key) {
		Object objValue = values.get(key);
		Boolean value = Utils.toBoolean(objValue);
		if (value != null) {
			values.put(key, value);
		}
		return value;
	}

	public static BigDecimal toBigDecimal(Map<String, Object> values, String key) {
		Object objValue = values.get(key);
		BigDecimal value = Utils.toBigDecimal(objValue);
		if (value != null) {
			values.put(key, value);
		}
		return value;
	}

	/**
	 * ת���߼�ͳһ���
	 * 
	 */
	public static class Utils {
		public static String toString(Object objValue) {
			if (objValue == null) {
				return null;
			}
			if (objValue instanceof Number) {
				return new BigDecimal(objValue.toString()).toPlainString();
			} else if (objValue instanceof Boolean) {
				return (((Boolean) objValue) ? "Y" : "N");
			}
			return objValue.toString();
		}

		public static Float toFloat(Object objValue) {
			if (objValue == null) {
				return null;
			}
			if (objValue instanceof Number) {
				return ((Number) objValue).floatValue();
			} else if (objValue instanceof Boolean) {
				return ((Boolean) objValue) ? 1F : 0F;
			} else if (objValue instanceof String) {
				return Float.valueOf(objValue.toString());
			}
			return (Float) objValue;
		}

		public static Double toDouble(Object objValue) {
			if (objValue == null) {
				return null;
			}
			if (objValue instanceof Number) {
				return ((Number) objValue).doubleValue();
			} else if (objValue instanceof Boolean) {
				return ((Boolean) objValue) ? 1D : 0D;
			} else if (objValue instanceof String) {
				return new BigDecimal(objValue.toString()).doubleValue();
			}

			return (Double) objValue;
		}

		public static Byte toByte(Object objValue) {
			if (objValue == null) {
				return null;
			}
			if (objValue instanceof Number) {
				return ((Number) objValue).byteValue();
			} else if (objValue instanceof Boolean) {
				return (byte) (((Boolean) objValue) ? 1 : 0);
			} else if (objValue instanceof String) {
				return Byte.valueOf(objValue.toString());
			}
			return (Byte) objValue;
		}

		public static Integer toInt(Object objValue) {
			if (objValue == null) {
				return null;
			}
			if (objValue instanceof Number) {
				return ((Number) objValue).intValue();
			} else if (objValue instanceof Boolean) {
				return (Integer) (((Boolean) objValue) ? 1 : 0);
			} else if (objValue instanceof String) {
				return Integer.valueOf(objValue.toString());
			}
			return (Integer) objValue;
		}

		public static Long toLong(Object objValue) {
			if (objValue == null) {
				return null;
			}
			if (objValue instanceof Number) {
				return ((Number) objValue).longValue();
			} else if (objValue instanceof Boolean) {
				return (((Boolean) objValue) ? 1l : 0l);
			} else if (objValue instanceof String) {
				return Long.valueOf(objValue.toString());
			}
			return (Long) objValue;
		}

		public static Short toShort(Object objValue) {
			if (objValue == null) {
				return null;
			}
			if (objValue instanceof Number) {
				return ((Number) objValue).shortValue();
			} else if (objValue instanceof Boolean) {
				return (short) (((Boolean) objValue) ? 1 : 0);
			} else if (objValue instanceof String) {
				return Short.valueOf(objValue.toString());
			}
			return (Short) objValue;
		}

		public static Character toChar(Object objValue) {
			if (objValue == null) {
				return null;
			}
			if (objValue instanceof Number) {
				return (char) (((Number) objValue).intValue());
			} else if (objValue instanceof Boolean) {
				return (((Boolean) objValue) ? 'Y' : 'N');
			} else if (objValue instanceof String) {
				String s = (String) objValue;
				return s.length() == 0 ? 0 : s.charAt(0);
			}
			return (Character) objValue;
		}

		public static Boolean toBoolean(Object objValue) {
			if (objValue == null) {
				return null;
			}
			if (objValue instanceof Number) {
				Number nValue = (Number) objValue;
				if (nValue.intValue() == 0) {
					return false;
				} else if (nValue.intValue() == 1) {
					return true;
				}
			} else if (objValue instanceof Boolean) {
				return (Boolean) objValue;
			} else if (objValue instanceof String) {
				String vString = objValue.toString();
				if (vString.equalsIgnoreCase("TRUE")
						|| vString.equalsIgnoreCase("YES")
						|| vString.equalsIgnoreCase("Y") || vString.equals("1")) {
					return true;
				} else if (vString.equalsIgnoreCase("FALSE")
						|| vString.equalsIgnoreCase("NO")
						|| vString.equalsIgnoreCase("N") || vString.equals("0")) {
					return false;
				}
			}
			return (Boolean) objValue;
		}

		public static BigDecimal toBigDecimal(Object objValue) {
			if (objValue == null) {
				return null;
			}
			if (objValue instanceof Number || objValue instanceof String) {
				return new BigDecimal(objValue.toString());
			} else if (objValue instanceof Boolean) {
				return new BigDecimal(((Boolean) objValue) ? "1" : "0");
			}
			return (BigDecimal) objValue;
		}
	}

	public enum Type {
		BYTE("byte"), INT("int"), FLOAT("float"), DOUBLE("double"), BIG_DECIMAL(
				"bigDecimal"), SHORT("short"), LONG("long"), BOOL("bool"), STRING(
				"string"), CHAR("char");
		private String name = null;

		private Type(String name) {
			this.name = name;
		}

		public String toString() {
			return name;
		}

		public static Type toType(String type) {
			Type t[] = Type.values();
			for (Type i : t) {
				if (i.toString().equals(type)) {
					return i;
				}
			}
			return null;
		}

		public static String toString(Type type) {
			return type.toString();
		}

	}

	public static Object convertType(String key, Map map, String type) {
		Type t = Type.toType(type);
		if (t == null) {
			return map.get(key);
		}
		return convertType(key, map, t);
	}

	public static Object convertType(String key, Map map, Type type) {
		if (type == Type.BYTE) {
			return toByte(map, key);
		} else if (type == Type.INT) {
			return toInt(map, key);
		} else if (type == Type.FLOAT) {
			return toFloat(map, key);
		} else if (type == Type.DOUBLE) {
			return toDouble(map, key);
		} else if (type == Type.BIG_DECIMAL) {
			return toBigDecimal(map, key);
		} else if (type == Type.SHORT) {
			return toShort(map, key);
		} else if (type == Type.LONG) {
			return toBoolean(map, key);
		} else if (type == Type.BOOL) {
			return toBoolean(map, key);
		} else if (type == Type.STRING) {
			return toString(map, key);
		} else if (type == Type.CHAR) {
			return toChar(map, key);
		}
		return map.get(key);
	}

	public static boolean checkType(Object obj, String type) {
		Type t = Type.toType(type);
		if (t == null) {
			return false;
		}
		return checkType(obj, t);
	}

	public static boolean checkType(Object obj, Type type) {
		if (type == Type.BYTE) {
			return (obj instanceof Byte) ? true : false;
		} else if (type == Type.INT) {
			return (obj instanceof Integer) ? true : false;
		} else if (type == Type.FLOAT) {
			return (obj instanceof Float) ? true : false;
		} else if (type == Type.DOUBLE) {
			return (obj instanceof Double) ? true : false;
		} else if (type == Type.BIG_DECIMAL) {
			return (obj instanceof BigDecimal) ? true : false;
		} else if (type == Type.SHORT) {
			return (obj instanceof Short) ? true : false;
		} else if (type == Type.LONG) {
			return (obj instanceof Long) ? true : false;
		} else if (type == Type.BOOL) {
			return (obj instanceof Boolean) ? true : false;
		} else if (type == Type.STRING) {
			return (obj instanceof String) ? true : false;
		} else if (type == Type.CHAR) {
			return (obj instanceof Character) ? true : false;
		}
		return false;
	}

}
