package cn.ibestcode.easiness.utils.codec;

import cn.ibestcode.easiness.utils.exception.UtilsException;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public abstract class CodecSupport {

  public static final String PREFERRED_ENCODING = "UTF-8";

  public static byte[] toBytes(char[] chars) {
    return toBytes(new String(chars), PREFERRED_ENCODING);
  }

  public static byte[] toBytes(char[] chars, String encoding) {
    return toBytes(new String(chars), encoding);
  }


  public static byte[] toBytes(String source) {
    return toBytes(source, PREFERRED_ENCODING);
  }


  public static byte[] toBytes(String source, String encoding) {
    try {
      return source.getBytes(encoding);
    } catch (UnsupportedEncodingException e) {
      log.warn(e.getMessage(), e);
      throw new UtilsException("UnsupportedEncodingException", e, encoding);
    }
  }


  public static String toString(byte[] bytes) {
    return toString(bytes, PREFERRED_ENCODING);
  }


  public static String toString(byte[] bytes, String encoding) {
    try {
      return new String(bytes, encoding);
    } catch (UnsupportedEncodingException e) {
      log.warn(e.getMessage(), e);
      throw new UtilsException("UnsupportedEncodingException", e, encoding);
    }
  }


  public static char[] toChars(byte[] bytes) {
    return toChars(bytes, PREFERRED_ENCODING);
  }


  public static char[] toChars(byte[] bytes, String encoding) {
    return toString(bytes, encoding).toCharArray();
  }


  protected static boolean isByteSource(Object o) {
    return o instanceof byte[] || o instanceof char[] || o instanceof String || o instanceof File || o instanceof InputStream;
  }


  protected static byte[] toBytes(Object o) {
    if (o == null) {
      throw new UtilsException("IllegalArgumentException", "ArgumentForByteConversionCannotBeNull");
    }
    if (o instanceof byte[]) {
      return (byte[]) o;
    } else if (o instanceof char[]) {
      return toBytes((char[]) o);
    } else if (o instanceof String) {
      return toBytes((String) o);
    } else if (o instanceof File) {
      return toBytes((File) o);
    } else if (o instanceof InputStream) {
      return toBytes((InputStream) o);
    } else {
      return objectToBytes(o);
    }
  }

  protected static String toString(Object o) {
    if (o == null) {
      throw new UtilsException("IllegalArgumentException", "ArgumentForByteConversionCannotBeNull");
    }
    if (o instanceof byte[]) {
      return toString((byte[]) o);
    } else if (o instanceof char[]) {
      return new String((char[]) o);
    } else if (o instanceof String) {
      return (String) o;
    } else {
      return objectToString(o);
    }
  }

  protected static byte[] toBytes(File file) {
    if (file == null) {
      throw new UtilsException("IllegalArgumentException", "FileArgumentCannotBeNull");
    }
    try {
      return toBytes(new FileInputStream(file));
    } catch (FileNotFoundException e) {
      log.warn(e.getMessage(), e);
      throw new UtilsException("FileNotFoundException", e);
    }
  }

  protected static byte[] toBytes(InputStream in) {
    if (in == null) {
      throw new UtilsException("IllegalArgumentException", "InputStreamArgumentCannotBeNull");
    }
    final int BUFFER_SIZE = 512;
    ByteArrayOutputStream out = new ByteArrayOutputStream(BUFFER_SIZE);
    byte[] buffer = new byte[BUFFER_SIZE];
    int bytesRead;
    try {
      while ((bytesRead = in.read(buffer)) != -1) {
        out.write(buffer, 0, bytesRead);
      }
      return out.toByteArray();
    } catch (IOException e) {
      log.warn(e.getMessage(), e);
      throw new UtilsException("IOException", e);
    } finally {
      try {
        in.close();
      } catch (IOException ignored) {
      }
      try {
        out.close();
      } catch (IOException ignored) {
      }
    }
  }

  protected static byte[] objectToBytes(Object o) {
    String msg = "The " + CodecSupport.class.getName() + " implementation only supports conversion to " +
      "byte[] if the source is of type byte[], char[], String, " +
      " File or InputStream.  The instance provided as a method " +
      "argument is of type [" + o.getClass().getName() + "].  If you would like to convert " +
      "this argument type to a byte[], you can 1) convert the argument to one of the supported types " +
      "yourself and then use that as the method argument or 2) subclass " + CodecSupport.class.getName() +
      "and override the objectToBytes(Object o) method.";
    throw new UtilsException("ObjectToBytesException", msg);
  }

  /**
   * Default implementation merely returns <code>objectArgument.toString()</code>.  Subclasses can override this
   * method for different mechanisms of converting an object to a String.
   *
   * @param o the Object to convert to a byte array.
   * @return a String representation of the Object argument.
   */
  protected static String objectToString(Object o) {
    return o.toString();
  }
}
